package com.riyaz.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.riyaz.bean.CartBean;
import com.riyaz.bean.CheckoutBean;
import com.riyaz.service.CheckoutService;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/checkout")
public class Checkout extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
	
		
		CheckoutService service = new CheckoutService();
		List<CheckoutBean> checkoutList =  service.gettingCheckout(username);
		
		
		
		
		service.deletingCartUpdatingItem(username);
		
		if(checkoutList != null) {
			session.setAttribute("checkoutList", checkoutList);
			response.sendRedirect("purchases.jsp");
		}
	}

}
