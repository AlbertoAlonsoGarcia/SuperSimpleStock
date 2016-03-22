/**
 * 
 */
package com.aag.sss.services;

import com.aag.sss.model.Trade;

/**
 * @author Alberto Alonso
 *
 */
public interface StocksService {
	
	public double getDividendYield(String symbol) throws Exception;

	public double getPERatio(String symbol) throws Exception;
	
	public boolean recordTrade(Trade trade) throws Exception;
	
	public double getStockPrice(String symbol) throws Exception;
	
	public double getGBCEAllShareIndex() throws Exception;
}
