package com.ai.web.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ai.web.domain.User;
import com.ai.web.service.UserService;

public class AutoLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获得cookie中的账号信息进行登录的操作
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String cookie_username = null;
		String cookie_password = null;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("cookie_username".equals(cookie.getName())) {
//					cookie_username = cookie.getValue();
					// 对中文用户名解码
					cookie_username = URLDecoder.decode(cookie.getValue(), "UTF-8");
				} else if ("cookie_password".equals(cookie.getName())) {
					cookie_password = cookie.getValue();
				}
			}
		}

		if (cookie_password != null && cookie_username != null) {
			UserService service = new UserService();
			User user = null;
			try {
				user = service.login(cookie_username, cookie_password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			req.getSession().setAttribute("user", user);
		}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}

}
