<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello Student!" %>
</h1>
<br/>
<form action="/StudentController" method="post">
    <input type="text" id="name" name="name" placeholder="Name"><br><br>
    <input type="number" id="age" name="age" placeholder="Age"><br><br>
    <input type="text" id="address" name="address" placeholder="Address"><br><br>
    <input type="submit" value="Send">
</form>
<%
    if (request.getAttribute("error") != null) {
%>
<h2>${error}</h2>
<%
    }
%>
</body>
</html>