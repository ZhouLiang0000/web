package com.ai.web.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 校验验证码
		// 1获取页面输入的验证码
		String checkCodeClient = request.getParameter("checkCode");
		// 2获取生成图片文字的验证码
		String checkCodeSession = (String) request.getSession().getAttribute("checkcode_session");
		// 3比对页面输入的和生成的验证码是否一致
		if (checkCodeClient != null && checkCodeSession != null && !checkCodeClient.endsWith(checkCodeSession)) {
			request.setAttribute("loginInfo", "您的验证码不正确，请重新输入");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		// 4获取用户名和密码进行校验
		// ......
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
