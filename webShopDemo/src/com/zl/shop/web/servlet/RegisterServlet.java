package com.zl.shop.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.zl.shop.domain.User;
import com.zl.shop.utils.CommonUtils;
import com.zl.shop.utils.MailUtils;
import com.zl.shop.web.sevice.UserService;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> parameterMap = request.getParameterMap();
		User user = new User();
		try {
			// 自己指定时间转换器的格式
			ConvertUtils.register(new Converter() {

				@Override
				public Object convert(Class arg0, Object arg1) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date date = null;
					try {
						date = format.parse(arg1.toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return date;
				}
			}, Date.class);
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		user.setUid(CommonUtils.getUUid());
		user.setTelephone("18510971680");
		user.setState(0);
		String activeCode = CommonUtils.getUUid();
		user.setCode(activeCode);
		UserService service = new UserService();
		boolean isRegisterSuccess = false;
		try {
			isRegisterSuccess = service.register(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (isRegisterSuccess) {
			//发送激活邮件
			String emailMsg = "恭喜您注册成功请点击下面链接进行激活"
						+"<a href='http://localhost:8080/webShopDemo/active?activeCode="+activeCode+"'>"
						+ "http://localhost:8080/webShopDemo/active?activeCode="+activeCode+"</a>";
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			// 注册成功
			response.sendRedirect(request.getContextPath() + "/registerSuccess.jsp");
		} else {
			// 注册失败
			response.sendRedirect(request.getContextPath() + "/registerFail.jsp");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
