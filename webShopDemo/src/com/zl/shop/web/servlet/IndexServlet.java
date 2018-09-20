package com.zl.shop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zl.shop.domain.Product;
import com.zl.shop.web.service.ProductService;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		List<Product> hotProductList = null;
		List<Product> newProductList = null;
		try {
			// 热门商品
			hotProductList = service.findHotProductList();
			// 最新商品
			newProductList = service.findNewProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("hotProductList", hotProductList);
		request.setAttribute("newProductList", newProductList);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
