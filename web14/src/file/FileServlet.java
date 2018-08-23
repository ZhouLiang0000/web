package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取ServeltContext对象
		ServletContext context = this.getServletContext();
		//2、获取服务器上图片的绝对路径
		String realPath = context.getRealPath("a.jpg");
		//3、获得输入流
		InputStream in = new FileInputStream(realPath);
		//4、获取输出流
		ServletOutputStream out = response.getOutputStream();
		//5、遍历写入流
		int len = 0;
		byte[] buffer = new byte[1024];
		while((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
