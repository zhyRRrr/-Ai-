package com.example.messge.Controller;

import com.example.messge.Service.UserScoreService;
import com.example.messge.pojo.Result;
import com.example.messge.pojo.UserScore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-scores")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserScoreController {

    @Autowired
    private UserScoreService userScoreService;

    @PostMapping("/add")
    public Result addUserScore(@RequestBody UserScore userScore) {
        userScoreService.addUserScore(userScore);
        return Result.success();
    }

    @GetMapping("/") // 修改为不需要传入 userId
    public List<UserScore> getUserScores() {
        return userScoreService.getUserScores(); // 调用新的获取成绩的方法
    }

    @GetMapping("/all")
    public List<UserScore> getAllUserScores() {
        return userScoreService.getAllUserScores();
    }

}

