package com.mta.javacourse.model;

import java.text.*;

import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

import java.util.*;

public class Stock {
	private String symbol;
	private float bid, ask;
	private java.util.Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	private SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public Stock (String newSymbol, float newBid, float newAsk, Date date){
		this.symbol = newSymbol;
		this.bid = newBid;
		this.ask = newAsk;
		this.date = date;
		this.recommendation = ALGO_RECOMMENDATION.HOLD;
		this.stockQuantity = 0;		
	}
	
	/**
	 * copy c'tor of stack class
	 * @author nitzankrauss
	 * @param stockToCopy
	 */
	
	public Stock (Stock stockToCopy){
		
		//creating new instance for date;	
		
		this.setSymbol(stockToCopy.getSymbol());
		this.setBid(stockToCopy.getBid());
		this.setAsk(stockToCopy.getAsk());
		this.date = new Date(stockToCopy.getDate().getTime());
		this.setRecommendation(stockToCopy.getRecommendation());
		this.stockQuantity = stockToCopy.getStockQuantity();
	}
	
	public String getHtmlDescription(){
		return "<b>Stock symbol: </b>"+this.getSymbol()+" <b>Ask: </b>"+this.getAsk()+"<b> Bid: </b>"+this.getBid()+
				"<b> Date: </b>"+this.formDate.format(this.getDate())+" <b>Quantity: </b>"+this.getStockQuantity();
	}
	/**
	 * geters and setters of stack class
	 * @author nitzankrauss
	 */

	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public SimpleDateFormat getFormDate() {
		return formDate;
	}

	public void setFormDate(SimpleDateFormat formDate) {
		this.formDate = formDate;
	}
	
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}


	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}


	public int getStockQuantity() {
		return stockQuantity;
	}


	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}