package com.mta.javacourse.service;
import java.util.Calendar;
import java.util.Date;

import com.mta.javacourse.*;
import com.mta.javacourse.model.*;


/**
 * class represent portfolio manager
 * @author nitzankrauss
 *
 */
public class PortfolioManager {
		

	public Portfolio getPortfolio(){
		
		Portfolio portfolio = new Portfolio("Exercise 7 portfolio ");
		portfolio.updateBalance(10000);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014,10,15);
		Date date1 = cal.getTime();
		Date date2 = cal.getTime();
		Date date3 = cal.getTime(); 
		Stock stock1;
		Stock stock2;
		Stock stock3;
		
		stock2 = new Stock("AAI", 5.5F, 5.78F,date1);
		stock1 = new Stock ("PIH",12.4F,13.1F,date2);
		stock3 = new Stock("CAAS", 31.5F, 32.2F,date3);
		
		portfolio.buyStock(stock1, 20);
		portfolio.buyStock(stock2, 30);
		portfolio.buyStock(stock3, 40);
		
		portfolio.sellStock("AAL", -1);
		portfolio.removeStock("CAAS");
		
		return portfolio;
		
	}
	
	
}

