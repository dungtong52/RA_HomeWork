<%@ page import="com.model.entity.Category" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <title>Edit Category</title>
</head>
<body>
<div class="container">
    <h1>Cập nhật danh mục</h1>
    <form action="<%=request.getContextPath()%>/category?action=update" method="post">
        <%
            Category category = (Category) request.getAttribute("category");
        %>
        <label for="categoryId">Category Name</label>
        <input class="input" type="number" id="categoryId" name="categoryId" value="<%=category.getId()%>" readonly>

        <label for="name">Category Name</label>
        <input class="input" type="text" id="name" name="name" value="<%=category.getCateName()%>">

        <label for="description"></label>
        <input class="input" type="text" id="description" name="description" value="<%=category.getDescription()%>">

        <label>Status</label>
        <label><input type="radio" id="active" name="status"
                                    value="true" <%=category.isStatus()?"checked":""%>> Active</label>
        <label><input type="radio" id="inactive" name="status" value="false"
                                      <%=category.isStatus() ? "" : "checked"%>> Inactive</label>

        <input type="submit" value="EDIT" class="btn edit">
    </form>
</div>
</body>
</html>