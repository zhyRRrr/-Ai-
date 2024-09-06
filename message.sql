CREATE TABLE chats (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    chat_name VARCHAR(255) NOT NULL,
    train_id TINYINT NOT NULL, -- 训练难度 (1-3)
    scene_id TINYINT NOT NULL, -- 场景号 (1-4)
    session_id BIGINT NOT NULL, -- 会话ID
    created_at DATETIME NOT NULL
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
    scene_name VARCHAR(255) NOT NULL  -- 场景名
);




