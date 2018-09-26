package com.zl.spring.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zl.spring.bean.User;

public class AnnotationTest {
	@Test
	public void annotationTest() {
		ApplicationContext aContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		User user = (User) aContext.getBean("user");
		System.out.println(user);
	}
}
