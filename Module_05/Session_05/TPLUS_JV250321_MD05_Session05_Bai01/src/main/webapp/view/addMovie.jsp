<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/addMovieStyle.css">
    <title>Add Movie</title>
</head>
<body>
<div class="container">
    <h1>Thêm phim mới</h1>
    <form action="" method="post">
        <label for="title">Tiêu đề</label>
        <input type="text" id="title" name="title" required>

        <label for="direction">Đạo diễn</label>
        <input type="text" id="direction" name="direction">

        <label for="genre">Thể loại</label>
        <input type="text" id="genre" name="genre">

        <label for="description">Mô tả</label>
        <textarea name="description" id="description" rows="5"></textarea>

        <label for="duration">Thời gian (phút)</label>
        <input type="number" id="duration" name="duration">

        <label for="language">Ngôn ngữ</label>
        <input type="text" id="language" name="language">

        <input type="submit" value="Thêm phim" class="btn-add">
    </form>
</div>
</body>
</html>
