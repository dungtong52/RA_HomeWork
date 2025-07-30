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
    <title>Document</title>
</head>
<body>
    <div class="container">
        <h1>To Do List</h1>
        <form class="input-container" method="post" action="/todolist">
            <input type="text" id="taskInput" name="taskInput">
            <button type="submit" value="Note" class="btn">Note</button>
        </form>
        <hr>
        <div class="task-list">
            <ul>
                <%
                    List<String> todoList = (List<String>) request.getAttribute("todo");
                    if (todoList != null) {
                        for (String task : todoList) {
                %>
                <li><%=task%></li>
                <%
                        }
                    }
                %>
            </ul>
        </div>
    </div>
</body>
</html>