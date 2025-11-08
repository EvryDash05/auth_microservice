CREATE TABLE IF NOT EXISTS users
(
    user_id        INT PRIMARY KEY AUTO_INCREMENT,
    status         TINYINT(1)   NOT NULL DEFAULT 1,
    dni            VARCHAR(8) NOT NULL UNIQUE,
    password       VARCHAR(250) NOT NULL,
    created_by     VARCHAR(255) NOT NULL DEFAULT 'SYSTEM',
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
);

CREATE TABLE IF NOT EXISTS audit_logs
(
    log_id         INT PRIMARY KEY AUTO_INCREMENT,
    user_id        INT          NOT NULL,
    action         VARCHAR(100) NOT NULL UNIQUE,
    details        TEXT         NOT NULL,
    created_by     VARCHAR(255) NOT NULL DEFAULT 'SYSTEM',
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);
