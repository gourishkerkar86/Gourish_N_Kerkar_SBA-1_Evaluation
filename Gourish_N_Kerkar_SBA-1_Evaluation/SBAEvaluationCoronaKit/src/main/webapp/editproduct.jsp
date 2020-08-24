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
	<%
	// fetch the shared data
	ProductMaster product = (ProductMaster) request.getAttribute("product");
	%>


<form action="admin?action=updateproduct" method="post">
	<div>
		<div><label>Enter Product Name</label> <input style="color:black;" type="text" name="pname" value="<%=product.getProductName()%>"> </div>
		<br/>
		<div><label>Enter Product Description</label> <input style="color:black;" type="text" name="pdesc" value="<%=product.getProductDescription()%>"></div>
		<br/>
		<div><label>Enter Product Price</label> <input style="color:black;" type="text" name="pcost" value="<%=product.getCost()%>"></div>
		<br/>
		<div> <input type="submit" value="Update"> </div>
	</div>
</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>
