<%@page import="com.iiht.evaluation.coronokit.model.CoronaKit"%>
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

tbody td {
	text-align: right;
	background: lightgrey;
	color: red
}
</style>

<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<h1>Order Summary</h1>
	<hr />
	<%-- Required View Template --%>

	<%
		// fetch the shared data
	CoronaKit orderSummary = (CoronaKit) request.getAttribute("OrderSummary");
	%>
	<tbody>
		<tr>
			<td><b>Order ID - </b></td>
			<td><%=orderSummary.getId()%></td>
			<br>
		</tr>
		<tr>
			<td><b>Order Date - </b></td>
			<td><%=orderSummary.getOrderDate()%></td>
			<br>
		</tr>
		<tr>
			<td><b>Customer Name - </b></td>
			<td><%=orderSummary.getPersonName()%></td>
			<br>
		</tr>
		<tr>
			<td><b>Customer Email - </b></td>
			<td><%=orderSummary.getEmail()%></td>
			<br>
		</tr>
		<tr>
			<td><b>Customer Phone - </b></td>
			<td><%=orderSummary.getContactNumber()%></td>
			<br>
		</tr>
		<tr>
			<td><b>Total Order Amount - </b></td>
			<td><%=orderSummary.getTotalAmount()%></td>
			<br>
		</tr>

	</tbody>

	<br>
	<hr />
	<a href="index.jsp"><button>Go Back to Home Page</button></a>

	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>