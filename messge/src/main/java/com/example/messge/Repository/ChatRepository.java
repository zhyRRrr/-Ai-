package com.example.messge.Repository;

import com.example.messge.pojo.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD 操作：
// 自动生成常用的增删改查（Create, Read, Update, Delete）方法。
// 例如，您可以直接使用 save(), findById(), findAll(), deleteById() 等方法，
// 而不需要手动实现这些功能。
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
