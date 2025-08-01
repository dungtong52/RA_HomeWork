<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/login" method="post">
    <label for="username">User Name</label>
    <input type="text" id="username" name="username">
    <label for="password">User Name</label>
    <input type="text" id="password" name="password">
    <input type="submit" value="Sign in">
</form>
<p style="color:red">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
</p>
</body>
</html>