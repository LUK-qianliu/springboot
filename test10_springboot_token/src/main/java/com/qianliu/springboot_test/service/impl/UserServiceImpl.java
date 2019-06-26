package com.qianliu.springboot_test.service.impl;

import com.qianliu.springboot_test.dao.UserRepository;
import com.qianliu.springboot_test.model.User3;
import com.qianliu.springboot_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    /**
     * 用户名查找用户
     * @param username 用户名
     * @return
     */
    @Override
    public User3 getUserByUserName(String username) {
        return userRepository.findByUsernameEquals(username);
    }
}
