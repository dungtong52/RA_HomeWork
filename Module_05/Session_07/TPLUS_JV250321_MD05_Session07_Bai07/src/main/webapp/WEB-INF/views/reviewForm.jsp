<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Đánh Giá Sản Phẩm</title>
    <style>
        .error {color: red;}
    </style>
</head>

<body>
<h2>Đánh Giá Sản Phẩm</h2>

<%--@elvariable id="review" type=""--%>
<form:form modelAttribute="review" method="post" action="submitReview">
    <p>
        Tên: <form:input path="username" />
        <form:errors path="username" cssClass="error" />
    </p>
    <p>
        Email: <form:input path="email" />
        <form:errors path="email" cssClass="error" />
    </p>
    <p>
        Đánh giá (1-5): <form:input path="rating" />
        <form:errors path="rating" cssClass="error" />
    </p>
    <p>
        Bình luận:<br>
        <form:textarea path="comment" rows="5" cols="40" />
        <form:errors path="comment" cssClass="error" />
    </p>
    <p><input type="submit" value="Gửi đánh giá"/></p>
</form:form>


</body>
</html>
