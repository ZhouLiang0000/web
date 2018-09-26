package com.zl.spring.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.zl.spring.service.UserService;
import com.zl.spring.service.UserServiceImpl;

//cglib代理方式
public class UserServiceProxyFactory2 implements MethodInterceptor {

	public UserService getUserServiceProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserServiceImpl.class);
		enhancer.setCallback(this);
		UserService userService = (UserService) enhancer.create();
		return userService;
	}

	@Override
	public Object intercept(Object proxyObj, Method method, Object[] arg, MethodProxy methodProxy) throws Throwable {
		System.out.println("打开事物");
		Object invoke = methodProxy.invokeSuper(proxyObj, arg);
		System.out.println("提交事物");
		return invoke;
	}

}
