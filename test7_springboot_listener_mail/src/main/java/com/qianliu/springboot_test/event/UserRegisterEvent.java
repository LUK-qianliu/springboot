package com.qianliu.springboot_test.event;

import com.qianliu.springboot_test.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/*
 * @author qianliu on 2019/5/17 19:30
 * @Discription:创建一个监听事件
 */
@Getter
public class UserRegisterEvent extends ApplicationEvent {

    private User user;

    /*
     * @author qianliu on 2019/5/17 19:28
     * @param source 发生事件的对象
     * @param user 注册监听对象
     * @Discription:
     */
    public UserRegisterEvent(Object source,User user) {
        super(source);
        this.user = user;
    }
}
