package com.qianliu.springboot_test.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component //@Component标签相当于配置文件中的<bean id="" class=""/>，以后可以直接放入到@Configuration标注的类中
public class SimpleIntercepter implements HandlerInterceptor {
    //获取日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //不是含有"/interceptor"的链接不进行拦截
        if(!request.getRequestURI().contains("/interceptor")){
            return true;
        }

        //查看session里面是否有"name"的数据
        String name = null;
        if((name = (String) request.getSession().getAttribute("name"))!=null){
            logger.info("user:"+name+"进入网站。。");
            return true;
        }

        return false;
    }

    /*
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle..................");
    }

    /*
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion...............");
    }
}
