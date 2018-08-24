package com.ai.web.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ai.web.domian.User;
import com.ai.web.utils.C3P0Utils;
import com.sun.org.apache.bcel.internal.generic.NEW;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 防止中文乱码
		request.setCharacterEncoding("UTF-8");

		// 1、获取用户名
		String username = request.getParameter("username");
		// 2、获取密码
		String password = request.getParameter("password");
		// 3、调用业务方法去查询这个用户是否存在
		User user = login(username, password);

		// 4、判断用户是否存在
		if (user != null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			// 该用户名或密码不正确
			// 跳回登录界面，使用转发，并且向request域中写入数据
			request.setAttribute("loginInfo", "该用户名或密码不正确");
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}
	}

	private User login(String username, String password) {
		try {
			QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
			String sql = "select * from user where username = ? and password = ?";
			Object[] params = { username, password };
			User user = queryRunner.query(sql, new BeanHandler<>(User.class), params);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
