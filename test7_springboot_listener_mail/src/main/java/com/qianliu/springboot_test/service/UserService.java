package com.qianliu.springboot_test.service;

import com.qianliu.springboot_test.entity.User;
import com.qianliu.springboot_test.event.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/*
 * @author qianliu on 2019/5/17 19:37
 * @Discription:管理UserBean的service
 */
@Service
public class UserService {
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 用户注册方法:所有的事件都要通过applicationContext进行发布
     * @param user
     */
    public void register(User user)
    {
        //../省略其他逻辑

        //发布UserRegisterEvent事件，注册需要监听的User类
        applicationContext.publishEvent(new UserRegisterEvent(this,user));
    }
}
