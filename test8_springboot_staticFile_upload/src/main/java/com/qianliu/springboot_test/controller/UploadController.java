package com.qianliu.springboot_test.controller;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.UUID;


@Controller
public class UploadController {
    //获取日志生成器
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${file.uploadFolder}")
    private String uploadPath; //文件上传的地址

    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "hello";
    }
    /**
     * 初始化上传文件界面，跳转到index.jsp
     * @return
     */
    @RequestMapping(value = "/index" , method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    /**
     * 上传多个文件
     * @param file 上传文件集合
     * @return
     */
    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
    @ResponseBody
    public HashMap uploads(MultipartFile[] file, HttpSession session)
    {
        //上传目录地址
        String uploadDir = uploadPath;

        //如果目录不存在，自动创建文件夹
        File dir = new File(uploadDir);
        if(!dir.exists())
        {
            dir.mkdir();
            logger.info("创建文件："+uploadDir);
        }

        String suffix = null;
        String fileName = null;

        HashMap hashMap = new HashMap();
        try {
            //遍历文件数组执行上传
            for (int i =0;i<file.length;i++) {
                if(file[i] != null) {
                    logger.info("开始上传文件.......");
                    //调用上传方法
                    suffix = file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf(".")); //后缀名
                    fileName = UUID.randomUUID() + suffix;
                    IOUtils.copy(file[i].getInputStream(),new FileOutputStream(uploadDir+"\\"+fileName));
                    //file[i].transferTo(new File(uploadDir+"\\"+fileName));
                    logger.info("上传"+uploadDir+"\\"+fileName+"文件成功！");
                }
            }
            logger.info("上传结束！所有文件上传文件至："+uploadDir);
        }catch (Exception e)
        {
            //打印错误堆栈信息
            e.printStackTrace();
            logger.info("上传文件失败！");
            hashMap.put("uploadMessage","上传失败");
            return hashMap;
        }

        hashMap.put("uploadMessage:","上传成功");
        return hashMap;
    }

}
