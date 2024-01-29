package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.service.daq.DaqTaskStorageSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-09 21:55
 * @Modified By:
 */
@SpringBootTest
public class DaqTaskStorageSpaceServiceTests {

    @Autowired
    private DaqTaskStorageSpaceService daqTaskStorageSpaceService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testCreateDaqDataTable() {
        daqTaskStorageSpaceService.createDaqTaskStorageSpace("ad492f6d140e45ce881e7c023802a2bc");
    }

    @Test
    void testPreparesStatementSql() {
    }
}
