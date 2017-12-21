package net.spring.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
	private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app.xml");
	
	
	private AppContext() {
		super();
	}

	public static ApplicationContext getInstance() {
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		return applicationContext.getBean(beanName, clazz);
	}
	
	public static String[] getBeanNamesForType(Class<?> clazz) {
		return applicationContext.getBeanNamesForType(clazz);
	}
}
