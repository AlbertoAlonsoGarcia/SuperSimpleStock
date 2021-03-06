/**
 * 
 */
package com.aag.sss.model;

import org.apache.log4j.Logger;

import com.aag.sss.exceptions.NoDataException;



/**
 * @author Alberto Alonso
 *
 */
public class Stock {
	

	private Logger logger = Logger.getLogger(Stock.class);


	private String symbol;

	private StockType stockType;

	private double lastDividend;
	
	private double fixedDividend;

	private double parValue;

	private double tickerPrice=0.0;

	
	
	/**
	 * 
	 */
	public Stock() {
		super();
	}



	/**
	 * @param symbol
	 * @param stockType
	 * @param lastDividend
	 * @param fixedDividend
	 * @param parValue
	 * @param tickerPrice
	 */
	public Stock(String symbol, StockType stockType, double lastDividend, double fixedDividend, double parValue,
			double tickerPrice) {
		super();
		this.symbol = symbol;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.tickerPrice = tickerPrice;
	}



	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}



	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}



	/**
	 * @return the stockType
	 */
	public StockType getStockType() {
		return stockType;
	}



	/**
	 * @param stockType the stockType to set
	 */
	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}



	/**
	 * @return the lastDividend
	 */
	public double getLastDividend() {
		return lastDividend;
	}



	/**
	 * @param lastDividend the lastDividend to set
	 */
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}



	/**
	 * @return the fixedDividend
	 */
	public double getFixedDividend() {
		return fixedDividend;
	}



	/**
	 * @param fixedDividend the fixedDividend to set
	 */
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}



	/**
	 * @return the parValue
	 */
	public double getParValue() {
		return parValue;
	}



	/**
	 * @param parValue the parValue to set
	 */
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}



	/**
	 * @return the tickerPrice
	 */
	public double getTickerPrice() {

		return tickerPrice;
	}



	/**
	 * @param tickerPrice the tickerPrice to set
	 */
	public void setTickerPrice(double tickerPrice) {
		logger.debug("Changing ticker price to: "+tickerPrice);
		this.tickerPrice = tickerPrice;
		logger.debug("Ticker price for "+symbol+": "+tickerPrice);

	} 
	
	
	
	public double getDividendYield() throws NoDataException {
		double dividendYield = -1.0;
		

		if(tickerPrice >= 0.0)
		{

			if( stockType==StockType.PREFERRED)
			{

				dividendYield = (fixedDividend * parValue ) / tickerPrice;				
			}else{

				dividendYield = lastDividend / tickerPrice;
			}
		}else
			throw new NoDataException("No data for calculating Dividend Yield for the stock symbol: "+symbol+".");
		return dividendYield;
	}
	
	/**
	 * 
	 * @return
	 * @throws NoDataException 
	 */
	public double getPeRatio() throws NoDataException {
		double peRatio = -1.0;

		if(tickerPrice > 0.0){

			peRatio = tickerPrice / this.getDividendYield();	
		}
		
		return peRatio;
	}

	
}
