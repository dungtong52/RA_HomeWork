<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <title>Add Category</title>
</head>
<body>
<div class="container">
    <h1>Thêm danh mục</h1>
    <form action="<%=request.getContextPath()%>/category?action=save" method="post">
        <label for="name">Category Name</label>
        <input class="input" type="text" id="name" name="name" required>

        <label for="description">Description</label>
        <input class="input" type="text" id="description" name="description">

        <label>Status</label>
        <label for="active"><input type="radio" id="active" name="status" value="true" checked> Active</label>
        <label for="inactive"><input type="radio" id="inactive" name="status" value="false"> Inactive</label>

        <input type="submit" value="ADD" class="btn add">
    </form>
</div>
</body>
</html>