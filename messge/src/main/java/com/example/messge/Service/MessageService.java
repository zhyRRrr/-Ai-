package com.example.messge.Service;

import com.example.messge.Repository.ChatRepository;
import com.example.messge.Repository.MessageRepository;
import com.example.messge.pojo.Chat;
import com.example.messge.pojo.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public Message saveMessage(Long chatId, String sender, String receiver, String content) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("聊天没有找到"));

        Message message = new Message();
        message.setChat(chat);
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public List<Message> getMessagesByChatId(Long chatId) {
        return messageRepository.findByChatId(chatId);
    }
}
