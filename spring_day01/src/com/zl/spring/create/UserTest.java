package com.zl.spring.create;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zl.spring.bean.User;

public class UserTest {
	// 空参构造创建方式1
	@Test
	public void userTest() {
		// 1、获取spring容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/zl/spring/create/applicationContext.xml");
		// 2、获取User对象
		User user = (User) ac.getBean("user");
		// 3、打印对象
		System.out.println(user.toString());
	}

	// 静态工厂方式创建
	@Test
	public void user2Test() {
		// 1、获取spring容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/zl/spring/create/applicationContext.xml");
		// 2、获取静态工厂对象
		User user = (User) ac.getBean("user2");
		// 3、打印对象
		System.out.println(user.toString());
	}

	// 动态工厂方式创建
	@Test
	public void user3Test() {
		// 1、获取spring容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/zl/spring/create/applicationContext.xml");
		// 2、获取静态工厂对象
		User user = (User) ac.getBean("user3");
		// 3、打印对象
		System.out.println(user.toString());
	}
}
