package com.zl.spring.create;

import com.zl.spring.bean.User;

public class UserFactory {
	public static User createUser() {
		System.out.println("静态工厂创建user对象");
		return new User();
	}

	public User createUser2() {
		System.out.println("实例工厂创建user对象");
		return new User();
	}
}
