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
                    Query query = Query.query(Criteria.where("task_code").is(taskCode));
                    long dataVolume = mongoTemplate.count(query, MsConst.DaqSpider.CODE2CLASS.get(spiderCode), spiderCode);

                    DaqTaskDataVolume daqTaskDataVolume = new DaqTaskDataVolume();
                    daqTaskDataVolume.setTaskCode(taskCode);
                    daqTaskDataVolume.setSpiderName(MsConst.DaqSpider.CODE2NAME.get(spiderCode));
                    daqTaskDataVolume.setSpiderCode(spiderCode);
                    daqTaskDataVolume.setCountedAt(countedAt);
                    daqTaskDataVolume.setDataVolume(dataVolume);
                    dataVolumes.add(daqTaskDataVolume);

                    log.info("daq data volume of " + taskCode + "'s " + spiderCode + " spider is " + dataVolume);
                });
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        daqTaskDataVolumeService.saveDaqDataVolumes(dataVolumes);
    }
}
