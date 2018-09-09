package com.ai.web;

import java.lang.reflect.Method;

public class MyAnnoParser {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		// 解析show方法上面的@MyAnno
		// 获得show方法的字节码文件
		Class clazz = MyAnnoTest.class;
		Method method = clazz.getMethod("show", String.class);
		MyAnno anno = method.getAnnotation(MyAnno.class);
		// 获得MyAnno上面的属性值
		System.out.println(anno.name());
	}
}
