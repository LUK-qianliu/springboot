package com.qianliu.springboot_test.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data  //生成get，set方法
public class User2 {
    //@Id表示设置为主键
    // @GeneratedValue表示自增长
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //hibernate会默认hibernate_sequence的表是User2
    private long id;

    //nullable = false表示不可以为空, unique = true表示列表中唯一，不可重复的userName
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int age;
}
