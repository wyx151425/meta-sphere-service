package org.metasphere.adminservice.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.service.daq.impl.DaqTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-24 21:11
 * @Modified By:
 */
@Slf4j
@SpringBootTest
public class DaqTaskServiceTests {

    @Autowired
    private DaqTaskServiceImpl daqTaskService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testImportDaqTaskAcquiredData() {
        daqTaskService.startImportDaqTaskAcquiredData(4L);
    }

    @Test
    void testHandleAcquiredWeiboData() {
//        daqTaskService.handleAcquiredWeiboData("ad492f6d140e45ce881e7c023802a2bc");
//        daqTaskService.handleAcquiredWeiboLikeData("ad492f6d140e45ce881e7c023802a2bc");
//        daqTaskService.handleAcquiredWeiboCommentData("ad492f6d140e45ce881e7c023802a2bc");
//        daqTaskService.handleAcquiredWeiboRepostData("ad492f6d140e45ce881e7c023802a2bc");
//        daqTaskService.handleAcquiredWeiboUserData("ad492f6d140e45ce881e7c023802a2bc");
    }
}
