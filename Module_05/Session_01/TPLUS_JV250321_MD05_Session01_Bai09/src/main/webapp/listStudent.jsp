<%@ page import="com.ra.entity.Student" %>
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
    <title>Danh sách vé xe sinh viên</title>
</head>
<body>
    <div class="container">
        <h1>Danh sách vé xe sinh viên</h1>
        <table>
            <tr>
                <th>Họ và tên</th>
                <th>Lớp</th>
                <th>Loại xe</th>
                <th>Biển số xe</th>
            </tr>
            <%
                List<Student> studentList = (List<Student>) request.getAttribute("students");
                if (studentList != null) {
                    for (Student student : studentList) {
            %>
            <tr>
                <td><%=student.getFullName()%>
                </td>
                <td><%=student.getClassName()%>
                </td>
                <td><%=student.getVehicleType()%>
                </td>
                <td><%=student.getLicensePlate()%>
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