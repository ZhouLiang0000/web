package com.ai.web.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ai.web.domain.Product;
import com.ai.web.utils.C3P0Utils;

public class ProductDao {

	public List<Product> findAllProduct() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product";
		List<Product> productList = queryRunner.query(sql, new BeanListHandler<>(Product.class));
		return productList;
	}

	public int getTotalCount() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select count(*) from product";
		Long totalCount = (Long) queryRunner.query(sql, new ScalarHandler());
		return totalCount.intValue();
	}

	public List<Product> getProductListForPageBean(int index, int currentCount) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product limit ?,?";
		List<Product> productList = queryRunner.query(sql, new BeanListHandler<>(Product.class), index, currentCount);
		return productList;
	}

}
