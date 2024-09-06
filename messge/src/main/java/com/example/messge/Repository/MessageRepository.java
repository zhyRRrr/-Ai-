package com.example.messge.Repository;

import com.example.messge.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//.自定义查询方法
//JpaRepository 还允许你通过方法命名规则自定义查询。例如，在 ChatRepository 中可以定义如下方法：
//List<Message> findByChatName(Long chatId);
//Spring Data JPA 会根据方法名自动生成查询逻辑，而无需显式编写 SQL 语句
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatId(Long chatId);
}
