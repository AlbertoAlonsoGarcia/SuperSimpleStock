/**
 * 
 */
package com.aag.sss.factories;

import com.aag.sss.factories.impl.StocksServiceFactoryImpl;
import com.aag.sss.services.StocksService;

/**
 * @author Alberto Alonso
 *
 */
public interface StocksServiceFactory {
	
	/**
	 * Singleton instance of the factory StocksServicesFactory.
	 */
	public StocksServiceFactory INSTANCE = StocksServiceFactoryImpl.getInstance();
	
	/**
	 * Gets the singleton instance of the StocksService
	 * 
	 * @return An object of type StocksService, representing a instance of the StocksService
	 */
	public StocksService getStockService();



}
