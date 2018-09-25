package com.zl.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zl.spring.bean.User;


public class UserTest {
	@Test
	public void userTest() {
		//1、获取spring容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2、获取User对象
		User user = (User) ac.getBean("user");
		//3、打印对象
		System.out.println(user.toString());
	}
}
