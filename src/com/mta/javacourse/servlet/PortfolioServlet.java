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
		Portfolio portfolio = portfolioManager.getPortfolio();
		resp.getWriter().println(portfolio.getHtmlString());
		
	}
}
