DROP TABLE IF EXISTS user;

CREATE TABLE credit
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    user_id BIGINT(20) NOT NULL COMMENT '用户ID',
    credit BIGINT(20) NOT NULL DEFAULT 0 COMMENT '积分',
    updated_at TIMESTAMP NOT NULl DEFAULT current_time,
    created_at TIMESTAMP NOT NULL DEFAULT current_time,
    PRIMARY KEY (id)
);