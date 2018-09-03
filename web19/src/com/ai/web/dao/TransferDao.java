package com.ai.web.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class TransferDao {

	public void out(String out, double money, Connection conn) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "update account set money = money - ? where name = ?";
		queryRunner.update(conn, sql, money, out);
	}

	public void in(String in, double money, Connection conn) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "update account set money = money + ? where name = ?";
		queryRunner.update(conn, sql, money, in);
	}

}
