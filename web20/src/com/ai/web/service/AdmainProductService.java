package com.ai.web.service;

import java.sql.SQLException;
import java.util.List;

import com.ai.web.dao.AdmainProductDao;
import com.ai.web.domain.Category;
import com.ai.web.domain.Product;

public class AdmainProductService {

	public List<Product> findAllProduct() throws SQLException {
		AdmainProductDao dao = new AdmainProductDao();
		List<Product> productList = dao.findAllProduct();
		return productList;
	}

	public List<Category> findAllProductCategory() throws SQLException {
		AdmainProductDao dao = new AdmainProductDao();
		List<Category> productCategoryList = dao.findAllProductCategory();
		return productCategoryList;
	}

	public void addProduct(Product product) throws SQLException {
		AdmainProductDao dao = new AdmainProductDao();
		dao.addProduct(product);
	}

	public void delProductByPid(String pid) throws SQLException {
		AdmainProductDao dao = new AdmainProductDao();
		dao.delProductByPid(pid);
	}

	public Product findProductByPid(String pid) throws SQLException {
		AdmainProductDao dao = new AdmainProductDao();
		Product product = dao.findProductByPid(pid);
		return product;
	}

	public void editProduct(Product product) throws SQLException {
		AdmainProductDao dao = new AdmainProductDao();
		dao.editProduct(product);
	}

}
