<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
<style>
thead th {
	text-align: left;
	background: lightgrey;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<h1>Add Products to Kit</h1>

	<%-- Required View Template --%>

	<%
		if (session.getAttribute("validateOrder") != null && session.getAttribute("validateOrder").equals(-1)) {
	%>
	<hr>
	<h4 style="color:Red;">No Products have been added to you kit. Please add a Product to view you kit.</h4>
	<hr>
	<%
		}
	List<ProductMaster> products = (List<ProductMaster>) request.getAttribute("products");
	%>

	<table border="1" width="100%">
		<thead>
			<th>Product Name</th>
			<!-- 			<th>Product Description</th> -->
			<th>Product Cost</th>
			<th></th>
		</thead>
		<tbody>
			<%
				for (ProductMaster product : products) {
			%>
			<tr>
				<td><%=product.getProductName()%></td>
				<td><%=product.getCost()%></td>
				<td><a
					href="user?action=addnewitem&pid=<%=product.getProductId()%>">
						<button>Add to Kit</button>
				</a></td>
			</tr>
			<%
				}
			%>
		</tbody>

	</table>
	<br />
	<a href="user?action=showkit"><button>View Kit Details</button></a>
	<br />
	<br />
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>