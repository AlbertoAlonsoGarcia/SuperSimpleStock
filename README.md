# SuperSimpleStock

Super Simple Stock is the first approach for solving the proposed assignment. 

The solution must provide code that:
  * Calculate the dividend yield for a given stock.
  * Calculate the P/E Ratio for a given stock.
  * Record a trade, with timestamp, quantity of shares, buy or sell indicator and price for a given stock.
  * Calculate Stock Price based on trades recorded in past 15 minutes for a given stock.
  * Calculate the GBCE All Share Index using the geometric mean of prices for all stocks.

##### Solution 

The proposed solution has been built following the **SOA** aproach, by providing a service _**StockService**_ which has the five desired operations. 

It has been written in Java as an Apache Maven project that uses Spring Framework. Maven has been choosen because it helps developer with some powerful features reducing the time and complexity. Spring and its Inversion of Control have been choosen in order to make a cleaner code that makes it easier to understand and evolve.

By non defining an environment to be deployed, I have built the solution as a java library named **SuperSimpleStock-0.0.1-SNAPSHOT.jar** that will be generated if the code is downloaded and the project is run as a Maven install, which can also be done from the command line.

The most important handicap about the project has been the difiiculty of testing a system without any persistance framework/system because it is difficult to test a system without available data because it means the system looses its consistency and most of the test cases must be readapated in order to check their behaviour when there are data or not. This has been solved by adding an exception (_NoDataException_) that will be thrown when the system doesn't have enough data to calculate some of the operations.

That's why the project is a service itself but it doesn't have any functionality until no other modules are built (a deployable WebService for example) or other application uses it as a java library.

##### Test

A JUnit test _StocksServicesTest_ has been built to check that the solution satisfies the five operations. JUnit policies says that any test should be independent of the other ones so no order should be defined.

Following this approach it has been necessary that every single test works without errors and it has been necessary the addition of the NoDataException to check that system works but it won't calculate Dividend Yield or P/E Ratio if there is no trades to check the current price of a given stock. 

When the first test launched is the _Record a trade_ one, every test is done and returns a value.
  
  
