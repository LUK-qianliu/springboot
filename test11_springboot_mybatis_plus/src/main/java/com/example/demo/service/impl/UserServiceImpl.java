package com.example.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.mapper.UserDao;
import com.example.demo.po.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> queryUserById(int i) {
        return userDao.queryAllPerms(1);
    }
}
