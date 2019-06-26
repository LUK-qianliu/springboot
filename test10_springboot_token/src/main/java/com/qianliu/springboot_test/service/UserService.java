package com.qianliu.springboot_test.service;

import com.qianliu.springboot_test.model.User3;
import org.springframework.stereotype.Service;


public interface UserService {
    /**
     * 通过用户名获取User3
     * @param username 用户名
     * @return
     */
    public User3 getUserByUserName(String username);
}
