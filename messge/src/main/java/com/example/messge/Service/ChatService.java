package com.example.messge.Service;

import com.example.messge.Repository.ChatRepository;
import com.example.messge.pojo.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ScoreService scoreService; // 引入ScoreService

    private final AtomicLong sessionIdGenerator = new AtomicLong();

    public Chat createChat(String chatName, int trainId, int sceneId) {
        Chat chat = new Chat();
        chat.setChatName(chatName);
        chat.setTrainId(trainId);
        chat.setSceneId(sceneId);
        chat.setSessionId(sessionIdGenerator.incrementAndGet());
        chat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    public void deleteChat(Long chatId) {
        scoreService.deleteScoreByChatId(chatId); // 删除分数
        chatRepository.deleteById(chatId);
    }

    public Chat getChatById(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("聊天没有找到"));
    }

    public List<Chat> getAllChats() {
        return chatRepository.findAll(); // 从数据库获取所有聊天记录
    }


}
