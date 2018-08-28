package com.ai.web.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SendCookieServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(getServletContext().getContextPath());
		//1、创建Cookie对象
		Cookie cookie = new Cookie("name", "zhangsan");
		//1.1设置Cookie的持久化时间,单位是秒 删除cookie：时间设置为0，名称相同路径相同就代表删除cookie
		cookie.setMaxAge(60*10);//10分钟
		//1.2设置Cookie的携带路径  备注：如果不写路径，默认访问创建该cookie同级目录下的应用资源都会携带
//		cookie.setPath(getServletContext().getContextPath()+"/sendCookie");
//		cookie.setPath(getServletContext().getContextPath());//访问这个应用下面的资源
		cookie.setPath("/");//服务器下面的所有应用资源
		//2、将Cookie对象发送给客户端
		response.addCookie(cookie);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
