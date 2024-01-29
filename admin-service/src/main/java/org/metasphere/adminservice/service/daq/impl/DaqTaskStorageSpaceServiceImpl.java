package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.model.bo.daq.*;
import org.metasphere.adminservice.service.daq.DaqTaskStorageSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-09 21:43
 * @Modified By:
 */
@Service(value = "daqEngineDbService")
public class DaqTaskStorageSpaceServiceImpl implements DaqTaskStorageSpaceService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void createDaqTaskStorageSpace(String daqTaskCode) {
        createMongoWeiboDataTable(daqTaskCode);
        createMongoWeiboLikeDataTable(daqTaskCode);
        createMongoWeiboCommentDataTable(daqTaskCode);
        createMongoWeiboRepostDataTable(daqTaskCode);
        createMongoWeiboUserDataTable(daqTaskCode);
    }

    private void createMongoWeiboDataTable(String daqTaskCode) {
        String tableName = "weibo_" + daqTaskCode;
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `" + tableName + "`(" +
                "`id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '入库主键'," +
                "`mid` VARCHAR(32) COMMENT '源微博的ID'," +
                "`hash_mid` VARCHAR(32) COMMENT '源微博的哈希ID'," +
                "`task_code` CHAR(32) COMMENT '所属数据采集任务的编码'," +
                "`task_keyword` VARCHAR(128) COMMENT '所属关键词'," +
                "`created_at` DATETIME COMMENT '源微博的创建时间'," +

                "`text` TEXT COMMENT '已格式化的文本内容'," +
                "`text_raw` TEXT COMMENT '未格式化的文本内容'," +
                "`text_length` INT COMMENT '文本长度'," +
                "`likes_count` INT COMMENT '点赞数'," +
                "`comments_count` INT COMMENT '评论数'," +
                "`reposts_count` INT COMMENT '转发数'," +
                "`weibo_url` TEXT COMMENT '源微博的URL'," +

                "`uid` VARCHAR(32) COMMENT '微博所属用户的ID'," +
                "`user_screen_name` VARCHAR(64) COMMENT '微博所属用户的昵称'," +
                "`region_name` VARCHAR(64) COMMENT '微博所属用户的所在地区'," +
                "`source` TEXT COMMENT '源微博的发布设备'" +
                ");");
    }

    private void createMongoWeiboLikeDataTable(String daqTaskCode) {
        String tableName = "weibo_like_" + daqTaskCode;
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `" + tableName + "`(" +
                "`id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '入库主键'," +
                "`mid` VARCHAR(32) COMMENT '源微博的ID'," +
                "`task_code` CHAR(32) COMMENT '所属数据采集任务的编码'," +
                "`task_keyword` VARCHAR(128) COMMENT '所属关键词'," +
                "`like` INT COMMENT '点赞标识'," +
                "`uid` VARCHAR(32) COMMENT '微博所属用户的ID'" +
                ");");
    }

    private void createMongoWeiboCommentDataTable(String daqTaskCode) {
        String tableName = "weibo_comment_" + daqTaskCode;
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `" + tableName + "`(" +
                "`id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '入库主键'," +
                "`mid` VARCHAR(32) COMMENT '源微博的ID'," +
                "`cid` VARCHAR(32) COMMENT '源评论的ID'," +
                "`task_code` CHAR(32) COMMENT '所属数据采集任务的编码'," +
                "`task_keyword` VARCHAR(128) COMMENT '所属关键词'," +
                "`created_at` DATETIME COMMENT '源评论的创建时间'," +

                "`text` TEXT COMMENT '已格式化的文本内容'," +
                "`text_raw` TEXT COMMENT '未格式化的文本内容'," +
                "`likes_count` INT COMMENT '评论点赞数'," +

                "`uid` VARCHAR(32) COMMENT '微博所属用户的ID'," +
                "`source` TEXT COMMENT '评论的发布设备'" +
                ");");
    }

    private void createMongoWeiboRepostDataTable(String daqTaskCode) {
        String tableName = "weibo_repost_" + daqTaskCode;
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `" + tableName + "`(" +
                "`id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '入库主键'," +
                "`oid` VARCHAR(32) COMMENT '源微博的ID'," +
                "`mid` VARCHAR(32) COMMENT '转发微博的ID'," +
                "`hash_mid` VARCHAR(32) COMMENT '转发微博的哈希ID'," +
                "`task_code` CHAR(32) COMMENT '所属数据采集任务的编码'," +
                "`task_keyword` VARCHAR(128) COMMENT '所属关键词'," +
                "`created_at` DATETIME COMMENT '转发微博的创建时间'," +

                "`text` TEXT COMMENT '已格式化的文本内容'," +
                "`text_raw` TEXT COMMENT '未格式化的文本内容'," +
                "`likes_count` INT COMMENT '点赞数'," +
                "`comments_count` INT COMMENT '评论数'," +
                "`reposts_count` INT COMMENT '转发数'," +
                "`weibo_url` TEXT COMMENT '转发微博的URL'," +

                "`uid` VARCHAR(32) COMMENT '转发所属用户的ID'," +
                "`user_screen_name` VARCHAR(64) COMMENT '转发所属用户的昵称'," +
                "`region_name` VARCHAR(64) COMMENT '转发所属用户的所在地区'," +
                "`source` TEXT COMMENT '转发微博的发布设备'" +
                ");");
    }

    private void createMongoWeiboUserDataTable(String daqTaskCode) {
        String tableName = "weibo_user_" + daqTaskCode;
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `" + tableName + "`(" +
                "`id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '入库主键'," +
                "`task_code` CHAR(32) COMMENT '所属数据采集任务的编码'," +
                "`task_keyword` VARCHAR(128) COMMENT '所属关键词'," +

                "`uid` VARCHAR(32) COMMENT '用户ID'," +
                "`created_at` DATETIME COMMENT '账户创建时间'," +
                "`screen_name` VARCHAR(64) COMMENT '昵称'," +
                "`gender` CHAR(4) COMMENT '性别'," +
                "`birthday` VARCHAR(32) COMMENT '生日'," +
                "`constellation` VARCHAR(32) COMMENT '星座'," +
                "`province` VARCHAR(32) COMMENT '所在省'," +
                "`city` VARCHAR(32) COMMENT '所在城市'," +
                "`description` VARCHAR(255) COMMENT '简介'," +
                "`profile_url` TEXT COMMENT '个人主页URL'," +
                "`verified` INT COMMENT '认证标识'," +
                "`credit_level` VARCHAR(32) COMMENT '信用等级'," +

                "`followers_count` INT COMMENT '粉丝的数量'," +
                "`followers_count_str` VARCHAR(32) COMMENT '粉丝数量字符串'," +
                "`follows_count` INT COMMENT '关注用户的数量'," +
                "`follows_count_str` VARCHAR(32) COMMENT '关注用户数量字符串'," +
                "`weibos_count` INT COMMENT '微博的数量'," +
                "`weibos_count_str` VARCHAR(32) COMMENT '微博数量字符串'" +
                ");");
    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void saveDaqEngineWeiboItem(String tableName, DaqEngineWeiboItem daqEngineWeiboItem) {
//        LocalDateTime createAt = LocalDateTime.now();
//        String sql = "INSERT INTO `" + tableName + "`(create_at, task_name, task_code, task_keyword, " +
//                "source_id, source_blog_id, source_origin_id, source_create_at, " +
//                "text, reads_count, likes_count, comments_count, reposts_count, " +
//                "account_id, account_name, region_name, platform_name, platform_code) " +
//                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, createAt, daqEngineWeiboItem.getTaskName(), daqEngineWeiboItem.getTaskCode(), daqEngineWeiboItem.getTaskKeyword(),
//                daqEngineWeiboItem.getSourceId(), daqEngineWeiboItem.getSourceBlogId(), daqEngineWeiboItem.getSourceOriginId(), daqEngineWeiboItem.getSourceCreateAt(),
//                daqEngineWeiboItem.getText(), daqEngineWeiboItem.getReadsCount(), daqEngineWeiboItem.getLikesCount(), daqEngineWeiboItem.getCommentsCount(), daqEngineWeiboItem.getRepostsCount(),
//                daqEngineWeiboItem.getAccountId(), daqEngineWeiboItem.getAccountName(), daqEngineWeiboItem.getRegionName(), daqEngineWeiboItem.getPlatformName(), daqEngineWeiboItem.getPlatformCode());
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void saveDaqEngineWeiboItems(String tableName, List<DaqEngineWeiboItem> daqEngineWeiboItems) {
//        LocalDateTime createAt = LocalDateTime.now();
//        String sql = "INSERT INTO `" + tableName + "`(create_at, task_name, task_code, task_keyword, " +
//                "source_id, source_blog_id, source_origin_id, source_create_at, " +
//                "text, reads_count, likes_count, comments_count, reposts_count, " +
//                "account_id, account_name, region_name, platform_name, platform_code) " +
//                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        daqEngineWeiboItems.forEach(weiboItem -> jdbcTemplate.update(sql, createAt, weiboItem.getTaskName(), weiboItem.getTaskCode(), weiboItem.getTaskKeyword(),
//                weiboItem.getSourceId(), weiboItem.getSourceBlogId(), weiboItem.getSourceOriginId(), weiboItem.getSourceCreateAt(),
//                weiboItem.getText(), weiboItem.getReadsCount(), weiboItem.getLikesCount(), weiboItem.getCommentsCount(), weiboItem.getRepostsCount(),
//                weiboItem.getAccountId(), weiboItem.getAccountName(), weiboItem.getRegionName(), weiboItem.getPlatformName(), weiboItem.getPlatformCode()));
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void findDaqTaskDataByPagination(String tableName, Integer pageNum, Integer pageSize) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void saveMongoWeibos(String daqTaskCode, List<MongoWeibo> mongoWeibos) {
        String tableName = "weibo_" + daqTaskCode;
        String sql = "INSERT INTO `" + tableName + "`(mid, hash_mid, task_code, task_keyword, created_at, " +
                "text, text_raw, text_length, likes_count, comments_count, reposts_count, weibo_url, " +
                "uid, user_screen_name, region_name, `source`) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        mongoWeibos.forEach(weibo -> jdbcTemplate.update(sql,
                weibo.getMid(), weibo.getHashMid(), weibo.getTaskCode(), weibo.getTaskKeyword(), weibo.getCreatedAt(),
                weibo.getText(), weibo.getTextRaw(), weibo.getTextLength(), weibo.getLikesCount(), weibo.getCommentsCount(), weibo.getRepostsCount(), weibo.getWeiboUrl(),
                weibo.getUid(), weibo.getUserScreenName(), weibo.getRegionName(), weibo.getSource()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void saveMongoWeiboLikes(String daqTaskCode, List<MongoWeiboLike> mongoWeiboLikes) {
        String tableName = "weibo_like_" + daqTaskCode;
        String sql = "INSERT INTO `" + tableName + "`(mid, task_code, task_keyword, `like`, uid) VALUES(?, ?, ?, ?, ?)";
        mongoWeiboLikes.forEach(weiboLike -> jdbcTemplate.update(sql,
                weiboLike.getMid(), weiboLike.getTaskCode(), weiboLike.getTaskKeyword(), weiboLike.getLike(), weiboLike.getUid()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void saveMongoWeiboComments(String daqTaskCode, List<MongoWeiboComment> mongoWeiboComments) {
        String tableName = "weibo_comment_" + daqTaskCode;
        String sql = "INSERT INTO `" + tableName + "`(mid, cid, task_code, task_keyword, created_at, text, text_raw, likes_count, uid, `source`) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        mongoWeiboComments.forEach(weiboComment -> jdbcTemplate.update(sql,
                weiboComment.getMid(), weiboComment.getCid(), weiboComment.getTaskCode(), weiboComment.getTaskKeyword(), weiboComment.getCreatedAt(),
                weiboComment.getText(), weiboComment.getTextRaw(), weiboComment.getLikesCount(), weiboComment.getUid(), weiboComment.getSource()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void saveMongoWeiboReposts(String daqTaskCode, List<MongoWeiboRepost> mongoWeiboReposts) {
        String tableName = "weibo_repost_" + daqTaskCode;
        String sql = "INSERT INTO `" + tableName + "`(oid, mid, hash_mid, task_code, task_keyword, created_at, " +
                "text, text_raw, likes_count, comments_count, reposts_count, weibo_url, uid, user_screen_name, region_name, `source`) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        mongoWeiboReposts.forEach(weiboRepost -> jdbcTemplate.update(sql,
                weiboRepost.getOid(), weiboRepost.getMid(), weiboRepost.getHashMid(), weiboRepost.getTaskCode(), weiboRepost.getTaskKeyword(), weiboRepost.getCreatedAt(),
                weiboRepost.getText(), weiboRepost.getTextRaw(), weiboRepost.getLikesCount(), weiboRepost.getCommentsCount(), weiboRepost.getRepostsCount(), weiboRepost.getWeiboUrl(),
                weiboRepost.getUid(), weiboRepost.getUserScreenName(), weiboRepost.getRegionName(), weiboRepost.getSource()
        ));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void saveMongoWeiboUsers(String daqTaskCode, List<MongoWeiboUser> mongoWeiboUsers) {
        String tableName = "weibo_user_" + daqTaskCode;
        String sql = "INSERT INTO `" + tableName + "`(task_code, task_keyword, uid, created_at, screen_name, gender, " +
                "birthday, constellation, province, city, description, profile_url, verified, credit_level, " +
                "followers_count, followers_count_str, follows_count, follows_count_str, weibos_count, weibos_count_str) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        mongoWeiboUsers.forEach(weiboUser -> jdbcTemplate.update(sql,
                weiboUser.getTaskCode(), weiboUser.getTaskKeyword(), weiboUser.getUid(), weiboUser.getCreatedAt(), weiboUser.getScreenName(), weiboUser.getGender(),
                weiboUser.getBirthday(), weiboUser.getConstellation(), weiboUser.getProvince(), weiboUser.getCity(), weiboUser.getDescription(), weiboUser.getProfileUrl(), weiboUser.getVerified(), weiboUser.getCreditLevel(),
                weiboUser.getFollowersCount(), weiboUser.getFollowersCountStr(), weiboUser.getFollowsCount(), weiboUser.getFollowsCountStr(), weiboUser.getWeibosCount(), weiboUser.getWeibosCountStr()));
    }
}
