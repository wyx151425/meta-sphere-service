package org.metasphere.adminservice.service.daq.impl;

import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.context.constant.MSConstant;
import org.metasphere.adminservice.context.constant.MSStatusCode;
import org.metasphere.adminservice.exception.MSException;
import org.metasphere.adminservice.model.bo.daq.*;
import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.HotEvent;
import org.metasphere.adminservice.model.pojo.daq.*;
import org.metasphere.adminservice.model.vo.DaqTaskTimingDataVolumes;
import org.metasphere.adminservice.model.vo.DataVolume;
import org.metasphere.adminservice.model.vo.TimingDataVolumes;
import org.metasphere.adminservice.repository.daq.*;
import org.metasphere.adminservice.service.daq.HotEventService;
import org.metasphere.adminservice.service.daq.ScrapydService;
import org.metasphere.adminservice.service.daq.*;
import org.metasphere.adminservice.util.UUIDUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
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
    private HotEventService hotEventService;

    @Autowired
    private DaqTaskKeywordService daqTaskKeywordService;

    @Autowired
    private DaqSpiderService daqSpiderService;

    @Autowired
    private DaqTaskSpiderService daqTaskSpiderService;

    @Autowired
    private DaqTaskServerService daqTaskServerService;

    @Autowired
    private DaqTaskStorageSpaceService daqTaskStorageSpaceService;

    @Autowired
    private DaqTaskDataVolumeService daqTaskDataVolumeService;

    @Autowired
    private ScrapydService scrapydService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MongoWeiboRepository mongoWeiboRepository;

    @Autowired
    private MongoWeiboLikeRepository mongoWeiboLikeRepository;

    @Autowired
    private MongoWeiboCommentRepository mongoWeiboCommentRepository;

    @Autowired
    private MongoWeiboRepostRepository mongoWeiboRepostRepository;

    @Autowired
    private MongoWeiboUserRepository mongoWeiboUserRepository;

    private static final ExecutorService executorService =
            new ThreadPoolExecutor(20, 60, 60L, TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(20));

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDaqTask(DaqTask daqTask) {
        // 创建任务编号，设置任务执行阶段，保存任务
        DaqTask target = new DaqTask();
        target.setName(daqTask.getName());
        String daqTaskCode = UUIDUtils.getDaqTaskCode();
        target.setCode(daqTaskCode);
        target.setExecutionStage(MSConstant.DaqTask.ExecutionStage.CONFIGURING);
        target.setCreatedAt(LocalDateTime.now().withNano(0));
        daqTaskRepository.save(target);

        // 创建关联的舆情热点事件
        HotEvent hotEvent = new HotEvent();
        hotEvent.setName(daqTask.getName());
        hotEvent.setDaqTaskCode(daqTaskCode);
        hotEvent = hotEventService.saveHotEvent(hotEvent);

        // 更新数据采集任务的关联舆情热点事件ID
        target = daqTaskRepository.findById(target.getId()).orElseThrow(MSException::getDataNotFoundException);
        target.setHotEventId(hotEvent.getId());
        daqTaskRepository.save(target);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDaqTask(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MSException::getDataNotFoundException);

        // 只有新建状态的数据采集任务才能被删除
        if (MSConstant.DaqTask.ExecutionStage.CONFIGURING != daqTask.getExecutionStage()) {
            throw new MSException(MSStatusCode.DAQ_TASK_EXECUTION_STAGE_ERROR);
        }
        daqTask.setStatus(MSConstant.MetaSphereEntity.Status.DISABLED);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);

        // 删除数据采集任务关联的热点事件
        hotEventService.deleteHotEvent(daqTask.getHotEventId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitDaqTask(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MSException::getDataNotFoundException);

        // 检查是否已配置数据采集任务的服务器、爬虫、关键词
        if (0 == daqTaskServerService.countServerNumberByDaqTask(daqTaskId)) {
            throw new MSException(MSStatusCode.MISSING_DAQ_TASK_SERVER);
        }
        if (0 == daqTaskSpiderService.countSpiderNumberByDaqTask(daqTaskId)) {
            throw new MSException(MSStatusCode.MISSING_DAQ_TASK_SPIDER);
        }
        if (0 == daqTaskKeywordService.countKeywordNumberByDaqTask(daqTaskId)) {
            throw new MSException(MSStatusCode.MISSING_DAQ_TASK_KEYWORD);
        }

        // 部署Scrapy项目
        List<DaqTaskServer> taskServers = daqTaskServerService.findDaqTaskServers(daqTaskId);
        taskServers.forEach(taskServer ->
                scrapydService.addScrapyProject(taskServer.getServerIpAddress(), taskServer.getServerPort(), daqTask.getCode()));

        // 更新数据采集任务执行阶段
        daqTask.setExecutionStage(MSConstant.DaqTask.ExecutionStage.WAITING_FOR_EXECUTION);
        daqTask.setUpdateAt(LocalDateTime.now().withNano(0));
        daqTaskRepository.save(daqTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeDaqTask(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MSException::getDataNotFoundException);

        // 将该项目的关键词放入缓存
        List<DaqTaskKeyword> taskKeywords = daqTaskKeywordService.findDaqTaskKeywords(daqTaskId);
        List<String> keywords = taskKeywords.stream().map(DaqTaskKeyword::getKeyword).collect(Collectors.toList());
        String keywordsCacheKey = String.format(MSConstant.CacheKeyTemplate.DAQ_TASK_KEYWORDS, daqTask.getCode());
        redisTemplate.opsForList().rightPushAll(keywordsCacheKey, keywords);

        // 启动数据采集爬虫
        List<DaqTaskSpider> daqTaskSpiders = daqTaskSpiderService.findDaqTaskSpiders(daqTaskId);
        daqTaskSpiders.forEach(daqTaskSpider -> {
            String jobId = scrapydService.scheduleScrapySpider(daqTaskSpider.getServerIpAddress(), daqTaskSpider.getServerPort(), daqTaskSpider.getTaskCode(), daqTaskSpider.getSpiderCode());
            // 更新数据采集任务爬虫的Scrapy项目工作ID
            daqTaskSpider.setJobId(jobId);
            daqTaskSpiderService.updateDaqTaskSpider(daqTaskSpider);
        });

        // 将数据采集任务及启用的爬虫计入缓存，用于数据量统计
        redisTemplate.opsForList().rightPushAll(MSConstant.CacheKey.RUNNING_DAQ_TASKS_CODE, daqTask.getCode());

        List<String> spiderCodes = daqTaskSpiders.stream().map(DaqTaskSpider::getSpiderCode).distinct().collect(Collectors.toList());
        if (spiderCodes.contains(MSConstant.DaqSpider.Codes.WEIBO)) {
            spiderCodes.add(MSConstant.DaqSpider.Codes.WEIBO_USER);
            spiderCodes.add(MSConstant.DaqSpider.Codes.WEIBO_LIKE);
            spiderCodes.add(MSConstant.DaqSpider.Codes.WEIBO_COMMENT);
            spiderCodes.add(MSConstant.DaqSpider.Codes.WEIBO_REPOST);
        }
        String spidersCacheKey = String.format(MSConstant.CacheKeyTemplate.DAQ_TASK_SPIDERS, daqTask.getCode());
        redisTemplate.opsForList().rightPushAll(spidersCacheKey, spiderCodes);

        // 修改数据采集任务运行阶段为执行中
        daqTask.setExecutionStage(MSConstant.DaqTask.ExecutionStage.EXECUTING);
        daqTask.setUpdateAt(LocalDateTime.now().withNano(0));
        daqTaskRepository.save(daqTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopDaqTask(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MSException::getDataNotFoundException);

        // 从运行数据采集任务编码缓存中删除该编码
        redisTemplate.opsForList().remove(MSConstant.CacheKey.RUNNING_DAQ_TASKS_CODE, 0, daqTask.getCode());

        // 删除缓存中该数据采集任务所有的爬虫
        String spidersCacheKey = String.format(MSConstant.CacheKeyTemplate.DAQ_TASK_SPIDERS, daqTask.getCode());
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
        String keywordsCacheKey = String.format(MSConstant.CacheKeyTemplate.DAQ_TASK_KEYWORDS, daqTask.getCode());
        redisTemplate.delete(keywordsCacheKey);

        // 修改数据采集任务运行阶段为已执行
        daqTask.setExecutionStage(MSConstant.DaqTask.ExecutionStage.WAIT_TO_IMPORT_DATA);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    @Override
    public void startImportDaqTaskAcquiredData(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MSException::getDataNotFoundException);

        // 创建数据存储表，此处不需要事务，因为事务无法控制DDL语句
        daqTaskStorageSpaceService.createDaqTaskStorageSpace(daqTask.getCode());

        // 修改任务状态为数据导入中
        daqTask.setExecutionStage(MSConstant.DaqTask.ExecutionStage.IMPORTING_DATA);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);

        String daqTaskCode = daqTask.getCode();

        // 将导入数据的消息发布到RabbitMQ，异步导入数据
        rabbitTemplate.convertAndSend("directExchange", "directRoutingKey", daqTaskCode);
    }

    @Override
    public void executeImportDaqTaskAcquiredData(String daqTaskCode) {
        // 根据数据采集任务配置的爬虫计算需要导入的数据表的数量
        DaqTask daqTask = daqTaskRepository.findOneByCode(daqTaskCode);

        List<DaqTaskSpider> daqTaskSpiders = daqTaskSpiderService.findDaqTaskSpiders(daqTask.getId());
        List<String> spiderCodes = daqTaskSpiders.stream().map(DaqTaskSpider::getSpiderCode).distinct().collect(Collectors.toList());
        int spiderCount = spiderCodes.size();
        if (spiderCodes.contains(MSConstant.DaqSpider.Codes.WEIBO)) {
            spiderCount += 4;
        }
        CountDownLatch countDownLatch = new CountDownLatch(spiderCount);

        // 根据数据采集任务配置的爬虫开始导入数据
        if (spiderCodes.contains(MSConstant.DaqSpider.Codes.WEIBO)) {
            executorService.execute(() -> {
                handleAcquiredWeiboData(daqTaskCode);
                countDownLatch.countDown();
            });
            executorService.execute(() -> {
                handleAcquiredWeiboLikeData(daqTaskCode);
                countDownLatch.countDown();
            });
            executorService.execute(() -> {
                handleAcquiredWeiboCommentData(daqTaskCode);
                countDownLatch.countDown();
            });
            executorService.execute(() -> {
                handleAcquiredWeiboRepostData(daqTaskCode);
                countDownLatch.countDown();
            });
            executorService.execute(() -> {
                handleAcquiredWeiboUserData(daqTaskCode);
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("The acquired data has been imported successfully!");

        // 修改数据采集任务运行阶段为数据已录入
        daqTask.setExecutionStage(MSConstant.DaqTask.ExecutionStage.WAITING_FOR_DATA_ANALYSIS);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    private void handleAcquiredWeiboData(String daqTaskCode) {
        int pageNum = 0;
        int pageSize = 100;

        long dataCount = mongoWeiboRepository.countAllByTaskCode(daqTaskCode);
        redisTemplate.opsForValue().set("ACQUIRED_DATA_COUNT:WEIBO:" + daqTaskCode, String.valueOf(dataCount));
        redisTemplate.opsForValue().set("IMPORTED_DATA_COUNT:WEIBO:" + daqTaskCode, "0");

        while ((long) pageNum * pageSize <= dataCount) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<MongoWeibo> page = mongoWeiboRepository.findAllByTaskCode(daqTaskCode, pageable);
            daqTaskStorageSpaceService.saveMongoWeibos(daqTaskCode, page.getContent());
            log.info("handleAcquiredWeiboData: " + pageNum * pageSize + " ---->");
            pageNum++;
        }
    }

    private void handleAcquiredWeiboLikeData(String daqTaskCode) {
        int pageNum = 0;
        int pageSize = 100;

        long dataCount = mongoWeiboLikeRepository.countAllByTaskCode(daqTaskCode);
        redisTemplate.opsForValue().set("ACQUIRED_DATA_COUNT:WEIBO_LIKE:" + daqTaskCode, String.valueOf(dataCount));
        redisTemplate.opsForValue().set("IMPORTED_DATA_COUNT:WEIBO_LIKE:" + daqTaskCode, "0");

        while ((long) pageNum * pageSize <= dataCount) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<MongoWeiboLike> page = mongoWeiboLikeRepository.findAllByTaskCode(daqTaskCode, pageable);
            daqTaskStorageSpaceService.saveMongoWeiboLikes(daqTaskCode, page.getContent());
            log.info("handleAcquiredWeiboLikeData: " + pageNum * pageSize + " ---->");
            pageNum++;
        }
    }

    private void handleAcquiredWeiboCommentData(String daqTaskCode) {
        int pageNum = 0;
        int pageSize = 100;

        long dataCount = mongoWeiboCommentRepository.countAllByTaskCode(daqTaskCode);
        redisTemplate.opsForValue().set("ACQUIRED_DATA_COUNT:WEIBO_COMMENT:" + daqTaskCode, String.valueOf(dataCount));
        redisTemplate.opsForValue().set("IMPORTED_DATA_COUNT:WEIBO_COMMENT:" + daqTaskCode, "0");

        while ((long) pageNum * pageSize <= dataCount) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<MongoWeiboComment> page = mongoWeiboCommentRepository.findAllByTaskCode(daqTaskCode, pageable);
            daqTaskStorageSpaceService.saveMongoWeiboComments(daqTaskCode, page.getContent());
            log.info("handleAcquiredWeiboCommentData: " + pageNum * pageSize + " ---->");
            pageNum++;
        }
    }

    private void handleAcquiredWeiboRepostData(String daqTaskCode) {
        int pageNum = 0;
        int pageSize = 100;

        long dataCount = mongoWeiboRepostRepository.countAllByTaskCode(daqTaskCode);
        redisTemplate.opsForValue().set("ACQUIRED_DATA_COUNT:WEIBO_REPOST:" + daqTaskCode, String.valueOf(dataCount));
        redisTemplate.opsForValue().set("IMPORTED_DATA_COUNT:WEIBO_REPOST:" + daqTaskCode, "0");

        while ((long) pageNum * pageSize <= dataCount) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<MongoWeiboRepost> page = mongoWeiboRepostRepository.findAllByTaskCode(daqTaskCode, pageable);
            daqTaskStorageSpaceService.saveMongoWeiboReposts(daqTaskCode, page.getContent());
            log.info("handleAcquiredWeiboRepostData: " + pageNum * pageSize + " ---->");
            pageNum++;
        }
    }

    private void handleAcquiredWeiboUserData(String daqTaskCode) {
        int pageNum = 0;
        int pageSize = 100;

        long dataCount = mongoWeiboUserRepository.countAllByTaskCode(daqTaskCode);
        redisTemplate.opsForValue().set("ACQUIRED_DATA_COUNT:WEIBO_USER:" + daqTaskCode, String.valueOf(dataCount));
        redisTemplate.opsForValue().set("IMPORTED_DATA_COUNT:WEIBO_USER:" + daqTaskCode, "0");

        while ((long) pageNum * pageSize <= dataCount) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<MongoWeiboUser> page = mongoWeiboUserRepository.findAllByTaskCode(daqTaskCode, pageable);
            daqTaskStorageSpaceService.saveMongoWeiboUsers(daqTaskCode, page.getContent());
            log.info("handleAcquiredWeiboUserData: " + pageNum * pageSize + " ---->");
            pageNum++;
        }
    }

    @Override
    public List<ImportedDataCount> getImportedDataCounts(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MSException::getDataNotFoundException);
        List<DaqTaskSpider> daqTaskSpiders = daqTaskSpiderService.findDaqTaskSpiders(daqTaskId);
        List<String> daqTaskSpiderCodes = daqTaskSpiders.stream().map(DaqTaskSpider::getSpiderCode).distinct().collect(Collectors.toList());
        if (daqTaskSpiderCodes.contains(MSConstant.DaqSpider.Codes.WEIBO)) {
            daqTaskSpiderCodes.add(MSConstant.DaqSpider.Codes.WEIBO_USER);
            daqTaskSpiderCodes.add(MSConstant.DaqSpider.Codes.WEIBO_LIKE);
            daqTaskSpiderCodes.add(MSConstant.DaqSpider.Codes.WEIBO_COMMENT);
            daqTaskSpiderCodes.add(MSConstant.DaqSpider.Codes.WEIBO_REPOST);
        }
        return daqTaskDataVolumeService.getImportedDataCounts(daqTask.getCode(), daqTaskSpiderCodes);
    }

    @Override
    public MSPage<DaqTask> findDaqTasksByParams(Integer pageNum, Integer pageSize, Integer stage) {
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
        return MSPage.newInstance(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDaqTaskSpiders(Long daqTaskId, List<Long> daqSpiderIds) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MSException::getDataNotFoundException);
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
    public MSPage<DaqTaskSpider> findDaqTaskSpidersByPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        return daqTaskSpiderService.findDaqTaskSpidersByPagination(daqTaskId, pageNum, pageSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDaqTaskKeywords(Long daqTaskId, List<String> keywords) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MSException::getDataNotFoundException);
        daqTaskKeywordService.saveDaqTaskKeywords(daqTask, keywords);
    }

    @Override
    public MSPage<DaqTaskKeyword> findDaqTaskKeywordsByPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        return daqTaskKeywordService.findDaqTaskKeywordsByPagination(daqTaskId, pageNum, pageSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindDaqTaskServers(Long daqTaskId, List<Long> serverIds) {
        daqTaskServerService.bindDaqTaskServers(daqTaskId, serverIds);
    }

    @Override
    public DaqTaskTimingDataVolumes findDaqTaskTimingDataVolumes(Long daqTaskId) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MSException::getDataNotFoundException);

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
                    tdv.setSpiderName(MSConstant.DaqSpider.CODE2NAME.get(spiderCode));
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
