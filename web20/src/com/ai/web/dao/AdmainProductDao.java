package com.ai.web.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ai.web.domain.Category;
import com.ai.web.domain.Product;
import com.ai.web.utils.C3P0Utils;

public class AdmainProductDao {

	public List<Product> findAllProduct() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product";
		List<Product> productList = queryRunner.query(sql, new BeanListHandler<>(Product.class));
		return productList;
	}

	public List<Category> findAllProductCategory() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from category";
		List<Category> productCategoryList = queryRunner.query(sql, new BeanListHandler<>(Category.class));
		return productCategoryList;
	}

	public void addProduct(Product product) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		queryRunner.update(sql, product.getPid(), product.getPname(), product.getMarket_price(),
				product.getShop_price(), product.getPimage(), product.getPdate(), product.getIs_hot(),
				product.getPdesc(), product.getPflag(), product.getCid());
	}

	public void delProductByPid(String pid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "delete from product where pid = ?";
		queryRunner.update(sql, pid);
	}

	public Product findProductByPid(String pid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product where pid = ?";
		Product product = queryRunner.query(sql, new BeanHandler<>(Product.class), pid);
		return product;
	}

	public void editProduct(Product product) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update product set pname = ?,market_price = ?,shop_price = ?,pimage = ?,pdate = ?,is_hot = ?,pdesc = ?,pflag = ?,cid = ? where pid = ?";
		queryRunner.update(sql, product.getPname(), product.getMarket_price(), product.getShop_price(),
				product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(),
				product.getCid(), product.getPid());
	}

}
