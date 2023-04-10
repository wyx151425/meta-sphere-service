CREATE TABLE IF NOT EXISTS `uuid`
(
    `id`               BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `create_at`        DATETIME COMMENT '数据入库时间',
    `task_name`        VARCHAR(64) COMMENT '所属数据采集任务的名称',
    `task_code`        CHAR(32) COMMENT '所属数据采集任务的编码',

    `source_id`        VARCHAR(32) COMMENT '源平台中的ID',
    `source_blog_id`   VARCHAR(32) COMMENT '源平台中的哈希ID',
    `source_origin_id` VARCHAR(32) COMMENT '转发文本在源平台中源文本的ID',
    `source_create_at` DATETIME COMMENT '源平台中的入库时间',
    `text`             TEXT COMMENT '文本内容',
    `reads_count`      INT COMMENT '阅读数',
    `likes_count`      INT COMMENT '点赞数',
    `comments_count`   INT COMMENT '评论数',
    `reposts_count`    INT COMMENT '转发数',

    `account_id`       VARCHAR(32) COMMENT '发布账号的ID',
    `account_name`     VARCHAR(64) COMMENT '发布账号的名称',
    `region_name`      VARCHAR(64) COMMENT '发布账号所在地区',

    `platform_name`    VARCHAR(32) COMMENT '源平台的名称',
    `platform_code`    VARCHAR(32) COMMENT '源平台的编码'
);

/**
  account
uid
location
follows
follow_list
fan_list
fans
gender
brief
label
verified
user_friend
all_content
history_weibo
credit
career
education
age
create_at

 */
