<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/loginStyle.css">
    <title>Login</title>
</head>
<body>
<div class="loginForm">
    <h1>Login</h1>
    <%
        if (request.getAttribute("error") != null) {
    %>
    <p class="error">${error}</p>
    <%
        }
    %>
    <form action="/login" method="post">
        <input type="text" id="name" name="name" placeholder="User Name">
        <input type="text" id="password" name="password" placeholder="Password">
        <button type="submit" value="Login" class="btn">Login</button>
    </form>
</div>
</body>
</html>