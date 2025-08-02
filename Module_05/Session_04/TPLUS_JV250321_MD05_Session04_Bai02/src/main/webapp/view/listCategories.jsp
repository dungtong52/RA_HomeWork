<%@ page import="com.model.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <title>List Categories</title>
</head>
<body>
<div class="table-div">
    <h1>Danh sách danh mục</h1>
    <table>
        <tr>
            <th class="col-id">ID</th>
            <th class="col-name">Category Name</th>
            <th class="col-description">Description</th>
            <th class="col-status">Status</th>
            <th class="col-action">Action</th>
        </tr>
        <%
            List<Category> categoryList = (List<Category>) request.getAttribute("categories");
            if (categoryList != null) {
                for (Category category : categoryList) {
        %>
        <tr>
            <td><%=category.getId()%>
            </td>
            <td><%=category.getCateName()%>
            </td>
            <td><%=category.getDescription()%>
            </td>
            <td><%=category.isStatus() ? "Active" : "Inactive"%>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/category?action=edit&categoryId=<%=category.getId()%>" class="btn edit">Update</a>
                <a href="<%=request.getContextPath()%>/category?action=delete&categoryId=<%=category.getId()%>" class="btn delete">Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <h2><a href="<%=request.getContextPath()%>/view/addCategory.jsp">Thêm mới</a></h2>
</div>

</body>
</html>