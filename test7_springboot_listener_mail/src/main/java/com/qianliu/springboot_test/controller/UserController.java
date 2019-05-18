package com.qianliu.springboot_test.controller;

import com.qianliu.springboot_test.entity.User;
import com.qianliu.springboot_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //用户业务逻辑实现
    @Autowired
    private UserService userService;

    /**
     * 注册控制方法
     * @param user 用户对象
     * @return
     */
    @GetMapping(value = "/register")
    public String register(User user){
        userService.register(user);
        return "注册成功！";
    }
}
