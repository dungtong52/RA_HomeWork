<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Avatar</title>
</head>
<body>
<h2>Upload ảnh đại diện</h2>
<form:form action="/upload" method="post" modelAttribute="userProfile" enctype="multipart/form-data">
    Tên người dùng: <form:input path="username"/><br/><br/>
    Ảnh đại diện: <input type="file" name="avatar"/><br/><br/>
    <input type="submit" value="Tải lên"/>
</form:form>
</body>
</html>
