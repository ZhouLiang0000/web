package com.zl.shop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zl.shop.domain.Categroy;
import com.zl.shop.utils.JedisUtils;
import com.zl.shop.web.service.ProductService;

import redis.clients.jedis.Jedis;

@SuppressWarnings("serial")
public class CategoryListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先从redis缓存中取数据，有返回，没有查数据库，并且将数据存储到redis缓存中
		Jedis jedis = JedisUtils.getJedis();
		String categoryListJson = jedis.get("categoryListJson");
		if(categoryListJson == null) {
			System.out.println("缓存没有数据，从数据库中取值");
			ProductService service = new ProductService();
			List<Categroy> categroysList = null;
			try {
				//获得分类导航条目
				categroysList = service.finAllCategroy();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Gson gson = new Gson();
			categoryListJson = gson.toJson(categroysList);
			jedis.set("categoryListJson", categoryListJson);
		}else {
			System.out.println("从缓存中取值........");
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryListJson);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
