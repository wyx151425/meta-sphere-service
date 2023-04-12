package org.metasphere.adminservice.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-11 21:42
 * @Modified By:
 */
public interface ScheduleService {

    @Async
    @Scheduled(cron = "0/30 * * * * ?")
    void scheduleCountDaqDataVolume();
}
