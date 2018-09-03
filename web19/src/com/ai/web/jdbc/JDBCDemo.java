package com.ai.web.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Connection conn = null;
		try {
			// 通过jdbc去控制事物
			// 1、注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2、获取连接
			conn = DriverManager.getConnection("jdbc:mysql:///web19", "zhouliang", "zhouliang");

			// 手动开始事物
			conn.setAutoCommit(false);

			// 3、获得操作数据库的对象
			Statement stmt = conn.createStatement();
			// 4、执行sql
			int row = (int) stmt.executeLargeUpdate("update account set money = 5000 where name = 'tom'");
			// 提交事物
			conn.commit();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
