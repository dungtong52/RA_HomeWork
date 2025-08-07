<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Đăng ký</h1>
<h3 style="color: red">${error}</h3>
<form:form modelAttribute="user" method="post" action="users">
    <table>
        <tr>
            <td>Tên đăng nhập:</td>
            <td>
                <form:input path="name"/> <br>
                <form:errors path="name" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td>Mật khẩu:</td>
            <td>
                <form:input path="password"/> <br>
                <form:errors path="password" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td>Email:</td>
            <td>
                <form:input path="email"/> <br>
                <form:errors path="email" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Insert"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
