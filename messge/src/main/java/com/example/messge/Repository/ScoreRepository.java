package com.example.messge.Repository;

import com.example.messge.pojo.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findByChatId(Long chatId); // 根据聊天ID查找分数
}

