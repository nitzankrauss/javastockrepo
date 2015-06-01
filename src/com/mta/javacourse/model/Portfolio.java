package com.mta.javacourse.model;
import java.text.DecimalFormat;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import com.mta.*;
import com.mta.javacourse.service.PortfolioManager;
import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * This class represents a Portfolio of Stocks.
 * where the maximum of stocks in the Portfolio is 5.
 * 
 * @author nitzankrauss
 * @since 22/4/2015
 */
@SuppressWarnings("unused")
public class Portfolio implements PortfolioInterface{

	private static final int MAX_PORTFOLIO_SIZE = 5;

	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE, HOLD 
	}

	private String title;
	private StockInterface[] stocks;
	private int portfolioSize;
	private float balance;


	/**
	 * C'tor of Portfolio.
	 * Creates an instance of an array of Stocks 
	 * Set the Portfolio Size to start as 0.
	 * @param title
	 * 		  the title of the Portfolio
	 * @author nitzankrauss
	 */
	public Portfolio() {
		this.title = new String("Temporary Title");
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		this.balance = 0;
	}

	/**
	 * C'tor of Portfolio.
	 * Creates an instance of an array of Stocks
	 * Set the Portfolio Size to start as 0.
	 * @param title
	 * 		  the title of the Portfolio
	 * @author nitzankrauss
	 */
	public Portfolio(StockInterface[] stocksArray) {
		this.title = new String("Temporary Title");
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = stocksArray.length;
		this.copyStocksArray(stocksArray, stocks);
		this.balance = 0;
	}


	/**
	 * Copy C'tor of Portfolio.
	 * Creates an instance of an array of Stocks
	 * Set the Portfolio Size to start as 0. And sets the portfolio name as string received.
	 * @param title
	 * 		  the title of the Portfolio
	 * @author nitzankrauss
	 */

	public Portfolio(String string) {
		this.title = string;
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		this.balance = 0;
	}

	/**
	 * copy C'tor for Portfolio type.
	 * @param oldPortfolio
	 * @author nitzankrauss
	 */
	public Portfolio (Portfolio oldPortfolio){

		this(oldPortfolio.getTitle());
		this.portfolioSize = oldPortfolio.getPortfolioSize();
		this.updateBalance(oldPortfolio.getBalance());
		copyStocksArray(oldPortfolio.getStocks(), this.getStocks());	
	}


	/**
	 * copy an array of stock from one array (old) to new empty array (new).
	 * @param oldStockInterfaces
	 * @param  (Stock)newStockInterfaces
	 * @author nitzankrauss
	 */

	private void copyStocksArray(StockInterface[] oldStockInterfaces, StockInterface[] newStockInterfaces ){

		for(int i = 0; i<this.portfolioSize; i++){
			newStockInterfaces[i]= new Stock ((Stock)oldStockInterfaces[i]);

		}
	}

	/**
	 * Add Stock to the portfolio's array of stocks.
	 * @param stock : a refferance of Stock type
	 * @author nitzankrauss
	 */
	//ASK IF WE SHOULD CHANGE THIS FUNCTION TO BOOLEAN OR NOT?!
	public void addStock(Stock stock){

		if(this.portfolioSize == MAX_PORTFOLIO_SIZE){
			System.out.println("Can’t add new stock, portfolio can have only "+this.portfolioSize+" stocks”");
			return;
		}else if (stock == null){
			System.out.println("There is an error with stock received! (Check if it it istanciated)");
			return;
		}else {
			int i = this.findStockPlace (stock.getSymbol());
			if(i != -1){
				System.out.println("Stock already exists in portfolio.");
				return;
			}
		}

		stocks[this.portfolioSize] = stock;
		((Stock) stocks[this.portfolioSize]).setStockQuantity(0); // NOT ACTUALLY NEEDED CAUSE WHEN WE CREATE STOCK- DEFAULD IS 0.
		this.portfolioSize++;
		return;
	}


	/**
	 * Removes all stocks from portfolio with the same symbol as received. 
	 * @param stockSymbol : the stock's symbol
	 */
	public boolean removeStock(String stockName){

		if (stockName == null){
			System.out.println("The stock received is invalid!");
			return false;
		}

		int i = this.findStockPlace (stockName);	
		if(i>-1){
			if (portfolioSize > 1){
				this.sellStock(stocks[i].getSymbol(), -1);
				stocks[i] = stocks[this.portfolioSize-1];
				stocks[this.portfolioSize-1]=null;

			}else  if (this.portfolioSize == 1){
				this.sellStock(stocks[i].getSymbol(), -1);
				stocks[i]=null;
			}
			portfolioSize--;
			System.out.println("Stock "+stockName+" was deleted as per request");
			return true;
		}

		System.out.println("Stock was not found in this Portfolio");
		return false;
	}

	/**
	 * Method return true if the stock recommendation was updated to SELL otherwise return false. an error will be shown 
	 * on screen in case of one.
	 * Method will update the stock quantity as per request. In case quantity will be "-1" the entire stock quantity 
	 * will be sold
	 * 
	 * @param symbol
	 * @param quantity
	 * @return TRUE in case of success, otherwise FALSE.
	 */

	public boolean sellStock(String symbol, int quantity){

		if(symbol == null || quantity < -1){
			System.out.println("There is an error! Please check your stock symbol or stock quntity.");
			return false;
		}

		int i = this.findStockPlace (symbol);

		if(i>-1){	
			if(((Stock) this.stocks[i]).getStockQuantity() - quantity < 0){
				System.out.println("Not enough stocks to sell");
				return false;

			}else if(quantity == -1){
				this.updateBalance(((Stock) this.stocks[i]).getStockQuantity()*this.stocks[i].getBid());
				((Stock) this.stocks[i]).setStockQuantity(0);
				System.out.println("Entire stock ("+symbol+") holdings was sold succefully");
				return true;

			}else {
				this.updateBalance(quantity*this.stocks[i].getBid());
				((Stock) this.stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity()-quantity);
				System.out.println("An amount of "+quantity+" of stock ("+symbol+") was sold succefully");
				return true;
			}
		}
		System.out.println("Stock was not found in this Portfolio");
		return false; 
	}

	/**
	 * Method return true if the stock recommendation was updated to BUY otherwise return false. an error will be shown 
	 * on screen in case of one.
	 * Method will update the stock quantity as per request. In case quantity will be "-1" the entire balance quantity 
	 * will be used
	 * 
	 * @param symbol
	 * @param quantity
	 * @return TRUE in case of success, otherwise FALSE.
	 */

	public boolean buyStock(Stock stock, int quantity){
		// IF THERE IS A PROBLEM WE NEED TO CHECK THIS FUNCTION!!!!!
		if(stock == null || quantity < -1){
			System.out.println("There is an error! Please check your stock symbol or stock quntity.");
			return false;
		}

		int stockLocation = this.findStockPlace (stock.getSymbol());

		if(quantity*stock.getAsk() > this.balance){
			System.out.println("Not enough balance to complete purchase.");
			return false;
		}

		if(stockLocation == MAX_PORTFOLIO_SIZE-1){
			System.out.println("Please note that the portfolio has reached it's maximum stock capacity.");
			return false;
		}


		if(stockLocation == -1){ 	 			//THE STOCK WAS NOT FOUND IN OUR STOCKS ARRAY
			this.addStock(stock);				//NEED TO ADD IT TO THE PORTFOLIO ARRAY

		}

		if(quantity == -1){
			stockLocation = this.findStockPlace (stock.getSymbol());
			int howManyToBuy = (int)this.balance/(int)this.stocks[stockLocation].getAsk();
			this.updateBalance(-howManyToBuy*this.stocks[stockLocation].getAsk());
			((Stock) this.stocks[stockLocation]).setStockQuantity(((Stock) this.stocks[stockLocation]).getStockQuantity()+howManyToBuy);
			System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
					+ "was bought succefully.");
			return true;

		}else {
			stockLocation = this.findStockPlace (stock.getSymbol());
			this.updateBalance(-quantity*this.stocks[stockLocation].getAsk());
			((Stock) this.stocks[stockLocation]).setStockQuantity(((Stock) stocks[stockLocation]).getStockQuantity()+quantity);
			System.out.println("An amount of "+quantity+" of stock ("+stock.getSymbol()+") was bought succefully");
			return true;
		}
	}


	/**
	 * Method calculates the portfolio's total stocks value.
	 * @return float representing portfolio's total stocks value.
	 * @author nitzankrauss
	 */
	public float getStocksValue(){
		float totalValue =0;
		for(int i = 0; i<this.portfolioSize ;i++){
			totalValue += ((Stock) this.stocks[i]).getStockQuantity()*this.stocks[i].getBid();
		}
		return totalValue;		
	}

	/**
	 * Method calculates the portfolio's total value.
	 * @return float representing portfolio's total value.
	 * @author nitzankrauss
	 */
	public float getTotalValue(){

		return this.getStocksValue()+this.balance;		
	}


	/**
	 * Method uses the portfolio's stock details.
	 * @return string with portfolio's details in HTML code.
	 */
	public String getHtmlString(){
		DecimalFormat decimalFormat=new DecimalFormat("#.#"); // SHOULD WE USE IT OR NOT?
		String htmlResString = new String();
		htmlResString = htmlResString+"<h1>"+this.getTitle()+"</h1> <br>";

		for(int i=0; i<portfolioSize; i++)
		{
			Stock tempStock = (Stock) stocks[i];
			if (tempStock != null){
				htmlResString = htmlResString + tempStock.getHtmlDescription()+"<br>";
			}
		}
		htmlResString += "Total Portfolio Value :"+this.getTotalValue()+ "$, "+
				"Total Stocks Value :"+this.getStocksValue()+"$, "+"Balance :"+this.balance+"$.";
		return htmlResString;	
	}


	/**
	 * method receives amount and calculates the current balance.
	 * @param amount
	 * @author nitzankrauss
	 */
	public void updateBalance (float amount){
		float tempBalance = this.balance + amount;
		if(tempBalance < 0){
			System.out.println("Please note you may not change balance to negative amount!");
		}else {
			this.balance = tempBalance;
			System.out.println("Balance has been updated to "+ this.balance);
		}

	}

	/**
	 * Find the place of a stock in stocks array.
	 * @param stockToFind
	 * @return place of a stock in the stocks array.
	 * 		 -1 if the stock was not found in the array.
	 */
	private int findStockPlace (String stockToFind){
		for(int i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return i;
			}
		}
		return -1;
	}

	/**
	 * Find the place of a stock in stocks array.
	 * @param stockToFind
	 * @return .
	 */
	public StockInterface findStock (String stockToFind){
		int i = 0;
		for( i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return this.stocks[i];
			}
		}
		return null;
	}

	/**
	 * return the logical portfolio size
	 * @param array
	 * @return
	 */
	private int getPortfolioSizeMethod(StockInterface[] array){
		int i=0;
		for (i=0; i< array.length ; i++){
			if (array[i] == null){
				return i;
			}
		}
		return i;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public StockInterface[] getStocks() {
		return stocks;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	public static int getMaxSize() {
		return MAX_PORTFOLIO_SIZE;
	}
	public int getPortfolioSize() {
		return portfolioSize;
	}
	public float getBalance() {
		return balance;
	}

	//	public static void main(String [] args){
	//		
	//		PortfolioManager portfolioManager= new PortfolioManager();
	//		Portfolio portfolio = portfolioManager.getPortfolio();
	//		portfolio.getHtmlString();
	//	}
}