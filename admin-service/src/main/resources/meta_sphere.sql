CREATE TABLE IF NOT EXISTS ms_user
(
    `id`                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id`           CHAR(32),
    `status`              TINYINT,
    `create_at`           DATETIME,
    `update_at`           DATETIME,
    `email`               VARCHAR(32),
    `password`            VARCHAR(64),
    `name`                VARCHAR(64),
    `mobile_phone_number` CHAR(11)
);

CREATE TABLE IF NOT EXISTS ms_role
(
    `id`        BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id` CHAR(32),
    `status`    TINYINT,
    `create_at` DATETIME,
    `update_at` DATETIME,
    `code`      VARCHAR(64),
    `name`      VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS ms_permission
(
    `id`        BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id` CHAR(32),
    `status`    TINYINT,
    `create_at` DATETIME,
    `update_at` DATETIME,
    `code`      VARCHAR(64),
    `name`      VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS ms_user_role
(
    `id`        BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id` CHAR(32),
    `status`    TINYINT,
    `create_at` DATETIME,
    `update_at` DATETIME,
    `user_id`   BIGINT,
    `role_id`   BIGINT
);

CREATE TABLE IF NOT EXISTS ms_role_permission
(
    `id`            BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id`     CHAR(32),
    `status`        TINYINT,
    `create_at`     DATETIME,
    `update_at`     DATETIME,
    `role_id`       BIGINT,
    `permission_id` BIGINT
);

CREATE TABLE IF NOT EXISTS ms_server
(
    `id`         BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id`  CHAR(32),
    `status`     TINYINT,
    `create_at`  DATETIME,
    `update_at`  DATETIME,
    `ip_address` VARCHAR(15),
    `port`       INT,
    `name`       VARCHAR(32),
    `type`       INT
);

CREATE TABLE IF NOT EXISTS ms_daq_task
(
    `id`          BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id`   CHAR(32),
    `status`      TINYINT,
    `create_at`   DATETIME,
    `update_at`   DATETIME,
    `name`        VARCHAR(64),
    `code`        CHAR(36),
    `stage`       INT,
    `created_at`  DATETIME,
    `finished_at` DATETIME
);

CREATE TABLE IF NOT EXISTS ms_daq_spider
(
    `id`        BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id` CHAR(32),
    `status`    TINYINT,
    `create_at` DATETIME,
    `update_at` DATETIME,
    `name`      VARCHAR(32),
    `code`      VARCHAR(32),
    `cookies`   TEXT
);

CREATE TABLE IF NOT EXISTS ms_daq_task_spider
(
    `id`                BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id`         CHAR(32),
    `status`            TINYINT,
    `create_at`         DATETIME,
    `update_at`         DATETIME,
    `task_id`           BIGINT,
    `task_name`         VARCHAR(64),
    `task_code`         CHAR(36),
    `spider_id`         BIGINT,
    `spider_name`       VARCHAR(32),
    `spider_code`       VARCHAR(32),
    `spider_status`     INT,
    `server_ip_address` VARCHAR(15),
    `server_port`       INT,
    `job_id`            VARCHAR(40)
);

CREATE TABLE IF NOT EXISTS ms_daq_task_keyword
(
    `id`        BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id` CHAR(32),
    `status`    TINYINT,
    `create_at` DATETIME,
    `update_at` DATETIME,
    `task_id`   BIGINT,
    `task_name` VARCHAR(64),
    `task_code` CHAR(36),
    `keyword`   VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS ms_daq_task_server
(
    `id`                BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id`         CHAR(32),
    `status`            TINYINT,
    `create_at`         DATETIME,
    `update_at`         DATETIME,
    `task_id`           BIGINT,
    `server_id`         BIGINT,
    `server_ip_address` VARCHAR(15),
    `server_port`       INT
);

CREATE TABLE IF NOT EXISTS ms_daq_task_data_volume
(
    `id`          BIGINT PRIMARY KEY AUTO_INCREMENT,
    `object_id`   CHAR(32),
    `status`      TINYINT,
    `create_at`   DATETIME,
    `update_at`   DATETIME,
    `task_id`     BIGINT,
    `task_name`   VARCHAR(64),
    `task_code`   CHAR(36),
    `spider_name` VARCHAR(32),
    `spider_code` VARCHAR(32),
    `counted_at`  DATETIME,
    `data_volume` BIGINT
);

CREATE TABLE IF NOT EXISTS `ms_weibo_item`
(
    `id`               BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `object_id`        CHAR(32) COMMENT '分布式ID',
    `status`           TINYINT COMMENT '数据状态',
    `create_at`        DATETIME COMMENT '数据入库时间',
    `update_at`        DATETIME COMMENT '数据最后一次更新时间',
    `task_name`        VARCHAR(64) COMMENT '所属数据采集任务的名称',
    `task_code`        CHAR(32) COMMENT '所属数据采集任务的编码',
    `task_keyword`     VARCHAR(128) COMMENT '任务关键词',

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

CREATE TABLE IF NOT EXISTS `ms_weibo_user_item`
(
    `id`              BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `object_id`       CHAR(32) COMMENT '分布式ID',
    `status`          TINYINT COMMENT '数据状态',
    `create_at`       DATETIME COMMENT '数据入库时间',
    `update_at`       DATETIME COMMENT '数据最后一次更新时间',

    `source_id`       VARCHAR(32) COMMENT '源平台中的ID',
    `screen_name`     VARCHAR(64) COMMENT '微博昵称',
    `gender`          TINYINT COMMENT '性别(1男0女)',
    `location`        VARCHAR(64) COMMENT '所在地区',
    `description`     VARCHAR(128) COMMENT '简介',
    `weibos_count`    INT COMMENT '微博数',
    `follows_count`   INT COMMENT '关注数',
    `followers_count` INT COMMENT '粉丝数',
    `profile_url`     VARCHAR(64) COMMENT '微博主页URL'
);
