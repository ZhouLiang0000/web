package com.ai.web.service;

import java.util.List;

import com.ai.web.dao.ProductDao;
import com.ai.web.domain.Product;

public class ProductService {

	public List<Product> findAllProduct() {
		// 没有复杂业务，传递到dao层
		ProductDao dao = new ProductDao();
		List<Product> productList = dao.findAllProduct();
		return productList;
	}

}
