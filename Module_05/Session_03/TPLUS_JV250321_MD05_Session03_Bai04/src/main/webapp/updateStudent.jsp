<%@ page import="com.ra.entity.Student" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/styles.css">
    <title>Edit Student</title>
</head>
<body>
<div class="container">
    <h1>Cập nhật thông tin sinh viên</h1>
    <div class="input-container">
        <h3>Thêm sinh viên</h3>
        <form action="/UpdateStudentController" method="post">
            <%
                Student student = (Student) request.getAttribute("student");
            %>
            <input type="hidden" name="id" value="<%=student.getId()%>">

            <label>Tên sinh viên</label>
            <input type="text" id="name" name="name" value="<%=student.getName()%>">

            <label>Tuổi</label>
            <input type="number" id="age" name="age" value="<%=student.getAge()%>">

            <label>Địa chỉ</label>
            <input type="text" id="address" name="address" value="<%=student.getAddress()%>">

            <input type="submit" value="Cập nhật" class="btn">
        </form>
    </div>
</div>
</body>
</html>