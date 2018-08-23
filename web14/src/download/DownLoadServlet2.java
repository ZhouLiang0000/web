package download;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

@SuppressWarnings("serial")
public class DownLoadServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ******中文下载*********
		// 1、获取所要下载的文件的名称
		String filename = request.getParameter("filename");// 美女.jpg

		// 解决中文乱码的问题
//		filename = new String(filename.getBytes("ISO8859-1"), "UTF-8");
		// 获取客户端浏览器的类型，不同的类型使用不同的编码
		String agent = request.getHeader("User-Agent");
		String filenameEncoder = "";
		if (agent.contains("MSIE")) {// IE浏览器
			filenameEncoder = URLEncoder.encode(filename, "utf-8");
			filenameEncoder = filenameEncoder.replaceAll("+", " ");
		} else if (agent.contains("Firefox")) {// 火狐浏览器
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filenameEncoder = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
		} else {// 其他浏览器
			filenameEncoder = URLEncoder.encode(filename, "utf-8");
		}

		// 2、告知客户端所要下载的文件的类型
		response.setContentType(this.getServletContext().getMimeType(filename));
		// 3、告知客户端不解析，下载文件(有中文的时候需要，需要客户端解码，这里编码，但是需要做兼容，不同的适配器解码规则不同)
		response.setHeader("Content-Disposition", "attachment;filename=" + filenameEncoder);
		// 4、获取文件在服务器的绝对路径
		String path = this.getServletContext().getRealPath("download/" + filename);
		// 5、获取输入流
		InputStream in = new FileInputStream(path);
		// 6、获取输出流
		OutputStream out = response.getOutputStream();
		// 7、拷贝文件
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
