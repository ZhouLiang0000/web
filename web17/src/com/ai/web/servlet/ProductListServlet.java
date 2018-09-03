package com.ai.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ai.web.domian.Product;
import com.ai.web.utils.C3P0Utils;

@SuppressWarnings("serial")
public class ProductListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取数据库数据
		QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product";
		List<Product> productList = null;
		try {
			productList = qRunner.query(sql, new BeanListHandler<>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将数据存储到request域并转发到product_list.jsp中
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
