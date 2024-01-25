package org.metasphere.adminservice.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.service.daq.DaqTaskService;
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
    private DaqTaskService daqTaskService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testImportDaqTaskAcquiredData() {
        daqTaskService.importDaqTaskAcquiredData(4L);
    }
}
