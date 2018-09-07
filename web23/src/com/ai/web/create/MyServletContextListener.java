package com.ai.web.create;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 获取被监听的对象
//		ServletContext servletContext = sce.getServletContext();
		// 获取被监听的对象 通用的方法
//		Object source = sce.getSource();
		System.out.println("servletContext is create");
		/*
		 * 作用： 1、初始化web应用的一些对象或数据 2、加载一些初始化的配置文件 3、任务调度，定时器
		 */
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				System.out.println("开始计时了");
//			}
//		}, new Date(), 3000);
		// 银行定时计息
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String currentTime = "2018-09-07 00:00:00";
//		Date date = null;
//		try {
//			date = format.parse(currentTime);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				System.out.println("开始计时了");
//			}
//		}, date, 24 * 60 * 60 * 1000);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("serveltContext is destory");
	}

}
