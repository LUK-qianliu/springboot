package com.qianliu.springboot_test.interceptor.configuration;

import com.qianliu.springboot_test.interceptor.SimpleIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/*
 * @author qianliu on 2019/5/16 21:43
 * @Discription:注册SimpleIntercepter拦截器并注册拦截的url
 */
@Configuration //SimpleIntercepter相当于一个Bean，因为它被@Component标注过
public class UserInterceptorAppConfig implements WebMvcConfigurer {
    @Resource
    SimpleIntercepter simpleIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(simpleIntercepter).addPathPatterns("/**"); //该拦截器使用在所有的url上
    }
}
