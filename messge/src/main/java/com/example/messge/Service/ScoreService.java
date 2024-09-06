package com.example.messge.Service;

import com.example.messge.Repository.ScoreRepository;
import com.example.messge.pojo.Chat;
import com.example.messge.pojo.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;

    public Score createScore(int value, Chat chat) {
        Score score = new Score();
        score.setValue(value);
        score.setChat(chat);
        return scoreRepository.save(score);
    }

    public Score getScoreByChatId(Long chatId) {
        return scoreRepository.findByChatId(chatId);
    }

    public void deleteScoreByChatId(Long chatId) {
        Score score = scoreRepository.findByChatId(chatId);
        if (score != null) {
            scoreRepository.delete(score);
        }
    }
}
