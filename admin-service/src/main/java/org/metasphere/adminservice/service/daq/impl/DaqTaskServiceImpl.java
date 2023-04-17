package org.metasphere.adminservice.service.daq.impl;

import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.constant.MsConst;
import org.metasphere.adminservice.constant.MsStatusCode;
import org.metasphere.adminservice.exception.MsException;
import org.metasphere.adminservice.model.bo.daq.DaqEngineWeiboItem;
import org.metasphere.adminservice.model.bo.daq.MongoWeibo;
import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.daq.*;
import org.metasphere.adminservice.model.vo.DaqTaskTimingDataVolumes;
import org.metasphere.adminservice.model.vo.DataVolume;
import org.metasphere.adminservice.model.vo.TimingDataVolumes;
import org.metasphere.adminservice.repository.daq.DaqTaskRepository;
import org.metasphere.adminservice.service.ScrapydService;
import org.metasphere.adminservice.service.daq.*;
import org.metasphere.adminservice.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 20:13
 * @Modified By:
 */
@Slf4j
@Service(value = "daqTaskService")
public class DaqTaskServiceImpl implements DaqTaskService {

    @Autowired
    private DaqTaskRepository daqTaskRepository;

    @Autowired
    private DaqTaskKeywordService daqTaskKeywordService;

    @Autowired
    private DaqSpiderService daqSpiderService;

    @Autowired
    private DaqTaskSpiderService daqTaskSpiderService;

    @Autowired
    private DaqTaskServerService daqTaskServerService;

    @Autowired
    private DaqEngineDbService daqEngineDbService;

    @Autowired
    private DaqTaskDataVolumeService daqTaskDataVolumeService;

    @Autowired
    private ScrapydService scrapydService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDaqTask(DaqTask daqTask) {
        daqTask.setStage(MsConst.DaqTask.Stage.NEW);
        daqTask.setCreatedAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDaqTask(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);
        if (MsConst.DaqTask.Stage.NEW != daqTask.getStage()) {
            throw new MsException(MsStatusCode.DAQ_TASK_STAGE_ERROR);
        }
        daqTask.setStatus(MsConst.MetaSphereEntity.Status.DISABLED);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void initDaqTask(Long daqTaskId) {
        String taskCode = UUIDUtils.getDaqTaskCode();

        // 创建任务编号，更新任务运行阶段
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);
        daqTask.setCode(taskCode);
        daqTask.setStage(MsConst.DaqTask.Stage.TASK_CONFIGURING);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);

        // 创建Scrapy项目
        List<DaqTaskServer> taskServers = daqTaskServerService.findDaqTaskServers(daqTaskId);
        taskServers.forEach(taskServer ->
                scrapydService.addScrapyProject(taskServer.getServerIpAddress(), taskServer.getServerPort(), taskCode));

        // 创建数据存储表
        daqEngineDbService.createDaqTaskDataTable(taskCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void performDaqTask(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);

        // 将该项目的关键词放入缓存
        List<DaqTaskKeyword> taskKeywords = daqTaskKeywordService.findDaqTaskKeywords(daqTaskId);
        List<String> keywords = taskKeywords.stream().map(DaqTaskKeyword::getKeyword).collect(Collectors.toList());
        String keywordsCacheKey = String.format(MsConst.CacheKeyTemplate.DAQ_TASK_KEYWORDS, daqTask.getCode());
        redisTemplate.opsForList().rightPushAll(keywordsCacheKey, keywords);

        // 启动数据采集爬虫
        List<DaqTaskSpider> taskSpiders = daqTaskSpiderService.findDaqTaskSpiders(daqTaskId);
        taskSpiders.forEach(taskSpider -> {
            String jobId = scrapydService.scheduleScrapySpider(taskSpider.getServerIpAddress(), taskSpider.getServerPort(), taskSpider.getTaskCode(), taskSpider.getSpiderCode());
            taskSpider.setJobId(jobId);
            daqTaskSpiderService.updateDaqTaskSpider(taskSpider);
        });

        // 将数据采集任务及启用的爬虫计入缓存，用于数据量统计
        redisTemplate.opsForList().rightPushAll(MsConst.CacheKey.RUNNING_DAQ_TASKS_CODE, daqTask.getCode());

        List<String> spiderCodes = taskSpiders.stream().map(DaqTaskSpider::getSpiderCode).distinct().collect(Collectors.toList());
        if (spiderCodes.contains(MsConst.DaqSpider.Codes.WEIBO)) {
            spiderCodes.add(MsConst.DaqSpider.Codes.WEIBO_USER);
            spiderCodes.add(MsConst.DaqSpider.Codes.WEIBO_LIKE);
            spiderCodes.add(MsConst.DaqSpider.Codes.WEIBO_COMMENT);
            spiderCodes.add(MsConst.DaqSpider.Codes.WEIBO_REPOST);
        }
        String spidersCacheKey = String.format(MsConst.CacheKeyTemplate.DAQ_TASK_SPIDERS, daqTask.getCode());
        redisTemplate.opsForList().rightPushAll(spidersCacheKey, spiderCodes);

        // 修改数据采集任务运行阶段为执行中
        daqTask.setStage(MsConst.DaqTask.Stage.TASK_RUNNING);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopDaqTaskPerforming(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);

        // 从运行数据采集任务编码缓存中删除该编码
        redisTemplate.opsForList().remove(MsConst.CacheKey.RUNNING_DAQ_TASKS_CODE, 0, daqTask.getCode());

        // 删除缓存中该数据采集任务所有的爬虫
        String spidersCacheKey = String.format(MsConst.CacheKeyTemplate.DAQ_TASK_SPIDERS, daqTask.getCode());
        redisTemplate.delete(spidersCacheKey);

        List<DaqTaskSpider> taskSpiders = daqTaskSpiderService.findDaqTaskSpiders(daqTaskId);
        taskSpiders.forEach(taskSpider -> {
            scrapydService.cancelScrapySpider(taskSpider.getServerIpAddress(), taskSpider.getServerPort(), daqTask.getCode(), taskSpider.getJobId());
            // TODO: 将数据库中的DaqTaskSpider状态设置为已停止
        });

        // 停止所有Scrapyd服务器中该数据采集任务
        List<DaqTaskServer> taskServers = daqTaskServerService.findDaqTaskServers(daqTaskId);
        taskServers.forEach(taskServer -> {
            // 从Scrapyd中删除该数据采集任务
            scrapydService.deleteScrapyProject(taskServer.getServerIpAddress(), taskServer.getServerPort(), daqTask.getCode());
        });

        // 删除缓存中该数据采集任务所有的关键词
        String keywordsCacheKey = String.format(MsConst.CacheKeyTemplate.DAQ_TASK_KEYWORDS, daqTask.getCode());
        redisTemplate.delete(keywordsCacheKey);

        // 修改数据采集任务运行阶段为已执行
        daqTask.setStage(MsConst.DaqTask.Stage.TASK_PERFORMED);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enterDaqTaskAcquiredData(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);

        int pageNum = 0;
        int pageSize = 10;

        List<MongoWeibo> mongoWeibos = mongoTemplate.find(Query.query(Criteria.where("task_code").is(daqTask.getCode())).skip(0L).limit(pageSize), MongoWeibo.class);
        while (mongoWeibos.size() > 0) {
            List<DaqEngineWeiboItem> daqEngineWeiboItems = MongoWeibo.batchConvertToDaqEngineWeiboItem(daqTask.getName(), mongoWeibos);
            daqEngineDbService.saveDaqEngineWeiboItems(daqTask.getCode(), daqEngineWeiboItems);
            pageNum++;
            mongoWeibos = mongoTemplate.find(Query.query(Criteria.where("task_code").is(daqTask.getCode())).skip((long) pageNum * pageSize).limit(pageSize), MongoWeibo.class);
        }

        // 修改数据采集任务运行阶段为数据已录入
        daqTask.setStage(MsConst.DaqTask.Stage.DATA_ENTERED);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    @Override
    public MsPage<DaqTask> findDaqTasksByParams(Integer pageNum, Integer pageSize, Integer stage) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<DaqTask> page;
        if (null != stage) {
            page = daqTaskRepository.findAll((Specification<DaqTask>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("stage"), stage));
                return query.where(predicates.toArray(new Predicate[0])).getRestriction();
            }, pageable);
        } else {
            page = daqTaskRepository.findAll(pageable);
        }
        return MsPage.newInstance(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDaqTaskSpiders(Long daqTaskId, List<Long> daqSpiderIds) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);
        List<DaqTaskServer> daqTaskServers = daqTaskServerService.findDaqTaskServers(daqTaskId);
        daqTaskSpiderService.saveDaqTaskSpiders(daqTask, daqTaskServers, daqSpiderIds);
    }

    @Override
    public List<DaqSpider> findDaqSpiders(Long daqTaskId) {
        List<Long> daqSpiderIds = daqTaskSpiderService.findDaqSpiderIdsByTaskId(daqTaskId);
        return daqSpiderService.findDaqSpidersByIds(daqSpiderIds);
    }

    @Override
    public List<DaqTaskSpider> findDaqTaskSpiders(Long daqTaskId) {
        return daqTaskSpiderService.findDaqTaskSpiders(daqTaskId);
    }

    @Override
    public MsPage<DaqTaskSpider> findDaqTaskSpidersByPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        return daqTaskSpiderService.findDaqTaskSpidersByPagination(daqTaskId, pageNum, pageSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDaqTaskKeywords(Long daqTaskId, List<String> keywords) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);
        daqTaskKeywordService.saveDaqTaskKeywords(daqTask, keywords);
    }

    @Override
    public MsPage<DaqTaskKeyword> findDaqTaskKeywordsByPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        return daqTaskKeywordService.findDaqTaskKeywordsByPagination(daqTaskId, pageNum, pageSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindDaqTaskServers(Long daqTaskId, List<Long> serverIds) {
        daqTaskServerService.bindDaqTaskServers(daqTaskId, serverIds);
    }

    @Override
    public DaqTaskTimingDataVolumes findDaqTaskTimingDataVolumes(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);

        Map<String, List<DaqTaskDataVolume>> dataVolumesMap = daqTaskDataVolumeService.findSpiderCode2DaqDataVolumesMap(daqTask.getCode());
        List<TimingDataVolumes> tdvs = dataVolumesMap.keySet()
                .stream().map(spiderCode -> {
                    List<DaqTaskDataVolume> dataVolumes = dataVolumesMap.get(spiderCode);

                    List<DataVolume> dvs = dataVolumes
                            .stream().map(dataVolume -> {
                                DataVolume dv = new DataVolume();
                                dv.setCountedAt(dataVolume.getCountedAt().toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
                                dv.setDataVolume(dataVolume.getDataVolume());
                                return dv;
                            }).collect(Collectors.toList());

                    TimingDataVolumes tdv = new TimingDataVolumes();
                    tdv.setSpiderName(MsConst.DaqSpider.CODE2NAME.get(spiderCode));
                    tdv.setSpiderCode(spiderCode);
                    tdv.setDataVolumes(dvs);

                    return tdv;
                }).collect(Collectors.toList());

        DaqTaskTimingDataVolumes dttdvs = new DaqTaskTimingDataVolumes();
        dttdvs.setTaskName(daqTask.getName());
        dttdvs.setTaskCode(daqTask.getCode());
        dttdvs.setTimingDataVolumes(tdvs);

        return dttdvs;
    }
}
