package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.service.daq.DaqEngineDbService;
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
@Service(value = "daqEngineDbService")
public class DaqEngineDbServiceImpl implements DaqEngineDbService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDaqDataTable(String tableName) {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `" + tableName + "`(" +
                "`id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键'," +
                "`create_at` DATETIME COMMENT '数据入库时间'," +
                "`task_name` VARCHAR(64) COMMENT '所属数据采集任务的名称'," +
                "`task_code` CHAR(32) COMMENT '所属数据采集任务的编码'," +
                "`task_keyword` VARCHAR(128) COMMENT '任务关键词'," +

                "`source_id` VARCHAR(32) COMMENT '源平台中的ID'," +
                "`source_blog_id` VARCHAR(32) COMMENT '源平台中的哈希ID'," +
                "`source_origin_id` VARCHAR(32) COMMENT '转发文本在源平台中源文本的ID'," +
                "`source_create_at` DATETIME COMMENT '源平台中的入库时间'," +
                "`text` TEXT COMMENT '文本内容'," +
                "`reads_count` INT COMMENT '阅读数'," +
                "`likes_count` INT COMMENT '点赞数'," +
                "`comments_count` INT COMMENT '评论数'," +
                "`reposts_count` INT COMMENT '转发数'," +

                "`account_id` VARCHAR(32) COMMENT '发布账号的ID'," +
                "`account_name` VARCHAR(64) COMMENT '发布账号的名称'," +
                "`region_name` VARCHAR(64) COMMENT '发布账号所在地区'," +

                "`platform_name` VARCHAR(32) COMMENT '源平台的名称'," +
                "`platform_code` VARCHAR(32) COMMENT '源平台的编码'" +
                ");");
    }
}
