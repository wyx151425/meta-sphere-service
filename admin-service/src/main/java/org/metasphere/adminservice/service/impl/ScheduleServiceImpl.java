package org.metasphere.adminservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.constant.MsConst;
import org.metasphere.adminservice.model.daq.WeiboItem;
import org.metasphere.adminservice.model.pojo.daq.DaqDataVolume;
import org.metasphere.adminservice.service.ScheduleService;
import org.metasphere.adminservice.service.daq.DaqDataVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
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
    private RedisTemplate redisTemplate;

    @Autowired
    private DaqDataVolumeService daqDataVolumeService;

    @Async
    @Scheduled(cron = "0 0/1 * * * ?")
    @Override
    public void scheduleCountDaqDataVolume() {
        List<DaqDataVolume> dataVolumes = new ArrayList<>();
        LocalDateTime countedAt = LocalDateTime.now();

        List<String> taskCodes = redisTemplate.opsForList().range(MsConst.CacheKey.RUNNING_DAQ_TASKS_CODE, 0, -1);
        try {
            taskCodes.forEach(taskCode -> {
                String spidersCacheKey = String.format(MsConst.CacheKeyTemplate.DAQ_TASK_SPIDERS, taskCode);
                List<String> spiderCodes = redisTemplate.opsForList().range(spidersCacheKey, 0, -1);
                spiderCodes.forEach(spiderCode -> {
                    Query query = Query.query(Criteria.where("task_code").is(taskCode).and("spider_code").is(spiderCode));
                    long dataVolume = mongoTemplate.count(query, WeiboItem.class);

                    DaqDataVolume daqDataVolume = new DaqDataVolume();
                    daqDataVolume.setTaskCode(taskCode);
                    daqDataVolume.setSpiderCode(spiderCode);
                    daqDataVolume.setCountedAt(countedAt);
                    daqDataVolume.setDataVolume(dataVolume);
                    dataVolumes.add(daqDataVolume);

                    log.info("daq data volume of " + taskCode + "'s " + spiderCode + " spider is " + dataVolume);
                });
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        daqDataVolumeService.saveDaqDataVolumes(dataVolumes);
    }
}
