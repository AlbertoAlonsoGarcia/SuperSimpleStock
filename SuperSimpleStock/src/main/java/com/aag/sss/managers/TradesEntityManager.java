/**
 * 
 */
package com.aag.sss.managers;

import java.util.ArrayList;


import com.aag.sss.model.Trade;

/**
 * @author Alberto Alonso
 *
 */
public interface TradesEntityManager {
	
	public boolean recordTrade(Trade trade) throws Exception;
	
	public ArrayList<Trade> getTrades();


}
