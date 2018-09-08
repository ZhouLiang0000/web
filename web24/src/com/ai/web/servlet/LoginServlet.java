package com.ai.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ai.web.domain.User;
import com.ai.web.service.UserService;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserService service = new UserService();
		User user = null;
		try {
			user = service.login(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user != null) {
			String autologin = request.getParameter("autologin");
			if (autologin != null) {

				// 对中文用户名编码之后再存储
				String username_code = URLEncoder.encode(username, "UTF-8");
				Cookie cookieName = new Cookie("cookie_username", username_code);
//				Cookie cookieName = new Cookie("cookie_username", user.getUname());
				Cookie cookiePassword = new Cookie("cookie_password", user.getUpassword());
				cookieName.setMaxAge(60 * 60);
				cookiePassword.setMaxAge(60 * 60);
				cookieName.setPath(request.getContextPath());
				cookiePassword.setPath(request.getContextPath());
				response.addCookie(cookieName);
				response.addCookie(cookiePassword);
			}

			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			request.setAttribute("loginInfo", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
