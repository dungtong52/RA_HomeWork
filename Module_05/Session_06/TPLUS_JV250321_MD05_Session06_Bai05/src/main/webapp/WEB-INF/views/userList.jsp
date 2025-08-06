<%@ page import="java.util.List" %>
<%@ page import="com.ra.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách người dùng</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h1>Danh sách người dùng</h1>

<table>
    <thead>
    <tr>
        <th>Tên</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        if (users != null) {
            for (User user : users) {
    %>
    <tr>
        <td><%= user.getName() %></td>
        <td><%= user.getEmail() %></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

</body>
</html>