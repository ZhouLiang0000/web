package com.ai.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ai.web.service.TransferService;

@SuppressWarnings("serial")
public class TransferServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 1、接收页面转账的参数
		String out = request.getParameter("out");
		String in = request.getParameter("in");
		String moneyStr = request.getParameter("money");
		double money = Double.parseDouble(moneyStr);
		// 2、传递给service层处理
		TransferService service = new TransferService();
		boolean isSuccess = service.transfer(out, in, money);
		if (isSuccess) {
			response.getWriter().write("转账成功");
		} else {
			response.getWriter().write("转账失败");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
