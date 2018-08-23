package status;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class StatusServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//手动的设置http响应行中的状态码
//		response.setStatus(302);
		//设置响应头
//		Date date = new Date();
//		response.addHeader("name", "zhangsan");
//		response.addHeader("name", "lisi");
//		response.addIntHeader("age", 28);
//		response.addDateHeader("birthday", date.getTime());
//		response.setHeader("name", "lisi");
		//没有响应，告知客户端去重定向到demoServlet
		//1、设置状态码
//		response.setStatus(302);
		//2、设置响应头的location
//		response.setHeader("location", "/web14/demo");
		//重定向的另外一种封装写法
		response.sendRedirect("/web14/demo");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
