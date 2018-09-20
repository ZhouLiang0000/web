package com.zl.shop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zl.shop.domain.Product;
import com.zl.shop.web.service.ProductService;

@SuppressWarnings("serial")
public class ProductInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String cid = request.getParameter("cid");
		String currentPage = request.getParameter("currentPage");
		ProductService service = new ProductService();
		Product product = null;
		try {
			product = service.findProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("product", product);
		request.setAttribute("cid", cid);
		request.setAttribute("currentPage", currentPage);
		// 获取客户端携带的cookie信息
		String pids = pid;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("pids".equals(cookies[i].getName())) {
					pids = cookies[i].getValue();
					String[] split = pids.split("-");
					List<String> asList = Arrays.asList(split);
					LinkedList<String> list = new LinkedList<>(asList);
					// 判断是否包含当前pid
					if (list.contains(pid)) {
						list.remove(pid);
						list.addFirst(pid);
					} else {
						list.addFirst(pid);
					}
					StringBuffer sb = new StringBuffer();
					for (int j = 0; j < list.size() && j < 7; j++) {
						sb.append(list.get(j));
						sb.append("-");
					}
					pids = sb.substring(0, sb.length() - 1);
				}
			}
		}
		// 转发之前存储cookie区分第几次访问
		Cookie cookiePids = new Cookie("pids", pids);
		response.addCookie(cookiePids);

		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
