package com.ai.web.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ai.web.domain.Product;
import com.ai.web.web.service.ProductService;

@SuppressWarnings("serial")
public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 传递请求到service层
		ProductService service = new ProductService();
		List<Product> productList = service.findAllProduct();
		// 转发给jsp展示数据
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
