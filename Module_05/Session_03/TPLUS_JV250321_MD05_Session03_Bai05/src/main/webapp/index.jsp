<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/styles.css">
    <title>Student Management</title>
</head>
<body>
<div class="container">
    <h1>Quản lý sinh viên</h1>
    <div class="input-container">
        <h3>Thêm sinh viên</h3>
        <form action="/AddStudentController" method="post">
            <label>Tên sinh viên</label>
            <input type="text" id="name" name="name">

            <label>Tuổi</label>
            <input type="number" id="age" name="age">

            <label>Địa chỉ</label>
            <input type="text" id="address" name="address">

            <input type="submit" value="Thêm" class="btn">
        </form>
    </div>
</div>


</body>
</html>