<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Place Order(user)</title>

<script>

function checkForBlank(){
	if(document.getElementById('address').value == ""){
		alert("Please Enter Your Address.");
		document.getElementById('address').style.borderColor = 'red';
		return false;
	}
}

</script>



</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />

	<h1>Enter Address to Complete your  Order</h1>

	<%-- Required View Template --%>

	<form action="user?action=ordersummary" method="post" onsubmit="return checkForBlank()">
		<div>
			<div>
				<label>Enter Your Delivery Address</label> <input type="text" id="address" name="address">
			</div>
			<br>
			<div>
				<input type="submit" value="Place Order">
<!-- 				<input type="submit" name="button" onclick="document.pressed=this.value" value="Place Orde"> -->
			</div>
		</div>
	</form>

	



	<hr />


	<jsp:include page="footer.jsp" />
</body>
</html>