package com.zl.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

//通知类定义
public class MyAdvice {
	// 前置通知 目标方法运行之前调用
	// 后置通知(如果出现异常将不会调用) 目标方法运行之后调用
	// 环绕通知 目标方法运行之前后调用
	// 异常拦截通知 出现异常调用
	// 后置通知(无论是否出现异常都会调用) 在目标方法之后调用

	public void before() {
		System.out.println("前置通知。。。。。。。");
	}

	public void afterReturning() {
		System.out.println("后置通知。。。。。。。如果出现异常将不会调用");
	}

	public Object around(ProceedingJoinPoint point) throws Throwable {
		System.out.println("环绕通知。。。前。。。。");
		Object proceed = point.proceed();// 调用目标方法
		System.out.println("环绕通知。。。后。。。。");
		return proceed;
	}

	public void afterException() {
		System.out.println("异常通知。。。。。。。");
	}

	public void after() {
		System.out.println("后置通知。。。。。。。无论是否出现异常都会调用");
	}
}
