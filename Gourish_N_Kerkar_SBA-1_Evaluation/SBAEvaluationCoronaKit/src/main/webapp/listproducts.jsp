<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>
thead th {
	text-align: left;
	background: Black;
}
</style>
<head>
<meta charset="ISO-8859-1">

	<%
		if(session.getAttribute("adminId")==null)
			response.sendRedirect("errorPage.jsp");
			
	%>

<title>Corona Kit- Products List</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<br />
	<h3>Welcome <%=session.getAttribute("adminId").toString()%></h3>
	<br />
	<a href="admin?action=logout"><button>Log Out</button></a>
	<br />

	<hr />

	<%
		// fetch the shared data
	List<ProductMaster> products = (List<ProductMaster>) request.getAttribute("products");
	%>
	<table border="1" width="100%">
		<thead>
			<th>Product Name</th>
			<th>Product Description</th>
			<th>Product Cost</th>
			<th>Edit</th>
			<th>Delete</th>
		</thead>
		<tbody>
			<%
				for (ProductMaster product : products) {
			%>
			<tr>
				<td><%=product.getProductName()%></td>
				<td><%=product.getProductDescription()%></td>
				<td><%=product.getCost()%></td>
				<td> <a href="admin?action=editproduct&pid=<%=product.getProductId()%>"> <button>Edit</button> </a> </td>
				<td> <a href="admin?action=deleteproduct&pid=<%=product.getProductId()%>"> <button>Delete</button> </a> </td>

 
				
				
			</tr>
			<%
				}
			%>
		</tbody>

	</table>

	<br />
	<a href="admin?action=newproduct"><button>Add New Product</button></a>
	<br />

	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>
