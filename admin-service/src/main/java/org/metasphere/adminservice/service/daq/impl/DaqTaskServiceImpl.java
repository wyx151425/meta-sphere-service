package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.constant.MsConst;
import org.metasphere.adminservice.constant.MsStatusCode;
import org.metasphere.adminservice.exception.MsException;
import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.daq.DaqTask;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskKeyword;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskServer;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskSpider;
import org.metasphere.adminservice.repository.daq.DaqTaskRepository;
import org.metasphere.adminservice.service.*;
import org.metasphere.adminservice.service.daq.*;
import org.metasphere.adminservice.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 20:13
 * @Modified By:
 */
@Service(value = "daqTaskService")
public class DaqTaskServiceImpl implements DaqTaskService {

    @Autowired
    private DaqTaskRepository daqTaskRepository;

    @Autowired
    private DaqTaskKeywordService daqTaskKeywordService;

    @Autowired
    private DaqTaskSpiderService daqTaskSpiderService;

    @Autowired
    private DaqTaskServerService daqTaskServerService;

    @Autowired
    private DaqEngineDbService daqEngineDbService;

    @Autowired
    private ScrapydService scrapydService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDaqTask(DaqTask daqTask) {
        daqTask.setStage(MsConst.DaqTask.Stage.CREATED);
        daqTask.setCreatedAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDaqTask(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);
        if (MsConst.DaqTask.Stage.CREATED != daqTask.getStage()) {
            throw new MsException(MsStatusCode.DAQ_TASK_STAGE_ERROR);
        }
        daqTask.setStatus(MsConst.MetaSphereEntity.Status.DISABLED);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startDaqTask(Long daqTaskId) {
        String taskCode = UUIDUtils.getDaqTaskCode();

        // 创建任务编号，更新任务运行阶段
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);
        daqTask.setCode(taskCode);
        daqTask.setStage(MsConst.DaqTask.Stage.CONFIGURING);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);

        // 创建Scrapy项目
        DaqTaskServer taskServer = daqTaskServerService.findDaqTaskServer(daqTaskId);
        scrapydService.addScrapyProject(taskServer.getServerIpAddress(), taskServer.getServerPort(), taskCode);

        // 创建数据存储表
        daqEngineDbService.createDaqDataTable(taskCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeDaqTask(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);

        // 将该项目的关键词放入缓存
        List<DaqTaskKeyword> taskKeywords = daqTaskKeywordService.findDaqTaskKeywords(daqTaskId);
        List<String> keywords = taskKeywords.stream().map(DaqTaskKeyword::getKeyword).collect(Collectors.toList());
        String keywordsCacheKey = String.format(MsConst.CacheKeyTemplate.DAQ_TASK_KEYWORDS, daqTask.getCode());
        redisTemplate.opsForList().rightPushAll(keywordsCacheKey, keywords);

        DaqTaskServer taskServer = daqTaskServerService.findDaqTaskServer(daqTaskId);

        // 启动数据采集爬虫
        List<DaqTaskSpider> taskSpiders = daqTaskSpiderService.findDaqTaskSpiders(daqTaskId);

        taskSpiders.forEach(taskSpider -> {
            String jobId = scrapydService.scheduleScrapySpider(taskServer.getServerIpAddress(), taskServer.getServerPort(),
                    taskSpider.getTaskCode(), taskSpider.getSpiderCode());
            taskSpider.setJobId(jobId);
            taskSpider.setServerIpAddress(taskServer.getServerIpAddress());
            taskSpider.setServerPort(taskServer.getServerPort());
            taskSpider.setUpdateAt(LocalDateTime.now());
            daqTaskSpiderService.updateDaqTaskSpider(taskSpider);
        });

        // 将数据采集任务及启用的爬虫计入缓存，用于数据量统计
        redisTemplate.opsForList().rightPushAll(MsConst.CacheKey.RUNNING_DAQ_TASKS_CODE, daqTask.getCode());

        List<String> spiderCodes = taskSpiders.stream().map(DaqTaskSpider::getSpiderCode).collect(Collectors.toList());
        String spidersCacheKey = String.format(MsConst.CacheKeyTemplate.DAQ_TASK_SPIDERS, daqTask.getCode());
        redisTemplate.opsForList().rightPushAll(spidersCacheKey, spiderCodes);

        // 修改数据采集任务运行阶段
        daqTask.setStage(MsConst.DaqTask.Stage.RUNNING);
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
    public void addDaqTaskSpiders(Long daqTaskId, List<Long> daqSpiderIds) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MsException::getDataNotFoundException);
        daqTaskSpiderService.saveDaqTaskSpiders(daqTask, daqSpiderIds);
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
    public void bindDaqTaskServer(Long daqTaskId, Long serverId) {
        daqTaskServerService.bindDaqTaskServer(daqTaskId, serverId);
    }
}