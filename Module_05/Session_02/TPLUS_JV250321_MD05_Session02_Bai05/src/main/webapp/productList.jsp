<%@ page import="com.ra.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <h1>Danh sách sản phẩm</h1>
  <table>
    <tr>
      <th class="col-id">ID</th>
      <th class="col-name">Tên sản phẩm</th>
      <th class="col-price">Giá</th>
      <th class="col-description">Mô tả</th>
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
    </tr>
    <%
        }
      }
    %>
  </table>
</div>

</body>
</html>
