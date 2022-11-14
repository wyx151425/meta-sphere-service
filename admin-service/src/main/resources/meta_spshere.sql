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
