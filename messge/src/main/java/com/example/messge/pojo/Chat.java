package com.example.messge.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "chats")
public class Chat {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)：这是另一个JPA注解，
//    表示主键的生成策略。GenerationType.IDENTITY策略意味着数据库会为每一条新记录生成一个自增的唯一标识符（ID）。
//    具体来说，这个策略依赖于底层数据库的自增机制来生成主键值，通常用于MySQL、PostgreSQL等数据库。
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatName; // 聊天会话的名称或标识
    private int trainId; // 训练难度 (1-3)
    private int sceneId; // 场景号 (1-4)
    private Long sessionId; // 会话ID，用于区分不同的聊天会话

    private LocalDateTime createdAt;
//    mappedBy = "chat"：表示Message类中的chat字段是该关系的拥有方。
//    这意味着Message类通过chat字段来维护和Chat类之间的关联关系。
//    mappedBy的值必须与Message类中的关联字段名称一致。
//    cascade = CascadeType.ALL: cascade属性指定了在Chat实体上
//    执行的所有操作（如持久化、删除、合并等）都会传递到它关联的Message实体。
//    这意味着当保存或删除Chat实体时，相关的Message实体会自动进行相应的操作。
//    例如，删除一个Chat时，它关联的所有Message也会被删除。
//    orphanRemoval = true：表示孤儿删除策略。
//    它意味着当Chat实体中的某个Message从messages列表中移除时，
//    这个Message会被自动从数据库中删除。
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();
}
