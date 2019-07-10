package com.riyaz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.riyaz.bean.CartBean;
import com.riyaz.service.CartService;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/addCart")
public class AddCart extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String username = (String) session.getAttribute("username");
		int itemID = Integer.parseInt(request.getParameter("id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int price = Integer.parseInt(request.getParameter("price"));
		String itemName = request.getParameter("pName");
		//String option = request.getParameter("option");
		//String option = null;
		

		session.setAttribute("cartItemName", itemName);
		session.setAttribute("cartItemQuantity", quantity);
		session.setAttribute("cartItemPrice", price);
		
		
		CartService service = new CartService();
		String addRes = service.addProduct(username, itemID, quantity, price);
		
		if(addRes == "add") {
			request.setAttribute("option", addRes);
		}
		else {
			request.setAttribute("option", addRes);
		}
		
			
			RequestDispatcher rd = request.getRequestDispatcher("viewCart");
			rd.forward(request, response);
		
		 
		 
	}

	

}
