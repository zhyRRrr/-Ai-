package com.example.messge.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "user")
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    private String password;//密码

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称

    @NotEmpty
    @Email(message = "邮箱格式不正确")
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间

    // 添加无参构造函数
    @JsonCreator
    public User(@JsonProperty("id") @NotNull Integer id,
                @JsonProperty("username") String username,
                @JsonProperty("nickname") String nickname,
                @JsonProperty("email") String email) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
    }
}
