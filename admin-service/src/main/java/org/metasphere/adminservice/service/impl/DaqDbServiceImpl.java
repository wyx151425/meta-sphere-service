package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.service.DaqDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-09 21:43
 * @Modified By:
 */
@Service(value = "daqDbService")
public class DaqDbServiceImpl implements DaqDbService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDaqDataTable(String tableName) {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `" + tableName + "` (" +
                "`id` BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "`object_id` CHAR(32)," +
                "`status` TINYINT," +
                "`create_at` DATETIME," +
                "`update_at` DATETIME" +
                ");");
    }
}
