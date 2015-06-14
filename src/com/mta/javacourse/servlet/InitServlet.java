package com.mta.javacourse.servlet;

import javax.servlet.ServletException;

import org.algo.service.ServiceManager;

import com.mta.javacourse.exceptions.BalanceException;
import com.mta.javacourse.exceptions.PortfolioFullException;
import com.mta.javacourse.exceptions.StockAlreadyExistsException;
import com.mta.javacourse.exceptions.StockNotExistException;
import com.mta.javacourse.service.PortfolioManager;

@SuppressWarnings("serial")
public class InitServlet extends  javax.servlet.http.HttpServlet{

	@Override
	public void init() throws ServletException {
		super.init();
		PortfolioManager pm = new PortfolioManager();
		ServiceManager.setPortfolioManager(pm);	

	}
}