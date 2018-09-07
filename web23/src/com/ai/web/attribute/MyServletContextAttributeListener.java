package com.ai.web.attribute;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener{

	@Override
	public void attributeAdded(ServletContextAttributeEvent scab) {
		//添加的属性
		System.out.println(scab.getName());
		System.out.println(scab.getValue());
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scab) {
		//移除的属性
		System.out.println(scab.getName());
		System.out.println(scab.getValue());
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		//修改前属性
		System.out.println(scab.getName());
		System.out.println(scab.getValue());
		
	}

}
