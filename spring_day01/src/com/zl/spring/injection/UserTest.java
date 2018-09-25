package com.zl.spring.injection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zl.spring.bean.User;

public class UserTest {
	// set注入
	@Test
	public void userTest() {
		// 1、获取spring容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/zl/spring/injection/applicationContext.xml");
		// 2、获取User对象
		User user = (User) ac.getBean("user");
		// 3、打印对象
		System.out.println(user.toString());
	}

	// 构造函数注入
	@Test
	public void user2Test() {
		// 1、获取spring容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/zl/spring/injection/applicationContext.xml");
		// 2、获取User对象
		User user = (User) ac.getBean("user2");
		// 3、打印对象
		System.out.println(user.toString());
	}

	// p名称空间注入
	@Test
	public void user3Test() {
		// 1、获取spring容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/zl/spring/injection/applicationContext.xml");
		// 2、获取User对象
		User user = (User) ac.getBean("user3");
		// 3、打印对象
		System.out.println(user.toString());
	}

	// elp表达式方式注入
	@Test
	public void user4Test() {
		// 1、获取spring容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/zl/spring/injection/applicationContext.xml");
		// 2、获取User对象
		User user = (User) ac.getBean("user4");
		// 3、打印对象
		System.out.println(user);
	}

	// 复杂类型注入
	@Test
	public void user5Test() {
		// 1、获取spring容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/zl/spring/injection/applicationContext.xml");
		// 2、获取User对象
		CollectionBean user = (CollectionBean) ac.getBean("cb");
		// 3、打印对象
		System.out.println(user);
	}
}
