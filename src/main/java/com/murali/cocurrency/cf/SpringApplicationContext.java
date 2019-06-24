package com.murali.cocurrency.cf;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {

	public static ApplicationContext CONTEXT;

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;		
	}
	
	public static Object getBean(String beanName) {
		if (null!=CONTEXT) {
			return CONTEXT.getBean(beanName);
		}
		return null;
	}

	public static boolean containsBean(String beanName) {
		return CONTEXT.containsBean(beanName);
	}
}