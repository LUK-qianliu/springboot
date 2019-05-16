package com.qianliu.springboot_test.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping
@RestController
public class Controller {
    //@PathVariable("name"):获取url中的name属性，将其赋值给String name
    @GetMapping(value = "/filter/{name}")
    public String sayHello(@PathVariable("name") String name, HttpSession session){
        session.setAttribute("name",name);
        return name+",hello!";
    }

    //拦截器
    @GetMapping(value = "/interceptor/{name}")
    public String login(@PathVariable("name") String name){
        return name+"来了。。。";
    }
}
