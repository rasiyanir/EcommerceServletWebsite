<%@page import="java.util.List" %>
<%@page import="com.riyaz.bean.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isELIgnored="false" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <form class="form-inline" action="viewCart" method="post">
    	<button class="btn btn-outline-light my-2 my-sm-0" type="submit">View Cart</button>
  	</form>
  	
  		<c:if test="${username != null}">
			<h2 class="ml-auto">Welcome ${username}</h2>
		</c:if>
    <form class="form-inline ml-auto" action="Logout">
    	<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Logout</button>
  	</form>
  </div>
</nav>




	

	

	<%-- <div align="center" style="background-color: orange;color: white;">
		<c:if test="${username != null}">
			<p>Welcome ${username}</p>
		</c:if>
		
		
		
		<h2>PRODUCT PAGE</h2>
			<form action="viewCart" method="post">
				<input type="submit" value="View Cart">
			</form>
		<div style="position: absolute;right: 10px;">
			<form action="Logout">
				<input type="submit" value=" Logout ">
			</form>
		</div>
	</div> --%>

	
	
	
	
	<c:forEach items="${productList}" var="i">
	
			<div class="card shadow-sm" style="width: 18rem;margin: 10px;display: inline-flex;text-align: center;">
  				<img src="..." class="card-img-top" alt="...">
  				<div class="card-body">
    				<h5 class="card-title">
    				
    				<c:out value="${i.itemName}"></c:out>
					<br>
					Price: $<c:out value="${i.itemPrice}"></c:out>
					<br>
					Quantity: <c:out value="${i.itemCount}"></c:out>
    				
    				</h5>
    				
    				<form action="addCart" method="post">
						
						<input type="hidden" name="pName" value="${i.itemName}" >
						<input type="hidden" name="price" value="${i.itemPrice}" >
						<input type="hidden" name="id" value="${i.itemID}">
						
						<div class="input-group mb-3">
						  <input type="number" class="form-control" placeholder=" Enter Quantity " name="quantity"  required="required" min="1" max="${i.itemCount}">
						  <div class="input-group-append">
						    <button type="submit" class="btn btn-outline-danger btn-light text-danger">Add to Cart</button>
						  </div>
						</div>
						
						
						
						
						<%-- <div style="display:inline-flex;">
							<input type="number" class="form-control"  placeholder=" Enter Quantity "  name="quantity"  required="required" min="1" max="${i.itemCount}">
    						<button type="submit" class="btn btn-outline-danger btn-light text-danger">Add to Cart</button>
						</div> --%>
						
					</form>	
  				</div>
			</div>
	
	
	
		
			
			<%-- <form action="deleteCart" method="post">
				<input type="hidden" name="pName" value="${i.itemName}" >
				<input type="hidden" name="price" value="${i.itemPrice}" >
				<input type="hidden" name="id" value="${i.itemID}">
				<input type="number" placeholder="Enter Quantity" name="quantity" required="required" min="1" max="${i.itemCount}">
				<input type="submit" value="Delete from Cart">
			</form> --%>
		</div>
	</c:forEach>
	
	

	<%-- <c:forEach items="${productList}" var="i">
		<div style="display:inline;" align="center">
			<div style="display:inline;">
				<p style="background-color: cyan; height: 100px; ">
					Name: <c:out value="${i.itemName}"></c:out>
					<br>
					Price: $<c:out value="${i.itemPrice}"></c:out>
					<br>
					Quantity: <c:out value="${i.itemCount}"></c:out>
					
				</p>
			</div>
			<form action="addCart" method="post">
				<!-- <input type="hidden" name="option" value="add"> -->
				<input type="hidden" name="pName" value="${i.itemName}" >
				<input type="hidden" name="price" value="${i.itemPrice}" >
				<input type="hidden" name="id" value="${i.itemID}">
				<input type="number" placeholder="Enter Quantity" name="quantity" required="required" min="1" max="${i.itemCount}">
				<input type="submit" value="Add to Cart">
			</form>
			<form action="deleteCart" method="post">
				<input type="hidden" name="pName" value="${i.itemName}" >
				<input type="hidden" name="price" value="${i.itemPrice}" >
				<input type="hidden" name="id" value="${i.itemID}">
				<input type="number" placeholder="Enter Quantity" name="quantity" required="required" min="1" max="${i.itemCount}">
				<input type="submit" value="Delete from Cart">
			</form>
		</div>
	</c:forEach> --%>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>