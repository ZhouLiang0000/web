package com.zl.spring.proxy;

import org.junit.Test;

import com.zl.spring.service.UserService;
import com.zl.spring.service.UserServiceImpl;

public class DemoTest {
	//动态代理方式实现
	@Test
	public void saveTest() {
		UserService userService = new UserServiceImpl();
		UserServiceProxyFactory factory = new UserServiceProxyFactory(userService);
		UserService userServiceProxy = factory.getUserServiceProxy();
		userServiceProxy.save();
	}
	//cglib代理方式实现
	@Test
	public void deleteTest() {
		UserServiceProxyFactory2 factory = new UserServiceProxyFactory2();
		UserService userServiceProxy = factory.getUserServiceProxy();
		userServiceProxy.delete();
		System.out.println(userServiceProxy instanceof UserServiceImpl);
	}
}
