<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/listMoviesStyle.css">
    <title>List Movies</title>
</head>
<body>
<div class="container">
    <h1>Danh sách phim</h1>
    <a href="<%=request.getContextPath()%>/view/addMovie.jsp" class="btn-add">Thêm phim mới</a>
    <table>
        <tr>
            <th class="col-id">Mã phim</th>
            <th class="col-title">Tiêu đề</th>
            <th class="col-direction">Đạo diễn</th>
            <th class="col-genre">Thể loại</th>
            <th class="col-duration">Thời lương</th>
            <th class="col-language">Ngôn ngữ</th>
            <th class="col-action">Hành động</th>
        </tr>
    </table>
</div>
</body>
</html>
