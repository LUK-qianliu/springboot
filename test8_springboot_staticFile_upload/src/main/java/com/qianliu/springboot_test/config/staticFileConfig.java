package com.qianliu.springboot_test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class staticFileConfig implements WebMvcConfigurer {
    /*
     * @author qianliu on 2019/5/20 12:22
     * @param
     * @Discription: 实现静态资源的url映射，隐藏实际存储地址
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //访问"/**"就会被解析为/static/img/
        registry.addResourceHandler("/test/img/**").addResourceLocations("classpath:/static/picWarehouse/");
    }
}
