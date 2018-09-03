package com.ai.web.dbutils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.ai.web.utils.C3P0Utils;

public class DBUtilsDemo {
	private static Connection conn = null;

	public static void main(String[] args) {
		conn = C3P0Utils.getConnection();
		try {
			QueryRunner queryRunner = new QueryRunner();
			String sql = "update account set money = 10000 where name = ?";
			conn.setAutoCommit(false);
			int row = queryRunner.update(conn, sql, "lucy");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
