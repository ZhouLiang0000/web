package com.ai.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ai.web.service.UserService;

@SuppressWarnings("serial")
public class CheckUserNameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserService service = new UserService();
		boolean isExist = false;
		try {
			isExist = service.checkUserName(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write("{\"isExist\":"+isExist+"}");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
