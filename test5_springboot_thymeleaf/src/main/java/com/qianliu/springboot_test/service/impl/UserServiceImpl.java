package com.qianliu.springboot_test.service.impl;


import com.qianliu.springboot_test.dao.UserRepository;
import com.qianliu.springboot_test.entity.User2;
import com.qianliu.springboot_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /*
     * @author qianliu on 2019/5/14 16:38
     * @return java.util.List<com.qianliu.springboot_test.entity.User> 查询返回User的列表
     * @Discription:查询所有的数据
     */
    @Override
    public List<User2> getUserList() {
        return userRepository.findAll();
    }

    /*
     * @author qianliu on 2019/5/14 16:39
     * @param [id] 输入id
     * @return com.qianliu.springboot_test.entity.User 返回用户的User实体类
     * @Discription:通过id查询某一个数据
     */
    @Override
    public User2 findUserById(long id) {
        return userRepository.findById(id);
    }

    /*
     * @author qianliu on 2019/5/14 16:39
     * @param  User 输入一个User的实体类
     * @Discription:
     */
    @Override
    public void save(User2 user) {
        userRepository.save(user);
    }

    /*
     * @author qianliu on 2019/5/14 16:39
     * @param  User 输入一个User的实体类
     * @Discription:更新User实体类
     */
    @Override
    public void edit(User2 user) {
        userRepository.save(user);
    }

   /*
    * @param  输入一个id
    * @Discription:通过id删除用户
    */
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}


