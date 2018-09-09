package com.ai.web.proxy;

public class Target implements TargetIntenface{

	@Override
	public void method1() {
		System.out.println("method1   -----------   is runing");
	}

	@Override
	public String method2() {
		System.out.println("method2   -----------   is runing");
		return "method2";
	}

}
