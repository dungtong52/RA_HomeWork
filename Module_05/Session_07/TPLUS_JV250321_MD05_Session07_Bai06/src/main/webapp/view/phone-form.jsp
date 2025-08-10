<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Check phone</title>
</head>
<body>
<div id="container">
    <h1>Check Phone Number</h1>
    <p>${message}</p>
    <form:form modelAttribute="userForm" method="POST" action="/phoneNumber">
        <label for="phone">Number </label>
        <form:input type="text" name="phone" id="phone" value="" path="phoneNumber"/><br>
        <form:errors path="phoneNumber" cssStyle="font-size: 10px;color: #e31212"/><br>

        <form:button type="submit" id="submit">Kiểm tra</form:button>
        <form:button type="reset" id="cancel">Cancel</form:button>

    </form:form>
</div>
</body>
</html>
