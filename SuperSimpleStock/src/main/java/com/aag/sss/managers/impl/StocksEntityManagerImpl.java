/**
 * 
 */
package com.aag.sss.managers.impl;


import java.util.HashMap;

import org.apache.log4j.Logger;

import com.aag.sss.managers.StocksEntityManager;
import com.aag.sss.model.Stock;


/**
 * @author Alberto Alonso
 *
 */
public class StocksEntityManagerImpl implements StocksEntityManager {

	private Logger logger = Logger.getLogger(StocksEntityManagerImpl.class);
	
	/**
	 * 
	 */
	private HashMap<String, Stock> stocks;
	
	
	
	public StocksEntityManagerImpl() {
		super();
		stocks = new HashMap<String, Stock>();
	}


	/**
	 * @return HashMap of existing stocks
	 * @param symbol
	 */
	public HashMap<String, Stock> getStocks() {
		return stocks;
	}

	/**
	 * 
	 * @param stocks
	 */
	public void setStocks(HashMap<String, Stock> stocks) {
		this.stocks = stocks;
	}

	
	/**
	 * @return Stock associated to the symbol 
	 * @param symbol
	 */
	public Stock getStockBySymbol(String symbol) {
		Stock stock = null;
		try{
			if(symbol!=null){
				stock = stocks.get(symbol);
			}
		}catch(Exception exception){
			logger.error("Error occurred recovering the stock for the symbol: "+symbol+".", exception);			
		}

		return stock;
	}

}
