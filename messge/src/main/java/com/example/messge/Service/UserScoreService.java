package com.example.messge.Service;

import com.example.messge.Repository.UserScoreRepository;
import com.example.messge.pojo.UserScore;

import com.example.messge.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserScoreService {

    @Autowired
    private UserScoreRepository userScoreRepository;

    public void addUserScore(UserScore userScore) {
        userScoreRepository.save(userScore);
    }

    public List<UserScore> getUserScores() {
        // 从 ThreadLocal 中获取用户 ID
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return userScoreRepository.findByUserId(userId);
    }

    public List<UserScore> getAllUserScores() {
        return userScoreRepository.findAll();
    }

}
