package com.riyaz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.riyaz.service.CartService;

/**
 * Servlet implementation class DeleteCart
 */
@WebServlet("/deleteCart")
public class DeleteCart extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String username = (String) session.getAttribute("username");
		int itemID = Integer.parseInt(request.getParameter("id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int price = Integer.parseInt(request.getParameter("price"));
		String itemName = request.getParameter("pName");
		
		session.setAttribute("cartItemName", itemName);
		session.setAttribute("cartItemQuantity", quantity);
		session.setAttribute("cartItemPrice", price);
		
		
		CartService service = new CartService();
		String deleteres =  service.deleteProduct(username, itemID, quantity, price);
		if(deleteres == "delete") {
			request.setAttribute("option", deleteres);
		}
		else {
			request.setAttribute("option", deleteres);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("viewCart");
		rd.forward(request, response);
	
		/* response.sendRedirect("shopOut.jsp"); */
	}

}
