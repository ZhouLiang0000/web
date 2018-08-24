package com.ai.web.line;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LineServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、获得请求方法
		String method = request.getMethod();
		System.out.println("请求的方式:" + method);
		// 2、获得请求资源相关的内容
		String requestURI = request.getRequestURI();
		System.out.println("请求的uri:" + requestURI);
		StringBuffer requestURL = request.getRequestURL();
		System.out.println("请求的url:" + requestURL.toString());
		// 3、获取应用的名称
		String contextPath = request.getContextPath();
		System.out.println("应用的名称:" + contextPath);
		// 4、获取请求参数的字符串
		String queryString = request.getQueryString();
		System.out.println("请求的参数:" + queryString);
		// 5、获得客户端的ip，访问者的
		String remoteAddr = request.getRemoteAddr();
		System.out.println("ip:" + remoteAddr);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
