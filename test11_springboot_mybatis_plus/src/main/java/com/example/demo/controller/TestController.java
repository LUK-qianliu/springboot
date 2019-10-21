package com.example.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.po.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    UserService userService;

    @GetMapping("/testSelect")
    @ResponseBody
    public List<User> get(){
        return userService.queryUserById(1);
    }

    @GetMapping("/testPage")
    @ResponseBody
    public Page<User> get2(){
        return userService.selectPage(new Page<User>(1,2),
                new EntityWrapper<User>().between("user_id",1,3));
    }
    @GetMapping("/test")
    @ResponseBody
    public User get3(){
        return userService.selectById(1);
    }

}
