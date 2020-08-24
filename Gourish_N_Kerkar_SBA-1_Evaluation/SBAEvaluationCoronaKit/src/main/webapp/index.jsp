<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corono Kit Order Application</title>

<script>

function checkForBlank(){
	if(document.getElementById('loginid').value == ""){
		alert("Please Enter the Login ID.");
		document.getElementById('loginid').style.borderColor = 'red';
		return false;
	}
	
	if(document.getElementById('password').value == ""){
		alert("Please Enter the Password.");
		document.getElementById('password').style.borderColor = 'red';
		return false;
	}
}

</script>

</head>
<body>
<div>
<jsp:include page="header.jsp"/>
<hr/>
	<h2>Admin Login</h2>
	<form action="admin?action=login" method="post">
		<div>
			<div><label for="loginid"><b>Enter login Id</b> </label> </div>
			<div><input type="text" id="loginid" name="loginid"> </div>
		</div>
		<br>
		<div>
			<div><label for="password"><b>Enter password</b></label> </div>
			<div><input type="password" id="password" name="password"> </div>
		</div>
		<div>
			<br>
			<div><input type="submit" value="Login"> </div>
		</div>
	</form>
</div>
<hr/>
<div>
	<br>
	<h3>For New Users</h3>
		<a href="user?action=newuser"><button>Order Your Corona Kit</button></a>
	</div>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>