<%@ page import="com.ra.entity.Student" %>
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
    <title>Student List</title>
</head>
<body>
<div class="result-container">
    <h1>Danh sách sinh viên</h1>
    <table>
        <tr>
            <th class="col-id">ID</th>
            <th class="col-name">Tên sinh viên</th>
            <th class="col-age">Tuổi</th>
            <th class="col-address">Địa chỉ</th>
            <th class="col-action">Action</th>
        </tr>
        <%
            List<Student> studentList = (List<Student>) request.getAttribute("students");
            if (studentList != null) {
                for (Student student : studentList) {

        %>
        <tr>
            <td><%=student.getId()%>
            </td>
            <td><%=student.getName()%>
            </td>
            <td><%=student.getAge()%>
            </td>
            <td><%=student.getAddress()%>
            </td>
            <td class="action">
                <a href="/UpdateStudentController?studentId=<%=student.getId()%>" class="btn-update">Sửa</a>

                <form action="/DeleteStudentController" method="post">
                    <input type="hidden" name="studentId" value="<%=student.getId()%>">
                    <input class="btn-delete" value="Xóa" type="submit">
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <h4><a href="index.jsp" class="add">Thêm sinh viên</a></h4>
</div>
</body>
</html>

