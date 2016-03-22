/**
 * 
 */
package com.aag.sss.managers.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aag.sss.managers.TradesEntityManager;
import com.aag.sss.model.Trade;

/**
 * @author Alberto Alonso
 *
 */
public class TradesEntityManagerImpl implements TradesEntityManager {

	private Logger logger = Logger.getLogger(TradesEntityManagerImpl.class);
	
	private ArrayList<Trade> trades ;
	
	
	
	public TradesEntityManagerImpl() {
		super();
		trades = new ArrayList<Trade>();
	}


	public ArrayList<Trade> getTrades() {
		return trades;
	}

	/**
	 * 
	 * @param trades
	 */
	public void setTrades(ArrayList<Trade> trades) {
		this.trades = trades;
	}
	

	/**
	 * @return boolean indicating if the operation has been correctly done 
	 * @param Trade
	 */
	public boolean recordTrade(Trade trade) throws Exception {
		boolean result = false;
		logger.info("Recording trade");
		try{
			result = trades.add(trade);
		}catch(Exception exception){
			logger.error("Error occurred recording a trade ");
			
			throw new Exception("Error occurred recording a trade", exception);
		}
		return result;
	}

}
