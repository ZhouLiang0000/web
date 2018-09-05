package com.ai.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.ai.web.domain.Category;
import com.ai.web.domain.Condition;
import com.ai.web.domain.Product;
import com.ai.web.service.AdmainProductService;

@SuppressWarnings("serial")
public class AdmainSearchProductListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 收集表单数据
		Map<String, String[]> parameterMap = request.getParameterMap();
		// 将散装的数据封装到一个VO实体中
		Condition condition = new Condition();
		try {
			BeanUtils.populate(condition, parameterMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// 将实体传递给service层
		AdmainProductService service = new AdmainProductService();
		List<Product> productList = null;
		try {
			productList = service.findProductListByCondition(condition);
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

		// 存储到request域中并转发到jsp界面展示
		request.setAttribute("condition", condition);
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
