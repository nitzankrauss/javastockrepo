package com.mta.javacourse.servlet;
import java.io.IOException;

import javax.servlet.http.*;

import com.mta.javacourse.model.*;
import com.mta.javacourse.service.*;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PortfolioManager portfolioManager= new PortfolioManager();
		Portfolio portfolio1 = portfolioManager.getPortfolio();
		resp.getWriter().println(portfolio1.getHtmlString());
		
		Portfolio portfolio2 = new Portfolio(portfolio1);
		portfolio2.setTitle("Portfolio #2");
		
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio1.removeStock(portfolio1.getStocks()[0].getSymbol());
		
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio2.getStocks()[portfolio2.getPortfolioSize()-1].setBid(55.55f);
		
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
	}


}
