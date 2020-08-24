<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<%-- Required View Template --%>

	<%
	// fetch the shared data
	ProductMaster product = (ProductMaster) request.getAttribute("product");
	%>

<h2>Are you sure you want to delete the following Product</h2>

<form action="admin?action=deleteproductconfirm" method="post">
	<div>
		<div><label>Product Name: </label> <label><%=product.getProductName()%></label> </div>
		<br/>
		<div><label>Product Description: </label> <label><%=product.getProductDescription()%></label> </div>
		<br/>
		<div><label>Product Cost: </label> <label><%=product.getCost()%></label> </div>
		<br/>
		<div> <input type="submit" value="Delete Product"> </div>
	</div>
</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>