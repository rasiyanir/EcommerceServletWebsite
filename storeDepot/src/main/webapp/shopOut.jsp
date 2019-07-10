<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isELIgnored="false" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<c:choose>
			<c:when test = "${option == 'add'}">
				<h3>You successfully added ${cartItemQuantity} - ${cartItemName} each priced $ ${cartItemPrice} to the cart </h3>
			</c:when>
			<c:when test = "${option == 'delete'}">
				<h3>You successfully deleted ${cartItemQuantity} - ${cartItemName} each priced $ ${cartItemPrice} from the cart </h3>
			</c:when>
			<c:when test = "${option == 'excessQuantity'}">
				<h3>Too much Quantity!!!!  ${cartItemQuantity} - ${cartItemName} each priced $ ${cartItemPrice} </h3>
			</c:when>
			<c:otherwise>
				<h3>Sorry ${cartItemQuantity} - ${cartItemName} each priced $ ${cartItemPrice} is not in the cart or check the quantity </h3>
			</c:otherwise>
		</c:choose>
		<form action="Products" method="post">
			<input type="submit" value="Continue Shopping">
		</form>
		<br>
		<form action="viewCart" method="post">
			<input type="submit" value="View Cart">
		</form>
	</div>
</body>
</html>