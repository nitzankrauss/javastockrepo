package com.mta.javacourse.model;
import com.mta.javacourse.*;
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
	public void removeStock(String symbol) {
		if (portfolioSize == 1 || symbol.equals(stocks[portfolioSize-1].getSymbol())){
			stocks[portfolioSize-1] = null;
			portfolioSize--;
			return;
		}
		for (int i = 0; i < portfolioSize; i++){
			if (symbol == null){ 
				return;
			}
			else if (symbol.equals(stocks[i].getSymbol()))
			{
				stocks[i] = stocks[portfolioSize - 1];
				stocks[portfolioSize - 1] = null;
				portfolioSize--;
			}
		}
		return;
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
	
	public String getHtmlString(){
		
		String resString = new String();
		resString = resString+"<h1>"+this.getTitle()+"</h1> <br>";
		
		for(int i=0; i<portfolioSize; i++)
		{
			Stock tempStock = stocks[i];
			if (tempStock != null){
				resString = resString + tempStock.getHtmlDescription()+"<br>";
			}
		}
		
		return resString;	
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






















