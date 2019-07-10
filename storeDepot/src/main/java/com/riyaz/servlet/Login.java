package com.riyaz.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.riyaz.service.LoginService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("user");
		String password = request.getParameter("psw");
		
		LoginService service = new LoginService();
		
		if(service.verifyUser(username, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			RequestDispatcher rd = request.getRequestDispatcher("Products");
			rd.forward(request, response);
			
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.sendRedirect("invalidlogin.jsp");
		}
	}

}
