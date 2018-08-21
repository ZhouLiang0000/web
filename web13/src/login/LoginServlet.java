package login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import domain.User;
import utils.C3P0Utils;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、获得用户名和密码
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		//2、从数据库中验证该用户名和密码是否正确
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String querySql = "select * from user where uname = ? and upassword = ?";
		Object[] params = {userName,passWord};
		User user = null;
		try {
			user = qr.query(querySql, new BeanHandler<>(User.class), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3、根据返回的结果给用户不同的显示信息
		if(user != null) {
			//用户登录成功
			response.getWriter().write(user.toString());
		}else {
			//用户登录失败
			response.getWriter().write("sorry your username or passworid is wrong");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
