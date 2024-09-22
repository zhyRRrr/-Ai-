package com.example.messge.Controller;

import com.example.messge.Service.ChatService;
import com.example.messge.Service.MessageService;
import com.example.messge.pojo.Chat;
import com.example.messge.pojo.Message;
import com.example.messge.pojo.Result;
import com.example.messge.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
                           @RequestParam int sceneId
                           ) {
       Map<String,Object>map = ThreadLocalUtil.get();// 添加 userId 参数
       Integer userId = (Integer) map.get("id");
        return chatService.createChat(chatName, trainId, sceneId, userId);
    }

//    @PathVariable注解用于绑定URL路径变量到方法参数。
    @DeleteMapping("/{chatId}")
    public Result deleteChat(@PathVariable Long chatId) {
        chatService.deleteChat(chatId);
        return Result.success();
    }

    @GetMapping("/{chatId}")
    public List<Message> getMessages(@PathVariable Long chatId) {
        return messageService.getMessagesByChatId(chatId);
    }

    @GetMapping("/") // 修改路径，表示这是一个针对特定用户的请求
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }
}
