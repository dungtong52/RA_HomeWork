<%@ page import="java.util.List" %>
<%@ page import="com.ra.entity.Task" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/styles.css">
    <title>Home</title>
</head>
<body>
<header>
    <nav>
        <ul>
            <li>Trang chủ</li>
            <li>Liên hệ</li>
            <li>Giỏ hàng</li>
            <li>Tài khoản</li>
        </ul>
    </nav>
</header>
<div class="input">
    <form action="AddTaskServlet" method="post">
        <input type="text" id="taskName" name="taskName" placeholder="Enter new job">
        <input type="submit" value="Add" class="btn">
    </form>
</div>
<main>
    <table>
        <tr>
            <th class="col-id">ID</th>
            <th class="col-name">Task Name</th>
            <th class="col-status">Status</th>
            <th class="col-action">Action</th>
        </tr>
        <%
            List<Task> taskList = (List<Task>) request.getAttribute("tasks");
            if (taskList != null) {
                for (Task task : taskList) {
        %>
        <tr>
            <td><%=task.getId()%>
            </td>
            <td><%=task.getTaskName()%>
            </td>
            <td><%=task.isStatus() ? "Completed" : "Doing"%>
            </td>
            <td>
                <form action="UpdateTaskServlet" method="post">
                    <input type="hidden" name="taskId" value="<%=task.getId()%>">
                    <input type="submit" value="Change status" class="btn-status">
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>

    </table>
</main>
</body>
</html>