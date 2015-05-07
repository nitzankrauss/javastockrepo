package com.mta.javacourse.model;
import com.mta.javacourse.*;
@SuppressWarnings("unused")

public class Portfolio {
	
	private static final int BUY = 0;
	private static final int SELL = 1;
	private static final int REMOVE = 2;
	private static final int HOLD = 3;
	
	private static final int MAX_PORTFOLIO_SIZE = 5;
	
	private String title;
	private Stock[] stocks;
	private int portfolioSize;


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
		
		this.setTitle(portfolioToCopy.getTitle());
		this.setPortfolioSize(portfolioToCopy.portfolioSize);
		
		//copy an array of stock from one array to new empty array;
		for (int i=0 ; i<this.portfolioSize; i++){
			Stock tmp = new Stock (portfolioToCopy.getStocks()[i]);
			this.addStock (tmp);
		}
	}
	
	/**
	 * Add Stock to the portfolio's array of stocks.
	 * @param stock : a referance of Stock type
	 * @author NitzanKrauss
	 */
	

	public void addStock(Stock stock){
		
		if(portfolioSize<MAX_PORTFOLIO_SIZE && stock != null){
			stocks[this.portfolioSize] = stock;
			this.portfolioSize++;
		}
		
		else {
			System.out.println("Sorry, Portfolio is Full OR Stock is NULL");
		}
	}
	
	/**
	 * remove first stock from portfolio
	 * @author nitzankrauss
	 * @param stock
	 */
	public void removeFirstStock (Stock stock){
		
		if (this.portfolioSize > 1 ){
			this.stocks[0] = this.stocks[this.portfolioSize - 1];
		}
		else{
			this.stocks[portfolioSize-1] = null;
		}
		this.portfolioSize --;
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
	
	
	
}






















