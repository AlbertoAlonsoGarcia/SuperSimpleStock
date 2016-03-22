/**
 * 
 */
package com.aag.sss.managers;

import java.util.HashMap;

import com.aag.sss.model.Stock;


/**
 * @author Alberto Alonso
 *
 */
public interface StocksEntityManager {
	
	public Stock getStockBySymbol(String symbol);
	
	public HashMap<String, Stock> getStocks();


}
