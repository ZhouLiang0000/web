package com.ai.web.web.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ai.web.domain.Product;
import com.ai.web.utils.C3P0Utils;

public class ProductDao {

	public List<Product> findAllProduct() {
		// 查询数据库获取数据
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product";
		List<Product> productList = null;
		try {
			productList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

}
