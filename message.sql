CREATE TABLE chats (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    chat_name VARCHAR(255) NOT NULL,
    train_id TINYINT NOT NULL, -- 训练难度 (1-3)
    scene_id TINYINT NOT NULL, -- 场景号 (1-4)
    session_id BIGINT NOT NULL, -- 会话ID
    user_id INT UNSIGNED NOT NULL, -- 用户ID，外键关联到用户表
    created_at DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE -- 外键关联到用户表
);

CREATE TABLE messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    chat_id BIGINT NOT NULL, -- 关联到具体聊天记录的ID
    sender VARCHAR(255) NOT NULL,
    receiver VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    timestamp DATETIME NOT NULL,
    FOREIGN KEY (chat_id) REFERENCES chats(id) ON DELETE CASCADE
);

CREATE TABLE scores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,   -- 自增的主键
    value INT NOT NULL,                      -- 分数值，不能为空
    chat_id BIGINT NOT NULL,                 -- 聊天记录的ID，不能为空
    FOREIGN KEY (chat_id) REFERENCES chats(id) ON DELETE CASCADE -- 外键关联到聊天表
);

CREATE TABLE scene_favorites (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    train_id INT NOT NULL,   -- 训练难度
    scene_id INT NOT NULL,   -- 场景号
    scene_name VARCHAR(255) NOT NULL,  -- 场景名
    user_id INT UNSIGNED NOT NULL,      -- 用户ID，外键关联到用户表
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE -- 外键关联到用户表
);

-- 用户表
CREATE TABLE user (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(32) COMMENT '密码',
    nickname VARCHAR(10) DEFAULT '' COMMENT '昵称',
    email VARCHAR(128) DEFAULT '' COMMENT '邮箱',
    user_pic VARCHAR(128) DEFAULT '' COMMENT '头像',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '修改时间'
) COMMENT '用户表';

CREATE TABLE user_scores (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    user_id INT UNSIGNED NOT NULL COMMENT '用户ID',
    score CHAR(1) NOT NULL COMMENT '成绩，A、B、C、D、E',
    assessment_date DATETIME NOT NULL COMMENT '考核日期',
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
) COMMENT '用户考核成绩表';
