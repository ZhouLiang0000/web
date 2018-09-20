package com.zl.shop.web.service;

import java.sql.SQLException;
import java.util.List;

import com.zl.shop.dao.ProductDao;
import com.zl.shop.domain.Categroy;
import com.zl.shop.domain.PageBean;
import com.zl.shop.domain.Product;

public class ProductService {

	public List<Product> findHotProductList() throws SQLException {
		ProductDao dao = new ProductDao();
		List<Product> hotProductList = dao.findHotProductList();
		return hotProductList;
	}

	public List<Product> findNewProductList() throws SQLException {
		ProductDao dao = new ProductDao();
		List<Product> newProductList = dao.findNewProductList();
		return newProductList;
	}

	public List<Categroy> finAllCategroy() throws SQLException {
		ProductDao dao = new ProductDao();
		List<Categroy> categroyList = dao.finAllCategroy();
		return categroyList;
	}

	@SuppressWarnings("rawtypes")
	public PageBean findProductListByCid(String cid, int currentPage, int currentCount) throws SQLException {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 封装pageBean
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		ProductDao dao = new ProductDao();
		int totalCount = dao.findAllProductCout(cid);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);
		int index = (currentPage - 1) * currentCount;
		List<Product> list = dao.findProductByPage(cid, index, currentCount);
		pageBean.setList(list);
		return pageBean;
	}

	public Product findProductByPid(String pid) throws SQLException {
		ProductDao dao = new ProductDao();
		Product product = dao.findProductByPid(pid);
		return product;
	}

}
