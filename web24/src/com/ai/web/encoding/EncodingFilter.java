package com.ai.web.encoding;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//post提交解决中文乱码问题
//		request.setCharacterEncoding("UTF-8");
		/*
		 *  get提交解决中文乱码问题
		   *       在传递request之前对request的getParameter方法进行增强
		 * 	装饰者模式(包装)
		 * 	1、增强类与被增强的类要实现同一个接口
		 * 	2、在增强类中传入被增强的类
		 * 	3、需要增强的方法重写，不需要增强的方法调用被增强的对象
		 */
		//被增强的类
		HttpServletRequest req = (HttpServletRequest) request;
		//增强的类
		EnhanceRequest enhanceRequest = new EnhanceRequest(req);
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
