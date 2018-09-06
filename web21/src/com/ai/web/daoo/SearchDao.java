package com.ai.web.daoo;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.ai.web.domain.Product;
import com.ai.web.utils.C3P0Utils;

public class SearchDao {

	public List<Object> searchWord(String word) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product where pname like ? limit 0,8 ";
		List<Object> productList = queryRunner.query(sql, new ColumnListHandler("pname"), "%" + word + "%");
		return productList;
	}

}
