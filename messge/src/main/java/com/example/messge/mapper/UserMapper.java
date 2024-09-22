package com.example.messge.mapper;

import com.example.messge.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    //添加用户
    @Insert("insert into user(username,password,create_time,update_time)" + " values(#{username},#{password},now(),now())")
    void add(String username, String password);

    //根据用户名查询用户
    //使用 @Results 注解来手动指定数据库列与实体类属性的映射关系：
    @Select("select * from user where username=#{username}")
    @Results({
            @Result(property = "userPic", column = "user_pic"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    User findUsername(String username);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);


    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
}
