package com.zl.spring.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sun.org.apache.xml.internal.security.Init;

@Component("user")
public class User {
	@Value("tom")
	private String name;
	private Integer age;
	// @Autowired 自动装配、多个对象的话不确定结合下面的Qualifier指定哪一个对象
	// @Qualifier("car")
	@Resource(name = "car") // 方式二：手动注入，指定注入的是哪个对象
	private Car car;

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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@PostConstruct // 在构造方法之后调用
	public void init() {
		System.out.println("初始化方法调用");
	}

	@PreDestroy // 在对象销毁之前调用
	public void destory() {
		System.out.println("销毁方法调用");
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", car=" + car + "]";
	}

}
