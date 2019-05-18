package com.qianliu.springboot_test.sendMailTest;

import com.qianliu.springboot_test.mail.SendMail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BasemailApplicationTests {

    @Value("${mail.receptionMail.addr}")
    private String receptionMailAddr;

    @Autowired
    private SendMail sendMailImpl;

    //模板引擎
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 测试 文本邮件
     * @throws Exception
     */
    @Test
    public void testSimpleMail() throws Exception {
        sendMailImpl.sendTextMail(receptionMailAddr,"测试文本邮箱发送","你好你好！");
    }

    /**
     * 测试 html 邮箱
     * @throws Exception
     */
    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        sendMailImpl.sendHtmlMail(receptionMailAddr,"test simple mail",content);
    }

    /*
     * @author qianliu on 2019/5/18 16:31
     * @Discription:测试带有附件的邮箱
     */
    @Test
    public void sendAttachmentsMail() {
        String filePath="C:\\\\Users\\\\Administrator\\\\Desktop\\\\java并发学习.txt";
        sendMailImpl.sendAttachmentsMail(receptionMailAddr, "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    /*
     * @author qianliu on 2019/5/18 16:32
     * @Discription:测试带有静态资源的邮箱
     */
    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\\\Users\\\\Administrator\\\\Desktop\\\\testMail.png";

        sendMailImpl.sendInlineResourceMail(receptionMailAddr, "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    /*
     * @author qianliu on 2019/5/18 16:33
     * @Discription:测试thymeleaf模板的邮箱
     */
    @Test
    public void sendTemplateMail() {
        //创建邮件正文中的变量
        Context context = new Context();
        context.setVariable("username", receptionMailAddr);//在thymeleaf中可以使用${username}获取到receptionMailAddr
        context.setVariable("methodName",this.getClass().getName());
        context.setVariable("resultMessage","无");
        //格式化时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");//显示2017年10月27日10时00分00秒格式
        context.setVariable("occurredTime", sdf.format(new Date()));

        // 传递 emailTemplate.html 模板需要的值，并将模板转换为 String
        // emailTemplate.html在resources/templates下
        String emailContent = templateEngine.process("emailTemplate", context);

        sendMailImpl.sendHtmlMail(receptionMailAddr,"主题：这是模板邮件",emailContent);
    }

    /*
     * @author qianliu on 2019/5/18 16:33
     * @Discription:测试thymeleaf模板的邮箱
     */
    @Test
    public void sendTemplateMailToWD() {
        //创建邮件正文中的变量
        Context context = new Context();
        context.setVariable("username",receptionMailAddr);
        context.setVariable("methodName",this.getClass().getName());
        context.setVariable("resultMessage","无");
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        context.setVariable("occurredTime",sdf.format(new Date()));

        // 传递 emailTemplate.html 模板需要的值，并将模板转换为 String
        // emailTemplate.html在resources/templates下
        String emailContent = templateEngine.process("emailTemplate", context);

        sendMailImpl.sendHtmlMail(receptionMailAddr,"主题：这是模板邮件",emailContent);
    }
}
