<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>

<script>
	function checkForBlank() {
		var phoneno = /^\d{10}$/;
		var email = /^[\w-]+(\.[\w-]+)*@([a-z0-9-]+(\.[a-z0-9-]+)*?\.[a-z]{2,6}|(\d{1,3}\.){3}\d{1,3})(:\d{4})?$/;

		if (document.getElementById('pname').value == "") {
			alert("Please Enter your Name.");
			document.getElementById('pname').style.borderColor = 'black';
			return false;
		}

		if (document.getElementById('pemail').value == "") {
			alert("Please Enter your Email.");
			document.getElementById('pemail').style.borderColor = 'black';
			return false;
		}

		if (!document.getElementById('pemail').value.match(email)) {
			alert("You have entered an invalid email address.")
			document.getElementById('pemail').style.borderColor = 'black';
			return false;
		}

		if (document.getElementById('pcontact').value == "") {
			alert("Please Enter your Phone Number.");
			document.getElementById('pcontact').style.borderColor = 'black';
			return false;
		}

		if (!document.getElementById('pcontact').value.match(phoneno)) {
			alert("Please Enter your Phone Number in 10 digit format");
			document.getElementById('pcontact').style.borderColor = 'black';
			return false;
		}
	}
</script>

</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />

	<%-- Required View Template --%>

	<form action="user?action=insertuser" method="post"
		onsubmit="return checkForBlank()">
		<div>
			<div>
				<label><b>Enter Customer Name </b></label> <input type="text"
					id="pname" name="pname">
			</div>
			<br />
			<div>
				<label><b>Enter Customer Email </b></label> <input type="text"
					id="pemail" name="pemail">
			</div>
			<br />
			<div>
				<label><b>Enter Customer Phone </b></label> <input type="text"
					id="pcontact" name="pcontact">
			</div>
			<br>
			<div>
				<input type="submit" value="Proceed to Product Selection">
			</div>
		</div>
	</form>

	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>
