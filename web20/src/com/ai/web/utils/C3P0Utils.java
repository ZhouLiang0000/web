package com.ai.web.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0工具类获取DataSource和Connection对象
 */
public class C3P0Utils {
	public static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	public static ThreadLocal<Connection> tL = new ThreadLocal<Connection>();

	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 从线程池中获取一个连接对象
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * 获取当前线程的链接对象，没有就创建一个存储到当前线程
	 */
	public static Connection getCurrentConnection() throws SQLException {
		Connection conn = tL.get();
		if (conn == null) {
			conn = getConnection();
			tL.set(conn);
		}
		return conn;
	}

	/**
	 * 开启一个事务
	 */
	public static void startTransaction() throws SQLException {
		Connection conn = getCurrentConnection();
		conn.setAutoCommit(false);
	}

	/**
	 * 提交一个事务
	 */
	public static void commitTransaction() throws SQLException {
		Connection conn = getCurrentConnection();
		conn.commit();
		// 将conn从ThreadLocal中移除
		tL.remove();
		conn.close();
	}

	/**
	 * 回滚一个事务
	 */
	public static void rollbackTransaction() throws SQLException {
		Connection conn = getCurrentConnection();
		conn.rollback();
	}
}
