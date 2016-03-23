/**
 * 
 */
package com.aag.sss.services.impl;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.aag.sss.exceptions.NoDataException;
import com.aag.sss.managers.StocksEntityManager;
import com.aag.sss.managers.TradesEntityManager;
import com.aag.sss.model.Stock;
import com.aag.sss.model.Trade;
import com.aag.sss.services.StocksService;
import com.aag.sss.utils.MathUtils;



/**
 * @author Alberto Alonso
 *
 */
public class StocksServiceImpl implements StocksService {
	
	private StocksEntityManager stocksEntityManager;
	private TradesEntityManager tradesEntityManager;
	
	private Logger logger = Logger.getLogger(StocksServiceImpl.class);

	/**
	 * @return the stocksEntityManager
	 */
	public StocksEntityManager getStocksEntityManager() {
		return stocksEntityManager;
	}

	/**
	 * @param stocksEntityManager the stocksEntityManager to set
	 */
	public void setStocksEntityManager(StocksEntityManager stocksEntityManager) {
		this.stocksEntityManager = stocksEntityManager;
	}

	/**
	 * @return the tradesEnitityManager
	 */
	public TradesEntityManager getTradesEntityManager() {
		return tradesEntityManager;
	}

	/**
	 * @param tradesEnitityManager the tradesEnitityManager to set
	 */
	public void setTradesEntityManager(TradesEntityManager tradesEntityManager) {
		this.tradesEntityManager = tradesEntityManager;
	}

	public double getDividendYield(String symbol) throws Exception {
		double dividendYield = -1.0;

		try{
			Stock stock = stocksEntityManager.getStockBySymbol(symbol);

			// If the stock is not supported the a exception is raised
			if(stock==null){
				throw new Exception("The stock symbol ["+symbol+"] is not supported by the Super Simple Stock system.");
			}
			
			// Ticker price with value zero does not make any sense and could produce a zero division			
			if(stock.getTickerPrice() == 0.0)
				throw new NoDataException("There is no data to get ticker price for the stock ["+symbol+"].");
			else if(stock.getTickerPrice() <= 0.0){
				throw new Exception("The ticker price for the stock ["+symbol+"] should be greater than zero (0).");
			}
			
			dividendYield = stock.getDividendYield();

			

		}catch(NoDataException nde){
			throw nde;
		}catch(Exception exception){
			throw new Exception("Error calculating Dividend Yield for the stock symbol: "+symbol+".", exception);
		}
		return dividendYield;
	}

	
	public double getPERatio(String symbol) throws Exception {
		double peRatio = -1.0;
		try{
			
			logger.info("Calculating Ratio for "+symbol);
			Stock stock = stocksEntityManager.getStockBySymbol(symbol);
			
			
			// If the stock is not supported the a exception is raised
			if(stock==null){
				throw new Exception("The stock symbol ["+symbol+"] is not supported by the Super Simple Stock system.");
			}

			// Ticker price with value zero does not make any sense and could produce a zero division			
			if(stock.getTickerPrice() == 0.0)
				throw new NoDataException("There is no data to get ticker price for the stock ["+symbol+"].");
			else if(stock.getTickerPrice() < 0.0){
				throw new Exception("The ticker price for the stock ["+symbol+"] should be greater than zero (0).");
			}

			peRatio = stock.getPeRatio();
			
			logger.info("Calculated Ratio "+peRatio);


		}catch(NoDataException nde){
			throw new NoDataException("There is no data for calculating P/E Ratio for the stock symbol: "+symbol+".");
		}catch(Exception exception){
			throw new Exception("Error calculating P/E Ratio for the stock symbol: "+symbol+".", exception);
		}
		return peRatio;
	}

	
	public boolean recordTrade(Trade trade) throws Exception {
		boolean result = false;
		try{

			// trade should be an object
			if(trade==null){
				throw new Exception("Trade object to record should be a valid object and it's null.");
			}

			// stock should be an object
			if(trade.getStock()==null){
				throw new Exception("A trade should be associated with a stock and the stock for the trade is null.");
			}

			// shares quantity should be greater than zero
			if(trade.getSharesNumber()<=0){
				throw new Exception("Shares number in the trade to record should be greater than cero.");
			}

			// shares price should be greater than zero
			if(trade.getPrice()<=0.0){
				throw new Exception("Shares price in the trade to record should be greater than cero.");
			}

			result = tradesEntityManager.recordTrade(trade);

			// Update the ticker price for the stock
			if(result){
				trade.getStock().setTickerPrice(trade.getPrice());
			}


		}catch(Exception exception){
			throw new Exception("Error when trying to record a trade.", exception);
		}
		return result;
	}

	
	public double getStockPrice(String symbol) throws Exception {
		double stockPrice = 0.0;

		logger.info("getStockPrice");
		try{
			
			Stock stock = stocksEntityManager.getStockBySymbol(symbol);

			// If the stock is not supported the a exception is raised
			if(stock==null){
				throw new Exception("The stock symbol ["+symbol+"] is not supported by the Super Simple Stock system.");
			}
			

			stockPrice = calculateStockPrice(stock, 15);


		}catch(Exception exception){	
			throw new Exception("Error calculating P/E Ratio for the stock symbol: "+symbol+".", exception);

		}


		return stockPrice;
	}
	

	public double calculateStockPrice(Stock stock, int minutesRange) {
		double stockPrice = 0.0;
		logger.info("calculateStockPrice(Stock,minutesRange)");

		Collection<Trade> trades = tradesEntityManager.getTrades();
		logger.info("tradesNumber: "+trades.size());
		// Calculate the summation
		double shareQuantityAcum = 0.0;
		double tradePriceAcum = 0.0;
		for(Trade trade : trades){
			// Calculate the summation of Trade Price x Quantity
			tradePriceAcum += (trade.getPrice() * trade.getSharesNumber());
			// Acumulate Quantity
			shareQuantityAcum += trade.getSharesNumber();
		}

		// calculate the stock price
		if(shareQuantityAcum > 0.0){
			stockPrice = tradePriceAcum / shareQuantityAcum;	
		}
		logger.info("returning: "+stockPrice);

		return stockPrice;
	}

	public double getGBCEAllShareIndex() throws Exception {
		double allShareIndex = 0.0;
		
		// Calculate stock price for all stock in the system
		HashMap<String, Stock> stocks = stocksEntityManager.getStocks();
		ArrayList<Double> stockPrices = new ArrayList<Double>();
		for(String symbol: stocks.keySet()){
			Stock stock = stocksEntityManager.getStockBySymbol(symbol);
			double stockPrice = calculateStockPrice(stock, 0);
			if(stockPrice > 0){
				stockPrices.add(stockPrice);
			}
		}
		
		if(stockPrices.size()>=1){
			double[] stockPricesArray = new double[stockPrices.size()];
			
			for(int i=0; i<=(stockPrices.size()-1); i++){
				stockPricesArray[i] = stockPrices.get(i).doubleValue();
			}
			// Calculates the GBCE All Share Index
			allShareIndex = MathUtils.geometricMean(stockPricesArray);
		}
		return allShareIndex;
	}

}
