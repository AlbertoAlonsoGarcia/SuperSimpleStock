/**
 * 
 */
package com.aag.sss.factories;

import com.aag.sss.factories.impl.SpringServiceFactoryImpl;

/**
 * @author Alberto Alonso
 *
 */
public interface SpringServiceFactory {
	
	/**
	 * Singleton instance .
	 */
	public SpringServiceFactory INSTANCE = SpringServiceFactoryImpl.getInstance();	

	/**
	 * Gets an object of the bean configured in the Spring context with the name <i>beanName</i> and representing for the class <i>objectClass</i>.
	 * 
	 * @param beanName	The id for the bean in the Spring context.
	 * @param objectClass The class of the bean configured with the name <i>beanName</i> in the Spring context.
	 * 
	 * @return Return an object associated to the bean configured in the Spring context with the name <i>beanName</i> and represented for the class <i>objectClass</i>.
	 */
	public <T extends Object> T getBean(String beanName, Class<T> objectClass);

	/**
	 * Gets an object of the bean configured in the Spring context for the class <i>objectClass</i>.
	 * 
	 * @param objectClass The class of the bean configured in the Spring context.
	 * 
	 * @return Return an object associated to the bean configured in the Spring context represented for the class <i>objectClass</i>.
	 */
	public <T extends Object> T getBean(Class<T> objectClass);

}
