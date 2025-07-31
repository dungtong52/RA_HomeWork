<%@ page import="com.ra.entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/styles.css">
    <title>Document</title>
</head>
<body>
<div class="container">
    <h1>Quản lý sản phẩm</h1>
    <div class="input-container">
        <h3>Thêm sản phẩm</h3>
        <form action="/AddProductServlet" method="post">
            <label>ID</label>
            <input type="number" id="id" name="id">

            <label>Tên sản phẩm</label>
            <input type="text" id="productName" name="productName">

            <label>Giá</label>
            <input type="number" id="price" name="price">

            <label>Mô tả</label>
            <input type="text" id="description" name="description">

            <label>Số lượng</label>
            <input type="number" id="quantity" name="quantity">

            <input type="submit" value="Thêm sản phẩm" class="btn">
        </form>
    </div>
    <div class="result-container">
        <h3>Danh sách sản phẩm đã thêm</h3>
        <table>
            <tr>
                <th class="col-id">ID</th>
                <th class="col-name">Tên sản phẩm</th>
                <th class="col-price">Giá</th>
                <th class="col-description">Mô tả</th>
                <th class="col-quantity">Số lượng</th>
                <th class="col-status">Trạng thái</th>
            </tr>
            <%
                List<Product> productList = (List<Product>) request.getAttribute("products");
                if (productList != null) {
                    for (Product product : productList) {

            %>
            <tr>
                <td><%=product.getId()%>
                </td>
                <td><%=product.getProductName()%>
                </td>
                <td><%=product.getPrice()%>
                </td>
                <td><%=product.getDescription()%>
                </td>
                <td><%=product.getStock()%>
                </td>
                <td><%=product.getStock() > 0 ? "Còn hàng" : "Hết hàng"%>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</div>

</body>
</html>