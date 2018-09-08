package com.ai.web.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ai.web.domain.User;
import com.ai.web.utils.C3P0Utils;

public class UserDao {

	public User login(String username, String password) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from user where uname = ? and upassword = ?";
		User user = qRunner.query(sql, new BeanHandler<>(User.class), username, password);
		return user;
	}

}
