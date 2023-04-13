package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.service.daq.DaqEngineDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-09 21:55
 * @Modified By:
 */
@SpringBootTest
public class DaqEngineDbServiceTests {

    @Autowired
    private DaqEngineDbService daqEngineDbService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testCreateDaqDataTable() {
        String code = UUID.randomUUID().toString();
        daqEngineDbService.createDaqTaskDataTable(code);
    }

    @Test
    void testPreparesStatementSql() {
    }
}
