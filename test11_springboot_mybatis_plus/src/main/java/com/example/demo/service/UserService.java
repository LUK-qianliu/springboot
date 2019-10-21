package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.po.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> queryUserById(int i);
}
