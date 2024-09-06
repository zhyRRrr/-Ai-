package com.example.messge.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value; // 分数

    @OneToOne
    @JoinColumn(name = "chat_id", nullable = false, unique = true) // 确保一对一关系
    private Chat chat; // 关联的聊天记录
}
