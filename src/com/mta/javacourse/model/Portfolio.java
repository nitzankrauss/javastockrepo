package com.mta.javacourse.model;
import com.mta.javacourse.*;
import java.text.DecimalFormat;
@SuppressWarnings("unused")

public class Portfolio {
	
	private static final int MAX_PORTFOLIO_SIZE = 5;
	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE, HOLD 
	}
	
	private String title;
	private Stock[] stocks;
	private int portfolioSize;
	private float balance;


	public Portfolio() {
		this.title = new String("");
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
	}
	
	/**
	 * copy C'tor for Portfolio type
	 * @param portfolioToCopy
	 * @author nitzankrauss
	 */
	public Portfolio (Portfolio portfolioToCopy){
		this();
		this.setTitle(portfolioToCopy.getTitle());
		
		//copy an array of stock from one array to new empty array;
		for (int i=0 ; i<portfolioToCopy.getPortfolioSize(); i++){
			Stock tmp = new Stock (portfolioToCopy.getStocks()[i]);
			this.addStock (tmp);
		}
	}
	
	public Portfolio(String string) {
		this.title = string;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		this.balance = 0;
	}
	
	/**
	 * Add Stock to the portfolio's array of stocks
	 * @param stock : a referance of Stock type
	 * @author NitzanKrauss
	 */
	
	public void addStock (Stock stock){

		if(portfolioSize == MAX_PORTFOLIO_SIZE){
			System.out.println("Can’t add new stock, portfolio can have only "+this.portfolioSize+" stocks”");
			return;
			}
		 else if (stock == null){
			System.out.println("There is an error with stock received");
			return;
			}
		 else {
			for(int i = 0; i< this.portfolioSize; i++){
				if(stock.getSymbol().equals(this.stocks[i].getSymbol())){
					System.out.println("Stock already exists in portfolio.");
					return;
				}
			}
		}
		stocks[this.portfolioSize] = stock;
		this.portfolioSize++;
		return;
	}
	

	/**
	 * remove stock by value
	 * @author nitzankrauss
	 * @param symbol
	 */
	public boolean removeStock(String symbol) {
		
		if (symbol == null){
			System.out.println("The stock received is invalid!");
			return false;
		}
	
		int i = this.findStock (symbol);
			
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
			System.out.println("Stock "+symbol+" was deleted as per request");
			return true;
		}
	
	System.out.println("Stock was not found in this Portfolio");
	return false;
	}
	
	/**
	 * @author nitzankrauss
	 * Find the place of a stock in stocks array.
	 * @param stockToFind
	 * @return place of a stock in the stocks array.
	 * 		 -1 if the stock was not found in the array.
	 */
	private int findStock (String stockToFind){
		for(int i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * mathod update balance according to the value has been given.
	 * @author nitzankrauss
	 * @param amount
	 */
	public void updateBalance(float amount) {
		
		if (amount < 0){
			if (this.balance < amount){
				System.out.println("Sorry! you don't have enough money available!");
			}
			else{
				this.balance = this.balance -  amount;
			}
		}
		else{
			this.balance +=amount;
		}
	}
	
	/**
	 * @author nitzankrauss
	 * sell stock by symbol and quantity
	 * @param symbol
	 * @param quantity
	 * @return
	 */
	public boolean sellStock(String symbol, int quantity){
		
		if(symbol == null || quantity < -1){
			System.out.println("Oops! ERROR! Please check your stock symbol or stock quntity.");
			return false;
		}
		
		for(int i = 0; i< this.portfolioSize; i++){

			if(this.stocks[i].getSymbol().equals(symbol) == true){

				if(this.stocks[i].getStockQuantity() - quantity < 0){
					System.out.println("Not enough stocks to sell");
					return false;

				}
				else if(quantity == -1){
					this.updateBalance(this.stocks[i].getStockQuantity()*this.stocks[i].getBid());
					this.stocks[i].setStockQuantity(0);
					System.out.println("Entire stock ("+symbol+") holdings was sold succefully");
					return true;

				}
				else {
					this.updateBalance(quantity*this.stocks[i].getBid());
					this.stocks[i].setStockQuantity(stocks[i].getStockQuantity()-quantity);
					System.out.println("An amount of "+quantity+" of stock ("+symbol+") was sold succefully");
					return true;
				}
			}

		}
		System.out.println("Stock was not found in this Portfolio");
		return false; 
	}
	
	/**
	 * Method return true if the stock recommendation was updated to BUY otherwise return false.d
	 * @author nitzankrauss
	 * @param symbol
	 * @param quantity
	 * @return TRUE in case of success, otherwise FALSE.
	 */

	public boolean buyStock(Stock stock, int quantity){
		int i = 0;
		boolean answer =false;
		if(stock == null || quantity < -1){
			System.out.println("Oops! ERROR! Please check your stock symbol or stock quntity.");
			answer= false;
		}
		if(quantity*stock.getAsk() > this.balance){
			System.out.println("Not enough balance to complete purchase.");
			answer = false;
		}
		for(i = 0; i< this.portfolioSize; i++){
			if(this.stocks[i].getSymbol().equals(stock.getSymbol()) == true){
				
				if(quantity == -1){
					int howManyToBuy = (int)this.balance/(int)this.stocks[i].getAsk();
					this.updateBalance(-howManyToBuy*this.stocks[i].getAsk());
					this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity()+howManyToBuy);
					System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
							+ "was bought succefully.");
					answer = true;

				}
				else {
					this.updateBalance(-quantity*this.stocks[i].getAsk());
					this.stocks[i].setStockQuantity(stocks[i].getStockQuantity()+quantity);
					System.out.println("An amount of "+quantity+" of stock ("+stock.getSymbol()+") was bought succefully");
					answer = true;
				}
			}

		}
		return answer;
		
	}
	
	/**
	 * Method uses the portfolio's stock details.
	 * @return string with portfolio's details in HTML code.
	 * @author nitzankrauss
	 */
	public String getHtmlString(){
		DecimalFormat decimalFormat=new DecimalFormat("#.#"); //????
		String htmlResString = new String();
		htmlResString = htmlResString+"<h1>"+this.getTitle()+"</h1> <br>";
		
		for(int i=0; i<portfolioSize; i++)
		{
			Stock tempStock = stocks[i];
			if (tempStock != null){
				htmlResString = htmlResString + tempStock.getHtmlDescription()+"<br>";
			}
		}
		htmlResString += "Total Portfolio Value :"+this.getTotalValue()+ "$, "+
		"Total Stocks Value :"+this.getStocksValue()+"$, "+"Balance :"+this.balance+"$.";
		return htmlResString;	
	}
	
	/**
	 * Method calculates the portfolio's total stocks value.
	 * @return float representing portfolio's total stocks value.
	 * @author nitzankrauss
	 */
	public float getStocksValue(){
		float totalValue =0;
		for(int i = 0; i<this.portfolioSize ;i++){
			totalValue += this.stocks[i].getStockQuantity()*this.stocks[i].getBid();
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Stock[] getStocks() {
		return stocks;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}
	
	public float getBalance() {
		return balance;
	}

	
}






















