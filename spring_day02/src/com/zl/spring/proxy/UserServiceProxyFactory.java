package com.zl.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.zl.spring.service.UserService;
import com.zl.spring.service.UserServiceImpl;
//动态代理
public class UserServiceProxyFactory implements InvocationHandler {
	private UserService us;

	public UserServiceProxyFactory(UserService us) {
		super();
		this.us = us;
	}

	public UserService getUserServiceProxy() {
		// 生成代理对象
		UserService usProxy = (UserService) Proxy.newProxyInstance(UserServiceProxyFactory.class.getClassLoader(),
				UserServiceImpl.class.getInterfaces(), this);
		// 返回代理对象
		return usProxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("开启事物");
		Object invoke = method.invoke(us, args);
		System.out.println("提交事物");
		return invoke;
	}

}
