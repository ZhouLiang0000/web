package login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
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

	@Override
	public void init() throws ServletException {
		// 在ServletContext域中存一个数据count
		int count = 0;
		this.getServletContext().setAttribute("count", count);
	}

	int count = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1、获得用户名和密码
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		// 2、从数据库中验证该用户名和密码是否正确
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String querySql = "select * from user where uname = ? and upassword = ?";
		Object[] params = { userName, passWord };
		User user = null;
		try {
			user = qr.query(querySql, new BeanHandler<>(User.class), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 3、根据返回的结果给用户不同的显示信息
		if (user != null) {
			ServletContext context = this.getServletContext();
			// 从ServletContext中取出count进行++运算
			Integer count = (Integer) context.getAttribute("count");
			count++;
			// 用户登录成功
			response.getWriter().write(user.toString() + "you are login success person:" + count);
			// 将count再次存入ServletContext中
			context.setAttribute("count", count);
		} else {
			// 用户登录失败
			response.getWriter().write("sorry your username or passworid is wrong");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
