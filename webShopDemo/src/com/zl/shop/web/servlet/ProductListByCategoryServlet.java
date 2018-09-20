package com.zl.shop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zl.shop.domain.PageBean;
import com.zl.shop.domain.Product;
import com.zl.shop.web.service.ProductService;

@SuppressWarnings("serial")
public class ProductListByCategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取cid
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;

		ProductService service = new ProductService();
		PageBean pageBean = null;
		try {
			pageBean = service.findProductListByCid(cid, currentPage, currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		List<Product> historyProductList = new ArrayList<>();
		// 获得客户端的cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();
					String[] split = pids.split("-");
					for (String str : split) {
						try {
							Product product = service.findProductByPid(str);
							historyProductList.add(product);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		request.setAttribute("historyProductList", historyProductList);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
