package com.example.messge.Controller;

import com.example.messge.Service.ChatService;
import com.example.messge.Service.ScoreService;
import com.example.messge.pojo.Chat;
import com.example.messge.pojo.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ScoreController {
    private final ScoreService scoreService;
    private final ChatService chatService; // 引入ChatService以获取聊天记录

    @PostMapping("/create")
    public Score createScore(@RequestParam int value, @RequestParam Long chatId) {
        Chat chat = chatService.getChatById(chatId); // 获取聊天对象
        return scoreService.createScore(value, chat); // 创建分数
    }

    @GetMapping("/{chatId}")
    public Score getScore(@PathVariable Long chatId) {
        return scoreService.getScoreByChatId(chatId); // 根据聊天ID获取分数
    }
}
