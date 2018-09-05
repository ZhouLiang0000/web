package com.ai.web.service;

import java.sql.SQLException;
import java.util.List;

import com.ai.web.dao.ProductDao;
import com.ai.web.domain.PageBean;
import com.ai.web.domain.Product;

public class ProductService {

	public List<Product> findAllProduct() throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findAllProduct();
	}

	public PageBean<Product> findPageBean(int currentPage, int currentCount) throws SQLException {
		PageBean<Product> pageBean = new PageBean<Product>();
		ProductDao dao = new ProductDao();
		// 1、 当前页 private int currentPage;
		pageBean.setCurrentPage(currentPage);
		// 2、 当前页显示的条数 private int currentCount;
		pageBean.setCurrentCount(currentCount);
		// 3、 总条数 private int totalCount;
		int totalCount = dao.getTotalCount();
		pageBean.setTotalCount(totalCount);
		// 4、 总页数 private int totalPage; 总条数/当前页显示条数 向上取整
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);
		// 5、 每页显示的数据 private List<T> productList;
		// 索引index = (当前页数-1)*每页显示条数
		int index = (currentPage - 1) * currentCount;
		List<Product> productList = dao.getProductListForPageBean(index, currentCount);
		pageBean.setProductList(productList);
		return pageBean;
	}

}
