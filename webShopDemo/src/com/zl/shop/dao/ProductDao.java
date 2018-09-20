package com.zl.shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zl.shop.domain.Categroy;
import com.zl.shop.domain.Product;
import com.zl.shop.utils.DataSourceUtils;

public class ProductDao {

	public List<Product> findHotProductList() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot = ? limit ?,?";
		List<Product> hotProductList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class), 1, 0, 9);
		return hotProductList;
	}

	public List<Product> findNewProductList() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product order by pdate desc limit ? ,?";
		List<Product> newProductList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class), 0, 9);
		return newProductList;
	}

	public List<Categroy> finAllCategroy() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		List<Categroy> categroyList = queryRunner.query(sql, new BeanListHandler<Categroy>(Categroy.class));
		return categroyList;
	}

	public int findAllProductCout(String cid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid = ?";
		Long count = (Long) queryRunner.query(sql, new ScalarHandler(), cid);
		return count.intValue();
	}

	public List<Product> findProductByPage(String cid, int index, int currentCount) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid = ? limit ? ,?";
		List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class), cid, index,
				currentCount);
		return list;
	}

	public Product findProductByPid(String pid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid = ?";
		Product product = queryRunner.query(sql, new BeanHandler<Product>(Product.class), pid);
		return product;
	}

}
