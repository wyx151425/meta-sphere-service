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

CREATE TABLE IF NOT EXISTS ms_daq_data_volume
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
