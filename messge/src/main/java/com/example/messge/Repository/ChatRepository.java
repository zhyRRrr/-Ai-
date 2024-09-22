package com.example.messge.Repository;

import com.example.messge.pojo.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//CRUD 操作：
// 自动生成常用的增删改查（Create, Read, Update, Delete）方法。
// 例如，您可以直接使用 save(), findById(), findAll(), deleteById() 等方法，
// 而不需要手动实现这些功能。
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByUserId(Integer userId); // 根据 userId 查找聊天记录
}

