package download;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1、获取要下载文件的名称
		String filename = request.getParameter("filename");

		// 要下载的这个文件的类型，客户端通过文件的MIME类型去区分类型
		response.setContentType(this.getServletContext().getMimeType(filename));
		// 告知客户端该文件不是解析，而是以附件的形式打开(下载)
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);

		// 2、获取所要下载文件在服务器端的绝对路径地址
		String path = this.getServletContext().getRealPath("download/" + filename);
		// 3、获取输入流
		InputStream in = new FileInputStream(path);
		// 4、获取输出流
		OutputStream out = response.getOutputStream();
		// 5、拷贝服务器文件写入客户端
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
