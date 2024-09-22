package com.example.messge.Controller;

import com.example.messge.Repository.UserService;
import com.example.messge.pojo.Result;
import com.example.messge.pojo.User;
import com.example.messge.utils.JwtUtil;
import com.example.messge.utils.Md5Util;
import com.example.messge.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        if (username != null && username.length() >= 5 && username.length() <= 16
                && password != null && password.length() >= 5 && password.length() <= 16) {
            //查询用户
            User u = userService.findByUsername(username);
            if (u != null) {
                return Result.error("用户名已存在");
            } else {
                //注册
                userService.register(username, password);
                return Result.success();
            }

        }
        return Result.error("用户名或密码格式不正确");
    }

    @PostMapping({"/login"})
    public Result<String> login(@Pattern(
            regexp = "^\\S{5,16}$"
    ) String username, @Pattern(
            regexp = "^\\S{5,16}$"
    ) String password) {
        User loginUser = this.userService.findByUsername(username);
        if (loginUser == null) {
            return Result.error("用户名错误");
        } else if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            Map<String, Object> claims = new HashMap();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            ValueOperations<String, String> operations = this.stringRedisTemplate.opsForValue();
            operations.set(token, token, 1, TimeUnit.HOURS);
            return Result.success(token);
        } else {
            return Result.error("密码错误");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userinfo(/*@RequestHeader("Authorization") String token*/) {
        /*//解析token
        Map<String, Object> claims = JwtUtil.parseToken(token);
        //获取用户名
        String username = (String) claims.get("username");*/
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        //查询用户
        User user = userService.findByUsername(username);
        //返回用户信息
        return Result.success(user);

    }

    @PutMapping("/update")
    public Result updateUser(@RequestBody User user) {
        // 检查邮箱格式
        if (user.getEmail() != null && !isValidEmail(user.getEmail())) {
            return Result.error("邮箱格式不正确");
        }

        userService.update(user);
        return Result.success();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailRegex);
    }



    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params,@RequestHeader("Authorization") String token) {
        //校验参数
        String oldpwd = params.get("old_pwd");
        String newpwd = params.get("new_pwd");
        String repwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldpwd) || !StringUtils.hasLength(newpwd) || !StringUtils.hasLength(repwd)) {
            return Result.error("参数不能为空");
        }

        //原密码是否正确
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUsername(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldpwd))) {
            return Result.error("原密码错误");
        }

        //新密码是否一致
        if (!newpwd.equals(repwd)) {
            return Result.error("两次密码不一致");
        }
        userService.updatePwd(newpwd);
        //删除redis存储的token
        stringRedisTemplate.opsForValue().getOperations().delete(token);
        return Result.success();
    }
}
