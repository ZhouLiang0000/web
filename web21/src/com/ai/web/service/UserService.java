package com.ai.web.service;

import java.sql.SQLException;

import com.ai.web.dao.UserDao;

public class UserService {

	public boolean checkUserName(String username) throws SQLException {
		UserDao dao = new UserDao();
		Long isExist = dao.checkUserName(username);
		return isExist > 0 ? true : false;
	}

}
