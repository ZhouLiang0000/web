package com.zl.spring.annotationaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("myAdvice")
@Aspect
public class MyAdvice {
	// 前置通知 目标方法运行之前调用
	// 后置通知(如果出现异常将不会调用) 目标方法运行之后调用
	// 环绕通知 目标方法运行之前后调用
	// 异常拦截通知 出现异常调用
	// 后置通知(无论是否出现异常都会调用) 在目标方法之后调用
	@Before("execution(* com.zl.spring.service.*ServiceImpl.*(..))")
	public void before() {
		System.out.println("前置通知。。。。。。。");
	}

	@AfterReturning("execution(* com.zl.spring.service.*ServiceImpl.*(..))")
	public void afterReturning() {
		System.out.println("后置通知。。。。。。。如果出现异常将不会调用");
	}

	@Around("execution(* com.zl.spring.service.*ServiceImpl.*(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		System.out.println("环绕通知。。。前。。。。");
		Object proceed = point.proceed();// 调用目标方法
		System.out.println("环绕通知。。。后。。。。");
		return proceed;
	}

	@AfterThrowing("execution(* com.zl.spring.service.*ServiceImpl.*(..))")
	public void afterException() {
		System.out.println("异常通知。。。。。。。");
	}

	@After("execution(* com.zl.spring.service.*ServiceImpl.*(..))")
	public void after() {
		System.out.println("后置通知。。。。。。。无论是否出现异常都会调用");
	}
}
