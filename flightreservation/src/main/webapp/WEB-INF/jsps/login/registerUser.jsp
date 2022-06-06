<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register User</title>
</head>
<body>
<h2><b>User Registration</b></h2>
<form action="registerUser" method="post">
<pre>
First name: <input type="text" name="firstName"/>
Last name: <input type="text" name="lastName"/>
User name: <input type="text" name="email"/>
Password: <input type="text" name="password"/>
Confirm Password: <input type="text" name="confirmPassword"/>
<input type="submit" value="register"/>
</pre>
</form>

</body>
</html>