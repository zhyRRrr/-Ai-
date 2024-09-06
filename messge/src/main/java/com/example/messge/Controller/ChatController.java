package com.example.messge.Controller;

import com.example.messge.Service.ChatService;
import com.example.messge.Service.MessageService;
import com.example.messge.pojo.Chat;
import com.example.messge.pojo.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/create")
//    @RequestParam注解用于绑定HTTP请求参数到方法参数。
    public Chat createChat(@RequestParam String chatName,
                           @RequestParam int trainId,
                           @RequestParam int sceneId) {
        return chatService.createChat(chatName, trainId, sceneId);
    }

//    @PathVariable注解用于绑定URL路径变量到方法参数。
    @DeleteMapping("/{chatId}")
    public void deleteChat(@PathVariable Long chatId) {
        chatService.deleteChat(chatId);
    }

    @GetMapping("/{chatId}")
    public List<Message> getMessages(@PathVariable Long chatId) {
        return messageService.getMessagesByChatId(chatId);
    }

    @GetMapping("/")
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }


}
