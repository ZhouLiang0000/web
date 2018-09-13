package com.zl.shop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zl.shop.domain.User;
import com.zl.shop.web.sevice.UserService;

@SuppressWarnings("serial")
public class CheckUsernameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserService service = new UserService();
		boolean isExist = false;
		User user = null;
		try {
			user = service.checkUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(user != null) {
			//用户已存在
			isExist = true;
		}else {
			//用户不存在
			isExist = false;
		}
		String json = "{\"isExist\":"+isExist+"}";
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
