package com.mta.javacourse.exceptions;

import org.algo.exception.PortfolioException;

public class StockAlreadyExistsException extends PortfolioException {
	
	public StockAlreadyExistsException(){
		super("Stock already exists in portfolio.");
	}
	public StockAlreadyExistsException(String symbol){
		super("Stock "+symbol+" already exists in portfolio.");
	}
}
