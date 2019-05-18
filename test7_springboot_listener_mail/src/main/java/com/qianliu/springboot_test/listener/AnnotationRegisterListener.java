package com.qianliu.springboot_test.listener;

import com.qianliu.springboot_test.entity.User;
import com.qianliu.springboot_test.event.UserRegisterEvent;
import com.qianliu.springboot_test.mail.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @author qianliu on 2019/5/17 20:13
 * @Discription:创建一个监听者取完成相应的监听操作
 *  使用@EventListener方法实现注册事件监听
 */
@Component
public class AnnotationRegisterListener {

    //获取日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${mail.receptionMail.addr}")
    private String receptionMailAddr;

    @Autowired
    private SendMail sendMail;

    //模板引擎
    @Autowired
    private TemplateEngine templateEngine;


    //通过@EventListener注解来监听UserRegisterEvent事件
    @EventListener
    public void register(UserRegisterEvent userRegisterEvent)
    {
        //获取注册用户对象
        User user = userRegisterEvent.getUser();

        //发送邮件给管理员
        //创建邮件正文中的变量
        Context context = new Context();
        context.setVariable("username",receptionMailAddr);
        context.setVariable("methodName",userRegisterEvent.getClass().getName());
        context.setVariable("resultUser",userRegisterEvent.getUser());
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        context.setVariable("occurredTime",sdf.format(new Date()));

        // 传递 emailTemplate.html 模板需要的值，并将模板转换为 String
        // emailTemplate.html在resources/templates下
        String emailContent = templateEngine.process("emailTemplate", context);

        sendMail.sendHtmlMail(receptionMailAddr,"主题：注册成功",emailContent);

        //输出注册用户信息
        logger.info("@EventListener注册信息，用户名："+user.getName()+"，密码："+user.getPassword());
    }
}
