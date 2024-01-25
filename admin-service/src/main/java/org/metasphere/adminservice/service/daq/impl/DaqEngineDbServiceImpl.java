package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.model.bo.daq.DaqEngineWeiboItem;
import org.metasphere.adminservice.service.daq.DaqEngineDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

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
    public void createDaqTaskDataTable(String tableName) {
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDaqEngineWeiboItem(String tableName, DaqEngineWeiboItem daqEngineWeiboItem) {
        LocalDateTime createAt = LocalDateTime.now();
        String sql = "INSERT INTO `" + tableName + "`(create_at, task_name, task_code, task_keyword, " +
                "source_id, source_blog_id, source_origin_id, source_create_at, " +
                "text, likes_count, comments_count, reposts_count, " +
                "account_id, account_name, region_name, platform_name, platform_code) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, createAt, daqEngineWeiboItem.getTaskName(), daqEngineWeiboItem.getTaskCode(), daqEngineWeiboItem.getTaskKeyword(),
                daqEngineWeiboItem.getSourceId(), daqEngineWeiboItem.getSourceBlogId(), daqEngineWeiboItem.getSourceOriginId(), daqEngineWeiboItem.getSourceCreateAt(),
                daqEngineWeiboItem.getText(), daqEngineWeiboItem.getLikesCount(), daqEngineWeiboItem.getCommentsCount(), daqEngineWeiboItem.getRepostsCount(),
                daqEngineWeiboItem.getAccountId(), daqEngineWeiboItem.getAccountName(), daqEngineWeiboItem.getRegionName(), daqEngineWeiboItem.getPlatformName(), daqEngineWeiboItem.getPlatformCode());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDaqEngineWeiboItems(String tableName, List<DaqEngineWeiboItem> daqEngineWeiboItems) {
        LocalDateTime createAt = LocalDateTime.now();
        String sql = "INSERT INTO `" + tableName + "`(create_at, task_name, task_code, task_keyword, " +
                "source_id, source_blog_id, source_origin_id, source_create_at, " +
                "text, reads_count, likes_count, comments_count, reposts_count, " +
                "account_id, account_name, region_name, platform_name, platform_code) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        daqEngineWeiboItems.forEach(weiboItem -> jdbcTemplate.update(sql, createAt, weiboItem.getTaskName(), weiboItem.getTaskCode(), weiboItem.getTaskKeyword(),
                weiboItem.getSourceId(), weiboItem.getSourceBlogId(), weiboItem.getSourceOriginId(), weiboItem.getSourceCreateAt(),
                weiboItem.getText(), weiboItem.getReadsCount(), weiboItem.getLikesCount(), weiboItem.getCommentsCount(), weiboItem.getRepostsCount(),
                weiboItem.getAccountId(), weiboItem.getAccountName(), weiboItem.getRegionName(), weiboItem.getPlatformName(), weiboItem.getPlatformCode()));
    }

    @Override
    public void findDaqTaskDataByPagination(String tableName, Integer pageNum, Integer pageSize) {

    }
}
