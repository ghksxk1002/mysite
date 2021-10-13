package com.douzone.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {
	
	// 어플리케이션이 올릴때 호출됨
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		String contextConfigLocation =sc.getInitParameter("contextConfigLocation");
		System.out.println("어플이케이션이 스타트 되었다...."+contextConfigLocation);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
