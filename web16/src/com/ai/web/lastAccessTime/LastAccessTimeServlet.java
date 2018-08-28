package com.ai.web.lastAccessTime;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LastAccessTimeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、获取当前时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String currentTime = format.format(date);
		// 2、创建并设置cookie
		Cookie cookie = new Cookie("lastAccessTime", currentTime);
		cookie.setMaxAge(60 * 10 * 500);
		// 3、写入cookie
		response.addCookie(cookie);

		// 4、获取用户上次访问的时间
		String lastAccessTime = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for (Cookie cookie2 : cookies) {
				if ("lastAccessTime".equals(cookie2.getName())) {
					lastAccessTime = cookie2.getValue();
				}
			}
			response.setContentType("text/html;charset=UTF-8");
			if (lastAccessTime == null) {
				response.getWriter().write("您是第一次访问");
			} else {
				response.getWriter().write("您上次访问的时间是：" + lastAccessTime);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
