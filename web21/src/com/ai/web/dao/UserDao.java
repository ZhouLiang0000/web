package com.ai.web.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ai.web.utils.C3P0Utils;

public class UserDao {

	public Long checkUserName(String username) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select count(*) from user where username = ?";
		Long query = (Long) queryRunner.query(sql, new ScalarHandler(), username);
		return query;
	}

}
