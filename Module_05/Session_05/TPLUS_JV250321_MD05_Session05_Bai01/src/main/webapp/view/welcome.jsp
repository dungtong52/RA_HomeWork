<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Trang Admin</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
</head>
<body>

<div class="container">
    <h1>Chào mừng Admin! 👋</h1>
    <p>Bạn có thể sử dụng các nút dưới đây để điều hướng:</p>
    <div class="nav-buttons">
        <a href="<%=request.getContextPath()%>/home" class="btn">Trang Home</a>
        <a href="<%=request.getContextPath()%>/movie?action=list" class="btn">Danh sách phim</a>
        <a href="<%=request.getContextPath()%>/schedule?action=list" class="btn">Danh sách lịch chiếu phim</a>
    </div>
</div>

</body>
</html>
