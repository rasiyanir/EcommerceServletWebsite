<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
	
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	
	response.setHeader("Expires", "0"); //Proxies
		
		if(session.getAttribute("username") == null){
			response.sendRedirect("index.jsp");
		}
	%>

	<nav class="navbar navbar-expand-lg navbar-light bg-danger text-white" >
  
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNavDropdown">
	    <a href="product.jsp" class="mr-4">
	    	<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Continue Shopping</button>
	  	</a>
	  	 <form class="form-inline mr-4" action="checkout" method="post">
    		<button class="btn btn-outline-light my-2 my-sm-0" type="submit">CHECKOUT</button>
  		</form>
	  	<h4 class="mt-2">checkout Total : ${checkoutTotal}</h4>
		<h2 class="ml-auto">Cart</h2>
	    <form class="form-inline ml-auto" action="Logout">
	    	<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Logout</button>
	  	</form>
	  </div>
	</nav>


	<!-- <div align="center" style="background-color: orange;color: white;">
		<h2>CART</h2>
	</div>
	
		<div style="position: absolute;right: 10px;">
			<form action="Logout">
				<input type="submit" value=" Logout ">
			</form>
		</div> -->
	<div align="center">
		<c:choose>
			<c:when test = "${option == 'add'}">
				<h3> </h3>
			</c:when>
			<c:when test = "${option == 'delete'}">
				<h3> </h3>
			</c:when>
			<c:when test = "${empty option}">
				<h3> </h3>
			</c:when>
			<c:when test = "${option == 'excessQuantity'}">
				<h3>Too much Quantity!!!!  ${cartItemQuantity} - ${cartItemName} each priced $ ${cartItemPrice} </h3>
			</c:when>
			<c:otherwise>
				<h3>Sorry ${cartItemQuantity} - ${cartItemName} each priced $ ${cartItemPrice} is not in stock or check the quantity </h3>
			</c:otherwise>
		</c:choose>
	</div>
	
	<c:if test="${msg != 'null'}">
		<h4>${msg}</h4>
	</c:if>
	
	
	
		<c:forEach items="${cartList}" var="i">
	
			<div class="card shadow-sm" style="width: 18rem;margin: 10px;display: inline-flex;text-align: center;">
  				<img src="..." class="card-img-top" alt="...">
  				<div class="card-body">
    				<h5 class="card-title">
    				
    				<c:out value="${i.itemName}"></c:out>
					<br>
					Price: $<c:out value="${i.itemPrice}"></c:out>
					<br>
					Quantity: <c:out value="${i.itemQuantity}"></c:out>
    				<br>
					itemTotal: $<c:out value="${i.itemTotal}"></c:out>
    				
    				</h5>
    				
    				<form action="deleteCart" method="post">
						
						<input type="hidden" name="pName" value="${i.itemName}" >
						<input type="hidden" name="price" value="${i.itemPrice}" >
						<input type="hidden" name="id" value="${i.itemID}">
						
						<div class="input-group mb-3">
						
						  <input type="number" class="form-control" placeholder=" Enter Quantity " name="quantity"  required="required" min="1" max="${i.itemQuantity}">
						  <div class="input-group-append">
						    <button type="submit" class="btn btn-outline-danger btn-light text-danger">Delete from cart</button>
						  </div>
						</div>
						
						
						
					</form>	
  				</div>
			</div>
	</c:forEach>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<%-- <div align="center">
	<c:forEach items="${cartList}" var="i">
		<!-- <div style="display:inline;"> -->
			<div style="display:block;">
				<p style="background-color: cyan; height: 100px; ">
					Id: <c:out value="${i.itemID}"></c:out>
					<br>
					Name: $<c:out value="${i.itemName}"></c:out>
					<br>
					Price: <c:out value="${i.itemPrice}"></c:out>
					<br>
					Quantity: <c:out value="${i.itemQuantity}"></c:out>
					<br>
					itemTotal: <c:out value="${i.itemTotal}"></c:out>
				</p>
				<form action="deleteCart" method="post">
					<input type="hidden" name="pName" value="${i.itemName}" >
					<input type="hidden" name="price" value="${i.itemPrice}" >
					<input type="hidden" name="id" value="${i.itemID}">
					<input type="number" placeholder="Enter Quantity" name="quantity" required="required" min="1" max="${i.itemQuantity}">
					<input type="submit" value="Delete from Cart">
				</form>
			</div>
	</c:forEach>
	
	
	<div style="background-color: lightblue">
	<p>checkout Total : ${checkoutTotal}</p>
	<a href="product.jsp"><input type="submit" value="Continue Shopping"></a> 
	<form action="checkout" method="post">
		<input type="submit" value=" CHECKOUT">
	</form>
	
	</div>
	</div>
	 --%>
	
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>