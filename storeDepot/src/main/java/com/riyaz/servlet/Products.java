package com.riyaz.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.riyaz.bean.ProductBean;
import com.riyaz.service.ProductService;

/**
 * Servlet implementation class Products
 */
@WebServlet("/Products")
public class Products extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ProductService service = new ProductService();
			List<ProductBean> products = service.gettingProduct();
			
			if(products != null) {
				HttpSession session = request.getSession();
				session.setAttribute("productList", products);
				response.sendRedirect("product.jsp");
			}
	}

	

}
