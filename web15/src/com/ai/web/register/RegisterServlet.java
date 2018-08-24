package com.ai.web.register;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.ai.web.domian.User;
import com.ai.web.utils.C3P0Utils;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置request的编码  只适合post方式
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		//get 方式乱码解决
//		username = new String(username.getBytes("iso8859-1"),"UTF-8");
		
		System.out.println(username);
		try {
			// 1、使用BeanUtils进行自动映射封装
			// BeanUtils工作原理：将map中的数据 根据key与实体的属性的对应关系封装
			// 只要key的名字与实体的属性的名字一样，就自动封装到实体中
			User user = new User();
			Map<String, String[]> properties = request.getParameterMap();
			BeanUtils.populate(user, properties);
			// 现在这个位置user对象已经封装好了
			// 但是需要手动的封装uid---随机数uuid -- 随机的不重复的32位-----java代码生成后是36位
			user.setUid(UUID.randomUUID().toString());
			// 2、将参数传递给一个业务操作方法
			register(user);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//认为注册成功/只要是用到web应用名称的地方就使用request.getContextPath()动态获取
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// 注册的方法
	public void register(User user) {
		try {
			// 操作数据库
			QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
			String insert = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
			qRunner.update(insert, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
					user.getEmail(), null, user.getBirthday(), user.getSex(), null, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
