/**
 * 
 */
package com.aag.sss.factories.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aag.sss.factories.SpringServiceFactory;


/**
 * @author Alberto Alonso
 *
 */
public class SpringServiceFactoryImpl implements SpringServiceFactory {

	
	private Logger logger = Logger.getLogger(SpringServiceFactoryImpl.class);
	
	/**
	 * Spring context files pattern defined for the Simple Stocks application.  
	 */
	private static final String SPRING_CONTEXT_PATH = "classpath*:*-context.xml";

	/**
	 * Spring context object.
	 */
	private AbstractApplicationContext context = null;
	
	

	/**
	 * Constructor of the class. 
	 */
	private SpringServiceFactoryImpl(){
		logger.info("Loading context");
		context = new ClassPathXmlApplicationContext(SPRING_CONTEXT_PATH);
		context.registerShutdownHook();
		logger.info("Context has been correctly created");
	}
	/**
	 * Holder class for the singleton factory instance. 
	 * Is loaded on the first execution of  or the first 
	 * access to , not before.
	 */
	private static class SpringServiceFactoryHolder{
		private static final SpringServiceFactory INSTANCE = new SpringServiceFactoryImpl();
	}
	
	/**
	 * Gets the singleton instance. 
	 * 
	 * @return An object of the SpringServiceFactory, which represents the service to access to all 
	 * beans in the spring container. 
	 */
	public static SpringServiceFactory getInstance(){
		return SpringServiceFactoryHolder.INSTANCE;
	}	
	
	/**
	 * @return An object associated to the objectClass with the specified beanName
	 */
	public <T> T getBean(String beanName, Class<T> objectClass) {
		return context.getBean(beanName, objectClass);
	}


	/**
	 * @return An object associated to the objectClass 
	 */
	public <T> T getBean(Class<T> objectClass) {
		return context.getBean(objectClass);
	}

}
