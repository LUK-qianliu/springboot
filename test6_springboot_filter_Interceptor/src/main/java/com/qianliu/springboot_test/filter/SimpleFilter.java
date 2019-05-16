package com.qianliu.springboot_test.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//将servlet过滤器配置起来
//1.urlPatterns = "/*"表示过滤的url的正则表达式
@Order(1) //多个filter的时候此过滤器的顺序第一
@WebFilter(urlPatterns = "/filter/*", filterName = "SimpleFilter")
public class SimpleFilter implements Filter {
	//获取日志
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void destroy() {}

	//打印过来的请求的host和address
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
			throws IOException, ServletException {

		logger.info("Remote Host:"+request.getRemoteHost());
		logger.info("Remote Address:"+request.getRemoteAddr());
		filterchain.doFilter(request, response);
		/*
		//过滤没有登陆的人
		if(request.getParameter("user")!=null){
			filterchain.doFilter(request, response);
		}
		*/
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {}
}

