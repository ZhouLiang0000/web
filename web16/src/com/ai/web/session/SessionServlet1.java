package com.ai.web.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class SessionServlet1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、创建属于该(会话)客户端的私有的session对象
		// 备注：request.getSession()方法内部会自己判断该客户端在服务器是否存在session，如果没有，创建返回，如果存在就返回存在的session
		HttpSession session = request.getSession();
		session.setAttribute("name", "hehe");
		String sessionId = session.getId();
		//JSESSIONID持久化
		Cookie cookie = new Cookie("JSESSIONID", sessionId);
		cookie.setPath("/");
		cookie.setMaxAge(60*10);
		response.addCookie(cookie);
		
		
		response.getWriter().write("JSESSIONID:" + sessionId);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
