package com.example.messge.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    声明与Chat的多对一关系
    @ManyToOne
//    关联列为chat_id
    @JoinColumn(name = "chat_id", nullable = false)
//    忽略序列化以避免无限递归。
    @JsonIgnore
    private Chat chat;

    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime timestamp;
}
