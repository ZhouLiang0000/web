package com.ai.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.ai.web.domain.Product;
import com.ai.web.service.AdmainProductService;

@SuppressWarnings("serial")
public class AdmainEditProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// 客户端网页我们控制为UTF-8
		// 1、获取表单数据
		Map<String, String[]> parameterMap = request.getParameterMap();
		// 2、封装数据
		Product product = new Product();
		try {
			BeanUtils.populate(product, parameterMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// 封装实体类中界面未有的数据
		product.setPimage("products/1/c_0001.jpg");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
		String pdate = format.format(new Date());
		product.setPdate(pdate);
		product.setPflag(0);
		// 3、传递数据
		AdmainProductService service = new AdmainProductService();
		try {
			service.editProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admainProductServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
