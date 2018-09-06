package com.ai.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ai.web.domain.Product;
import com.ai.web.service.SearchService;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class SearchWordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String word = request.getParameter("word");
		SearchService service = new SearchService();
		List<Object> productList = null;
		try {
			productList = service.searchWord(word);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 使用json工具将服务器返回的集合转换成json串
		Gson gson = new Gson();
		String product = gson.toJson(productList);
		System.out.println(product);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(product);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
