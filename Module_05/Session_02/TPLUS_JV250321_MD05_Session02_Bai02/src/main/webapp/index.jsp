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
    <div class="sendForm container">
        <h1>Nhập thông tin người dùng</h1>
        <form action="/UserServlet" method="post">
            <input type="text" id="name" name="name" placeholder="Full Name">
            <input type="text" id="email" name="email" placeholder="Email">
            <input type="submit" value="Send" class="btn">
        </form>
    </div>
    <div class="result container">
        <h1>Thông tin người dùng</h1>
        <p><b>Name: </b>${name}</p>
        <p><b>Email: </b>${email}</p>
    </div>

</body>
</html>