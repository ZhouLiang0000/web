package com.ai.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class ProxyTest {
	@Test
	public void test1() {
		// 获得动态代理对象
		TargetIntenface objProxy = (TargetIntenface) Proxy.newProxyInstance(Target.class.getClassLoader(), new Class[] { TargetIntenface.class },
				new InvocationHandler() {
					//invoke 代表的是执行代理对象的方法
					//method 代表的是目标独享的方法的字节码对象
					//agrs 代表目标对象的响应的方法的参数
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("执行目标方法前的逻辑");
						//执行目标对象的方法
						Object invoke = method.invoke(new Target(), args);
						System.out.println("执行目标方法后的逻辑");
						return invoke;
					}
				});
		objProxy.method1();
		String method2 = objProxy.method2();
		System.out.println("-----------"+method2);
	}
}
