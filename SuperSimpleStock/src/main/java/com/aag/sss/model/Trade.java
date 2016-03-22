/**
 * 
 */
package com.aag.sss.model;

import java.util.Date;

/**
 * @author Alberto Alonso
 *
 */
public class Trade {
	
	private long idTrade;
	
	private Date timestamp;
	
	private Stock stock;
	
	private TradeType tradeType;
	
	private int sharesNumber;
	
	private double price;

	
	
	
	/**
	 * 
	 */
	public Trade() {
		super();
	}



	/**
	 * @param timestamp
	 * @param stock
	 * @param tradeType
	 * @param sharesNumber
	 * @param price
	 */
	public Trade(Date timestamp, Stock stock, TradeType tradeType, int sharesNumber, double price) {
		super();
		this.timestamp = timestamp;
		this.stock = stock;
		this.tradeType = tradeType;
		this.sharesNumber = sharesNumber;
		this.price = price;
	}
	
	

	/**
	 * @param stock
	 * @param tradeType
	 * @param sharesNumber
	 */
	public Trade(Stock stock, TradeType tradeType, int sharesNumber) {
		super();
		this.timestamp = new Date(System.currentTimeMillis());
		this.stock = stock;
		this.tradeType = tradeType;
		this.sharesNumber = sharesNumber;
		this.price = calculatePrice(stock, sharesNumber);
	}



	private double calculatePrice(Stock stock, int sharesNumber) {
		if(stock!=null)
			return stock.getTickerPrice()*sharesNumber;
		return -1;
	}



	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * @return the tradeType
	 */
	public TradeType getTradeType() {
		return tradeType;
	}

	/**
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	/**
	 * @return the sharesNumber
	 */
	public int getSharesNumber() {
		return sharesNumber;
	}

	/**
	 * @param sharesNumber the sharesNumber to set
	 */
	public void setSharesNumber(int sharesNumber) {
		this.sharesNumber = sharesNumber;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}



	/**
	 * @return the idTrade
	 */
	public long getIdTrade() {
		return idTrade;
	}



	/**
	 * @param idTrade the idTrade to set
	 */
	public void setIdTrade(long idTrade) {
		this.idTrade = idTrade;
	}
	
	
	

}
