package com.qianliu.springboot_test.controller;

import com.qianliu.springboot_test.entity.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UserController {

    @PostMapping("/addUser")
    public String addUser(@Valid @RequestBody User user){//@RequestBody接受一个json对象
        System.out.println(user);
        return "success";
    }


}
