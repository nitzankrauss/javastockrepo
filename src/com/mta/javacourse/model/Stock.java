package com.mta.javacourse.model;

import java.text.*;
import java.util.*;

import org.algo.model.StockInterface;

import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;
/**
 * This class represents a Stock of Stocks.
 * @param symbol
 * @param bid : buy value
 * @param ask : sell value
 * @param date : creation date
 * @param recommendation : price recommendation
 * @param stockQuantity : the amount of stocks of that kind
 * @since 22/4/2015
 */
public class Stock implements StockInterface{


	private String symbol;
	private float bid, ask;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	transient private SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy");


	/**
	 * C'tor of Stock.
	 * @author nitzankrauss
	 */
	public Stock (){
		this.symbol = new String();
		this.bid = 0;
		this.ask = 0;
		this.date = new Date();
		this.recommendation = ALGO_RECOMMENDATION.HOLD;
		this.stockQuantity = 0;				
	}
	/**
	 * C'tor of Stock.
	 * @param newSymbol
	 * 		  : Stock Symbol.
	 * @param newBid
	 * 		  : Stock bid value.
	 * @param newAsk
	 * 		  : Stock ask value.
	 * @param date
	 * 		  : Creation date of the stock.
	 *@author nitzankrauss
	 */
	public Stock (String newSymbol, float newBid, float newAsk, Date date){
		this.symbol = newSymbol;
		this.bid = newBid;
		this.ask = newAsk;
		this.date = date;
		this.recommendation = ALGO_RECOMMENDATION.HOLD;
		this.stockQuantity = 0;				
	}

	/**
	 * Copy C'tor of Stock class.
	 * @param oldStock
	 * @author nitzankrauss
	 */
	public Stock (Stock oldStock)
	{

		this(oldStock.getSymbol(),oldStock.getBid(),oldStock.getAsk(),new Date(oldStock.getDate().getTime()));
		this.recommendation = oldStock.getRecommendation();
		this.stockQuantity = oldStock.getStockQuantity();
	}

	/**
	 * Method uses the stock's details.
	 * @return string with stock's details in HTML code.
	 */
	public String getHtmlDescription(){
		return "<b>Stock symbol: </b>"+this.getSymbol()+" <b>Ask: </b>"+this.getAsk()+"<b> Bid: </b>"+this.getBid()+
				"<b> Date: </b>"+this.formDate.format(this.getDate())+" <b>Quantity: </b>"+this.getStockQuantity();
	}

	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}
	//	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
	//		this.recommendation = recommendation;
	//	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public String getSymbol() {
		return this.symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getBid() {
		return this.bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public float getAsk() {
		return this.ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public Date getDate() {
		return this.date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setRecommendation(ALGO_RECOMMENDATION valueOf) {
		this.recommendation = valueOf;

	}

}