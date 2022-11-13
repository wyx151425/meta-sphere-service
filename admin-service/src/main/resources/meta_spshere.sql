CREATE TABLE ms_user
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    object_id CHAR(32),
    status TINYINT,
    create_at DATETIME,
    update_at DATETIME,
    email VARCHAR(32),
    password VARCHAR(64),
    name VARCHAR(64),
    mobile_phone_number CHAR(11)
);
