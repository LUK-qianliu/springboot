package com.qianliu.springboot_test.controller;
/*
 * @author qianliu on 2019/5/11 21:02
 * @Discription:
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  //表示这是一个controller控制器
//1.当控制器在类级别上添加@RequestMapping注解时，这个注解会应用到控制器的所有处理器方法上。
// 2.处理器方法上的@RequestMapping注解会对类级别上的@RequestMapping的声明进行补充。
@RequestMapping(value = "/controller")
public class HelloWorldController {
    //helloworld
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "HelloWorld";
    }
}
