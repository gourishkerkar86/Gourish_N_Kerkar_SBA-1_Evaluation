<%@page import="com.iiht.evaluation.coronokit.model.KitDetail"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<style>
thead th {
	text-align: left;
	background: lightgrey;
}
</style>

<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<h1>View Kit Details</h1>
	<br>
	<%-- Required View Template --%>

	<%
		List<KitDetail> products = (List<KitDetail>) session.getAttribute("KitDetailList");
	%>

	<table border="1" width="100%">
		<thead>
			<th>Product Name</th>
			<th>Cost per Product</th>
			<th>Product Quantity</th>
			<th>Total Cost</th>
		</thead>
		<tbody>
			<%
				int totalQuantity = 0;
			int totalAmount = 0;
			for (KitDetail product : products) {
				totalQuantity += product.getQuantity();
				totalAmount += (product.getAmount() * product.getQuantity());
			%>
			<tr>
				<td><%=product.getProductName()%></td>
				<td><%=product.getAmount()%></td>
				<td><%=product.getQuantity()%></td>
				<td><%=product.getAmount() * product.getQuantity()%></td>
			</tr>
			<%
				}
			%>

			<tr>
				<td><b>Total</b></td>
				<td></td>
				<td><b><%=totalQuantity%></b></td>
				<td><b><%=totalAmount%></b></td>

			</tr>
		</tbody>

	</table>



	<hr />

	<a href="user?action=showproducts"><button>Back</button></a>
	<a href="user?action=placeorder"><button>Proceed to Enter
			Address and Place Order</button></a>
	<br>
	<br>

	<jsp:include page="footer.jsp" />
</body>
</html>