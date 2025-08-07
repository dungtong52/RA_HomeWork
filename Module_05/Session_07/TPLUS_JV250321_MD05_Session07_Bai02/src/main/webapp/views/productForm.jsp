<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Thêm sản phẩm</title></head>
<body>
<h2>Nhập thông tin sản phẩm</h2>

<form:form action="/add-product" method="post" modelAttribute="product">
    Tên sản phẩm: <form:input path="name"/><br/>
    <form:errors path="name" cssStyle="color:red"/><br/>

    Giá: <form:input path="price"/><br/>
    <form:errors path="price" cssStyle="color:red"/><br/>

    Mô tả: <form:textarea path="description"/><br/>
    <form:errors path="description" cssStyle="color:red"/><br/>

    <input type="submit" value="Thêm sản phẩm"/>
</form:form>

</body>
</html>
