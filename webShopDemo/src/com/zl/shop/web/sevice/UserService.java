package com.zl.shop.web.sevice;

import java.sql.SQLException;

import com.zl.shop.domain.User;
import com.zl.shop.web.dao.UserDao;

public class UserService {

	public boolean register(User user) throws SQLException {
		UserDao dao = new UserDao();
		int row = dao.register(user);
		return row > 0 ? true : false;
	}

	public boolean activie(String activeCode) throws SQLException {
		UserDao dao = new UserDao();
		int row = dao.active(activeCode);
		return row > 0 ? true : false;
	}

	public User checkUsername(String username) throws SQLException {
		UserDao dao = new UserDao();
		User user = dao.checkUsername(username);
		return user;
	}

}
