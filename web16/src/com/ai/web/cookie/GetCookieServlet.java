package com.ai.web.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GetCookieServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取Cookie
		Cookie[] cookies = request.getCookies();
		//遍历获取想要获取的cookie
		for(Cookie cookie:cookies) {
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();
			System.out.println(cookieName+"---------"+cookieValue);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
