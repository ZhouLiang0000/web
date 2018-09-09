package com.ai.web;

public class MyAnnoTest {
	@MyAnno(name = "zhangsan")
	public void show(String str) {
		System.out.println("MyAnnoTest method show is runing ----" + str);
	}
}
