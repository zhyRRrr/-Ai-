package com.example.messge.Repository;


import com.example.messge.pojo.User;

public interface UserService  {
    User findByUsername(String username);

    void register(String username, String password);
    //更新
    void update(User user);


    void updatePwd(String newPwd);
}
