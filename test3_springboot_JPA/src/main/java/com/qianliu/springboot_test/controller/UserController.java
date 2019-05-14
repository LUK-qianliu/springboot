package com.qianliu.springboot_test.controller;

import com.qianliu.springboot_test.entity.UserEntity;
import com.qianliu.springboot_test.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserJPA userJPA;

    /*
     * @author qianliu on 2019/5/12 21:36
     * @return List<UserEntity> 返回UserEntity实体的list<UserEntity>
     * @Discription:查询所有数据
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserEntity> getAll() {
        System.out.println("list");
        return userJPA.findAll();
    }

    /*
     * @author qianliu on 2019/5/12 21:37
     * @param entity 传入一个对象UserEntity
     * @return UserEntity 返回这个UserEntity实体
     * @Discription:更新、添加UserEntity实体
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public UserEntity save() {
        UserEntity entity = new UserEntity("user1", 22, "123@qq.com");
        return userJPA.save(entity);
    }

    /**
     * 通过id删除用户方法
     *
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public List<UserEntity> delete(@PathVariable Long id) {
        userJPA.deleteById(id);
        return userJPA.findAll();
    }
}
