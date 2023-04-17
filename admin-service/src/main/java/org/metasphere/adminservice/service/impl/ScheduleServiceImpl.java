package org.metasphere.adminservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.constant.MsConst;
import org.metasphere.adminservice.model.bo.daq.MongoWeibo;
import org.metasphere.adminservice.model.bo.daq.MongoWeiboUser;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskDataVolume;
import org.metasphere.adminservice.service.ScheduleService;
import org.metasphere.adminservice.service.daq.DaqTaskDataVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-12 9:59
 * @Modified By:
 */
@Slf4j
@Service(value = "scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private DaqTaskDataVolumeService daqTaskDataVolumeService;

    @Async
    @Scheduled(cron = "0 0/1 * * * ?")
    @Override
    public void scheduleCountDaqDataVolume() {
        List<DaqTaskDataVolume> dataVolumes = new ArrayList<>();
        LocalDateTime countedAt = LocalDateTime.now();

        List<String> taskCodes = redisTemplate.opsForList().range(MsConst.CacheKey.RUNNING_DAQ_TASKS_CODE, 0, -1);
        try {
            taskCodes.forEach(taskCode -> {
                String spidersCacheKey = String.format(MsConst.CacheKeyTemplate.DAQ_TASK_SPIDERS, taskCode);
                List<String> spiderCodes = redisTemplate.opsForList().range(spidersCacheKey, 0, -1);
                spiderCodes.forEach(spiderCode -> {
                    if ("weibo".equals(spiderCode)) {
                        Query query = Query.query(Criteria.where("task_code").is(taskCode));
                        long dataVolume1 = mongoTemplate.count(query, MongoWeibo.class, "weibo");

                        DaqTaskDataVolume daqTaskDataVolume1 = new DaqTaskDataVolume();
                        daqTaskDataVolume1.setTaskCode(taskCode);
                        daqTaskDataVolume1.setSpiderCode("weibo");
                        daqTaskDataVolume1.setCountedAt(countedAt);
                        daqTaskDataVolume1.setDataVolume(dataVolume1);
                        dataVolumes.add(daqTaskDataVolume1);

                        log.info("daq data volume of " + taskCode + "'s weibo spider is " + dataVolume1);

                        long dataVolume2 = mongoTemplate.count(query, MongoWeiboUser.class, "weibo_user");

                        DaqTaskDataVolume daqTaskDataVolume2 = new DaqTaskDataVolume();
                        daqTaskDataVolume2.setTaskCode(taskCode);
                        daqTaskDataVolume2.setSpiderCode("weibo_user");
                        daqTaskDataVolume2.setCountedAt(countedAt);
                        daqTaskDataVolume2.setDataVolume(dataVolume2);
                        dataVolumes.add(daqTaskDataVolume2);

                        log.info("daq data volume of " + taskCode + "'s weibo_user spider is " + dataVolume2);

                        long dataVolume3 = mongoTemplate.count(query, MongoWeibo.class, "weibo_like");

                        DaqTaskDataVolume daqTaskDataVolume3 = new DaqTaskDataVolume();
                        daqTaskDataVolume3.setTaskCode(taskCode);
                        daqTaskDataVolume3.setSpiderCode("weibo_like");
                        daqTaskDataVolume3.setCountedAt(countedAt);
                        daqTaskDataVolume3.setDataVolume(dataVolume3);
                        dataVolumes.add(daqTaskDataVolume3);

                        log.info("daq data volume of " + taskCode + "'s weibo_like spider is " + dataVolume3);

                        long dataVolume4 = mongoTemplate.count(query, MongoWeibo.class, "weibo_comment");

                        DaqTaskDataVolume daqTaskDataVolume4 = new DaqTaskDataVolume();
                        daqTaskDataVolume4.setTaskCode(taskCode);
                        daqTaskDataVolume4.setSpiderCode("weibo_comment");
                        daqTaskDataVolume4.setCountedAt(countedAt);
                        daqTaskDataVolume4.setDataVolume(dataVolume4);
                        dataVolumes.add(daqTaskDataVolume4);

                        log.info("daq data volume of " + taskCode + "'s weibo_comment spider is " + dataVolume4);

                        long dataVolume5 = mongoTemplate.count(query, MongoWeibo.class, "weibo_repost");

                        DaqTaskDataVolume daqTaskDataVolume5 = new DaqTaskDataVolume();
                        daqTaskDataVolume5.setTaskCode(taskCode);
                        daqTaskDataVolume5.setSpiderCode("weibo_repost");
                        daqTaskDataVolume5.setCountedAt(countedAt);
                        daqTaskDataVolume5.setDataVolume(dataVolume5);
                        dataVolumes.add(daqTaskDataVolume5);

                        log.info("daq data volume of " + taskCode + "'s weibo_repost spider is " + dataVolume5);
                    } else {
                        Query query = Query.query(Criteria.where("task_code").is(taskCode));
                        long dataVolume = mongoTemplate.count(query, MongoWeibo.class, spiderCode);

                        DaqTaskDataVolume daqTaskDataVolume = new DaqTaskDataVolume();
                        daqTaskDataVolume.setTaskCode(taskCode);
                        daqTaskDataVolume.setSpiderCode(spiderCode);
                        daqTaskDataVolume.setCountedAt(countedAt);
                        daqTaskDataVolume.setDataVolume(dataVolume);
                        dataVolumes.add(daqTaskDataVolume);

                        log.info("daq data volume of " + taskCode + "'s " + spiderCode + " spider is " + dataVolume);
                    }
                });
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        daqTaskDataVolumeService.saveDaqDataVolumes(dataVolumes);
    }
}
