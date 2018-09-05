package com.ai.web.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ai.web.domain.Category;
import com.ai.web.domain.Condition;
import com.ai.web.domain.Product;
import com.ai.web.utils.C3P0Utils;
import com.sun.org.apache.bcel.internal.generic.NEW;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

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

	public List<Product> findProductListByCondition(Condition condition) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		List<String> list = new ArrayList<>();
		String sql = "select * from product where 1 = 1";
		if (condition.getPname() != null && !condition.getPname().trim().equals("")) {
			sql += " and pname like ? ";
			list.add("%" + condition.getPname().trim() + "%");
		}
		if (condition.getIsHot() != null && !condition.getIsHot().trim().equals("")) {
			sql += " and is_hot = ? ";
			list.add(condition.getIsHot().trim());
		}
		if (condition.getCid() != null && !condition.getCid().trim().equals("")) {
			sql += " and cid = ? ";
			list.add(condition.getCid().trim());
		}
		List<Product> productList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class), list.toArray());
		return productList;
	}

}
