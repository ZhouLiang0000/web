package com.ai.web.encoding;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EnhanceRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	public EnhanceRequest(HttpServletRequest req) {
		super(req);
		this.request = req;
	}

	@Override
	public String getParameter(String name) {
		String parameter = request.getParameter(name);// 乱码
		try {
			parameter = new String(parameter.getBytes("iso8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return parameter;
	}
}
