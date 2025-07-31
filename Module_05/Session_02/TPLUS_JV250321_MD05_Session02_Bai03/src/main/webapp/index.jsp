<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/styles.css">
    <title>Register</title>
</head>
<body>
<div class="registerForm container">
    <h3>Webrelaration</h3>
    <form action="/RegisterServlet" method="post">
        <input type="text" id="name" name="name" placeholder="User Name">
        <input type="text" id="password" name="password" placeholder="Password">
        <input type="text" id="email" name="email" placeholder="Email">
        <button type="submit" value="Register" class="btn">Register</button>
    </form>
</div>

<%
    if (request.getAttribute("name") != null && request.getAttribute("password") != null && request.getAttribute("email") != null) {
%>
<div class="result container">
    <h1>User Info</h1>
    <p><b>Name: </b>${name}</p>
    <p><b>Password: </b>${password}</p>
    <p><b>Email: </b>${email}</p>
    <p class="success">Register Success!</p>
</div>
<%
    }
%>

</body>
</html>