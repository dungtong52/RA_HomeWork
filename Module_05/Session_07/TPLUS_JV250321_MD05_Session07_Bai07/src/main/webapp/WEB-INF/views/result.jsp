<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kết Quả Đánh Giá</title>
</head>
<body>
<h2>Thông Tin Đánh Giá</h2>
<p><strong>Tên:</strong> ${review.username}</p>
<p><strong>Email:</strong> ${review.email}</p>
<p><strong>Đánh giá:</strong> ${review.rating} sao</p>
<p><strong>Bình luận:</strong> ${review.comment}</p>
</body>
</html>
