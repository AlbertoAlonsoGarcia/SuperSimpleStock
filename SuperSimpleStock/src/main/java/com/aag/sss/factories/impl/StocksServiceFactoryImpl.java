/**
 * 
 */
package com.aag.sss.factories.impl;

import com.aag.sss.factories.SpringServiceFactory;
import com.aag.sss.factories.StocksServiceFactory;
import com.aag.sss.services.StocksService;



/**
 * @author Alberto Alonso
 *
 */
public class StocksServiceFactoryImpl implements StocksServiceFactory {

	
	/**
	 * Private constructor for the factory which prevents new instance
	 */
	private StocksServiceFactoryImpl(){
		/**
		 * 1. Load the spring context for the engine
		 */
		SpringServiceFactory.INSTANCE.getClass();
	}
	
	
	/**
	 * Holder class for the singleton factory instance. {@link StocksServicesFactoryHolder} is 
	 * loaded on the first execution of {@link StocksServicesFactoryImpl#getInstance()} or the first 
	 * access to {@link StocksServicesFactoryHolder#INSTANCE}, not before.
	 */
	private static class StocksServicesFactoryHolder{
		private static final StocksServiceFactory INSTANCE = new StocksServiceFactoryImpl();
	}
	
	/**
	 * Gets the singleton instance of the factory of the services in the Super Simple Stock application. 
	 * 
	 * @return An object of the StocksServiceFactory, which represents the factory to access to all 
	 * services in the Simple Stock application. 
	 */
	public static StocksServiceFactory getInstance(){
		return StocksServicesFactoryHolder.INSTANCE;
	}
	
	/**
	 * @return An object of the StocksService. 
	 */
	
	public StocksService getStockService() {
		return SpringServiceFactory.INSTANCE.getBean("stocksService", StocksService.class);
	}
	


}
