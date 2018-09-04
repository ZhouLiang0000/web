package com.ai.web.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.ai.web.utils.C3P0Utils;

public class TransferDao {

	public void out(String out, double money) throws SQLException {
		Connection conn = C3P0Utils.getCurrentConnection();
		QueryRunner queryRunner = new QueryRunner();
		String sql = "update account set money = money - ? where name = ?";
		queryRunner.update(conn, sql, money, out);
	}

	public void in(String in, double money) throws SQLException {
		Connection conn = C3P0Utils.getCurrentConnection();
		QueryRunner queryRunner = new QueryRunner();
		String sql = "update account set money = money + ? where name = ?";
		queryRunner.update(conn, sql, money, in);
	}

}
