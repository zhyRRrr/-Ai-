package com.example.messge.Controller;

import com.example.messge.Service.MessageService;
import com.example.messge.pojo.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/send")
    public Message sendMessage(@RequestParam Long chatId,
                               @RequestParam String sender,
                               @RequestParam String receiver,
                               @RequestParam String content) {
        return messageService.saveMessage(chatId, sender, receiver, content);
    }
}
