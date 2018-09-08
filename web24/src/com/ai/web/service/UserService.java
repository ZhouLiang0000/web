package com.ai.web.service;

import java.sql.SQLException;

import com.ai.web.dao.UserDao;
import com.ai.web.domain.User;

public class UserService {

	public User login(String username, String password) throws SQLException {
		UserDao dao = new UserDao();
		User user = dao.login(username, password);
		return user;
	}

}
