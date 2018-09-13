package com.zl.shop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zl.shop.web.sevice.UserService;

@SuppressWarnings("serial")
public class ActiveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取激活码
		String activeCode = request.getParameter("activeCode");
		UserService service = new UserService();
		boolean isActive = false;
		try {
			isActive = service.activie(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (isActive) {
			// 激活成功，跳转到登录界面
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else {
			// 激活失败
			System.out.println("激活失败");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
