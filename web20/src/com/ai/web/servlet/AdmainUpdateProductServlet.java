package com.ai.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ai.web.domain.Category;
import com.ai.web.domain.Product;
import com.ai.web.service.AdmainProductService;

@SuppressWarnings("serial")
public class AdmainUpdateProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取参数
		String pid = request.getParameter("pid");
		AdmainProductService service = new AdmainProductService();
		Product product = null;
		try {
			product = service.findProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 传递请求到service层获取商品分类信息
		List<Category> productCategoryList = null;
		try {
			productCategoryList = service.findAllProductCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("productCategoryList", productCategoryList);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
