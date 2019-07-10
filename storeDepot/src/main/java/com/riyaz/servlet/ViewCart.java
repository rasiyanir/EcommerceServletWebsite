package com.riyaz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.riyaz.bean.CartBean;
import com.riyaz.service.CartService;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet("/viewCart")
public class ViewCart extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String username = (String) session.getAttribute("username");
		
		CartService service = new CartService();
		
		String msg = service.checkingMessage(username);
		
		List<CartBean> cartList = service.gettingCart(username);
		
		int checkoutTotal = service.getFinalTotal(username);
		
		String optioncoming = (String) request.getAttribute("option");
		
		session.setAttribute("option", optioncoming);
		
		
		session.setAttribute("msg", msg);
		session.setAttribute("cartList", cartList);
		session.setAttribute("checkoutTotal", checkoutTotal);
		
		
		response.sendRedirect("cart.jsp");
	}

}
