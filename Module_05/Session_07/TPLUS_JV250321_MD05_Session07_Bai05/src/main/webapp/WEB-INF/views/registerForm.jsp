<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Đăng ký</title>
</head>
<body>
<h1>Đăng ký</h1>

<form:form modelAttribute="user" method="post">
    <table>
        <tr>
            <td>Tên đăng nhập:</td>
            <td>
                <form:input path="username"/> <br>
                <form:errors path="username" cssStyle="color: red"/>
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
                <input type="submit" value="Đăng ký"/>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
