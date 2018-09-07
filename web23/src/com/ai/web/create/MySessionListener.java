package com.ai.web.create;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session is create " + se.getSession().getId() );
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session is destory");
	}

}
