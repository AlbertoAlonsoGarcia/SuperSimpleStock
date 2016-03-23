package com.aag.sss.services;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.aag.sss.exceptions.NoDataException;
import com.aag.sss.factories.SpringServiceFactory;
import com.aag.sss.factories.StocksServiceFactory;
import com.aag.sss.managers.TradesEntityManager;
import com.aag.sss.model.Trade;




public class StocksServicesTest {
	
	Logger logger = Logger.getLogger(StocksServicesTest.class);
	/**
	 * 
	 */
	@Test
	public void recordATradeTest(){
		logger.info("ENTERING Recording a Trade Test");

		// Create the stock service and verify it's not null object
		StocksService stockService = StocksServiceFactory.INSTANCE.getStockService();
		Assert.assertNotNull(stockService);

		// Recover the trades configured in spring for the unit test
		@SuppressWarnings("unchecked")
		ArrayList<Trade> tradeList = SpringServiceFactory.INSTANCE.getBean("trades", ArrayList.class);
		Assert.assertNotNull(tradeList);
		logger.info("Trade List size: "+tradeList.size());


		try{
			// Initial trades are empty, means, trades number equls to cero (0)
			TradesEntityManager tradesEntityManager = SpringServiceFactory.INSTANCE.getBean("tradesEntityManager", TradesEntityManager.class);
			int tradesNumber = tradesEntityManager.getTrades().size();
			logger.info("Trades number: "+tradesNumber);
			Assert.assertEquals(tradesNumber, 0);

			// Insert many trades in the stock system
			for(Trade trade: tradeList){
				boolean result = stockService.recordTrade(trade);
				Assert.assertTrue(result);
			}

			// After record trades, the number of trades should be equal to the trades list
			tradesNumber = tradesEntityManager.getTrades().size();
			logger.info("Trades number: "+tradesNumber);
			Assert.assertEquals(tradesNumber, tradeList.size());


		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}

		logger.info("EXITING Recording a Trade Test...OK");

	}	

	@Test
	public void calculateDividendYieldTest(){
		logger.info("ENTERING Calculating Dividend Yield Test");
		try{
			// Create the stock service and verify it's not null object
			StocksService stockService = StocksServiceFactory.INSTANCE.getStockService();
			Assert.assertNotNull(stockService);

			TradesEntityManager tradesEntityManager = SpringServiceFactory.INSTANCE.getBean("tradesEntityManager", TradesEntityManager.class);
			int tradesNumber = tradesEntityManager.getTrades().size();
			
			
			
			logger.info("Trades number: "+tradesNumber);

			// Calculates the dividend yield for the stock symbol
			String[] symbols = {"TEA", "POP", "ALE", "GIN", "JOE"};
			//String[] symbols = {"TEA"};
			for(String symbol: symbols){
				double dividendYield = -1.0;
				dividendYield = stockService.getDividendYield(symbol);
				logger.info(symbol+" - DividendYield calculated: "+dividendYield);
				Assert.assertTrue(dividendYield >= 0.0);
			}

		}catch(NoDataException exception){
			logger.info(exception);
			Assert.assertTrue(true);
		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}
		logger.info("EXITING Calculating Dividend Yield Test...OK");	
	}

	/*
	@Test
	public void calculateDividendYieldTestAfterTradesRecording(){
		logger.info("Calculating Dividend Yield Test After Trades Recording");

		try{
			// Create the stock service and verify it's not null object
			StocksService stockService = StocksServiceFactory.INSTANCE.getStockService();
			Assert.assertNotNull(stockService);

			TradesEntityManager tradesEntityManager = SpringServiceFactory.INSTANCE.getBean("tradesEntityManager", TradesEntityManager.class);
			int tradesNumber = tradesEntityManager.getTrades().size();
			
			ArrayList<Trade> tradeList = SpringServiceFactory.INSTANCE.getBean("trades", ArrayList.class);
			
			for(Trade trade:tradeList)
			{
				boolean result = stockService.recordTrade(trade);
			}
						
			logger.info("Trades number recorded: "+tradeList.size());

			// Calculates the dividend yield for the stock symbol
			String[] symbols = {"TEA", "POP", "ALE", "GIN", "JOE"};
			//String[] symbols = {"TEA"};
			for(String symbol: symbols){
				double dividendYield = -1.0;
				dividendYield = stockService.getDividendYield(symbol);
				logger.info(symbol+" - DividendYield calculated: "+dividendYield);
				Assert.assertTrue(dividendYield >= 0.0);
			}
			
			stockService.s

		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}
		
		

		logger.info("Finish calculateDividendYieldTest ...OK");
	}
	*/

	@Test
	public void calculatePERatioTest(){
		logger.info("ENTERING Calculating PE Ratio Test");


		try{
			// Create the stock service and verify it's not null object
			StocksService stockService = StocksServiceFactory.INSTANCE.getStockService();
			Assert.assertNotNull(stockService);

			TradesEntityManager tradesEntityManager = SpringServiceFactory.INSTANCE.getBean("tradesEntityManager", TradesEntityManager.class);
			int tradesNumber = tradesEntityManager.getTrades().size();
			
			logger.info("Trades number: "+tradesNumber);

			// Calculates the P/E Ratio for the stock Symbol
			String[] symbols = {"TEA", "POP", "ALE", "GIN", "JOE"};
			for(String symbol: symbols){
				logger.info(symbol+" - calculating P/E Ratio for: "+symbol);
				double peRatio = -1.0;
				peRatio = stockService.getPERatio(symbol);
				logger.info(symbol+" - P/E Ratio calculated: "+peRatio);
				Assert.assertTrue(peRatio > 0.0);
			}
		}catch(NoDataException exception){
			logger.info(exception);
			Assert.assertTrue(true);
		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}

		logger.info("EXITING Calculating PE Ratio Test...OK");
	}


	/**
	 * 
	 */
	@Test
	public void calculateStockPriceTest(){
		try{
			logger.info("ENTERING Calculating Stock Price Test");
			// Create the stock service and verify it's not null object
			StocksService stockService = StocksServiceFactory.INSTANCE.getStockService();
			Assert.assertNotNull(stockService);

			TradesEntityManager tradesEntityManager = SpringServiceFactory.INSTANCE.getBean("tradesEntityManager", TradesEntityManager.class);
			int tradesNumber = tradesEntityManager.getTrades().size();
			logger.info("Trades number: "+tradesNumber);
			
			// Calculates the Stock Price for all stocks
			String[] symbols = {"TEA", "POP", "ALE", "GIN", "JOE"};
			//String[] symbols = {"TEA"};
			for(String symbol: symbols){
				double stockPrice = stockService.getStockPrice(symbol);
				logger.info(symbol+" - Stock Price calculated: "+stockPrice);
				Assert.assertTrue(stockPrice >= 0.0);
			}

			
		}catch(NoDataException exception){
			logger.error(exception);
			Assert.assertTrue(true);
		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}
		logger.info("EXITING Calculating Stock Price Test...OK");
	}

	/**
	 * 
	 */
	@Test
	public void calculateGBCEAllShareIndexTest(){
		try{
			logger.info("ENTERING GBCE All Share Index Test");

			// Create the stock service and verify it's not null object
			StocksService stockService = StocksServiceFactory.INSTANCE.getStockService();
			Assert.assertNotNull(stockService);

			TradesEntityManager tradesEntityManager = SpringServiceFactory.INSTANCE.getBean("tradesEntityManager", TradesEntityManager.class);
			int tradesNumber = tradesEntityManager.getTrades().size();
			logger.info("Trades number: "+tradesNumber);
			
			double GBCEAllShareIndex = -1.0;
			GBCEAllShareIndex = stockService.getGBCEAllShareIndex();
			logger.info("GBCE All Share Index: "+GBCEAllShareIndex);
			Assert.assertTrue(GBCEAllShareIndex > 0.0);
			
		}catch(NoDataException exception){			
			Assert.assertTrue(true);
		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}
		
		logger.info("EXITING GBCE All Share Index Test");

	}
	

}
