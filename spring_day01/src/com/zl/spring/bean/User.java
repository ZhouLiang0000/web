package com.zl.spring.bean;

public class User {
	private String name;
	private Integer age;
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User() {
//		System.out.println("user 空参构造方法");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", car=" + car + "]";
	}

	public void init() {
		System.out.println("init方法执行了。。。。。。。。。。。");
	}

	public void destroy() {
		System.out.println("destroy方法执行了。。。。。。。。。。。。");
	}

}
