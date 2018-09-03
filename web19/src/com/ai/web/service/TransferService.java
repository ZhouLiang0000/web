package com.ai.web.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.ai.web.dao.TransferDao;
import com.ai.web.utils.C3P0Utils;

public class TransferService {
	boolean isTransferSuccess = true;

	public boolean transfer(String out, String in, double money) {
		TransferDao dao = new TransferDao();
		Connection conn = C3P0Utils.getConnection();
		try {
			//开启事务
			conn.setAutoCommit(false);
			// 转出钱的方法
			dao.out(out, money, conn);
			//自杀代码，造成事务失败
			int i = 1/0;
			// 转入钱的方法
			dao.in(in, money, conn);
			
		} catch (Exception e) {
			isTransferSuccess = false;
			try {
				//回滚事务
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isTransferSuccess;
	}

}
