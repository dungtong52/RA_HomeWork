<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/loginStyle.css">
    <title>Sign in</title>
</head>
<body>
    <div class="container">
        <h1>Đăng nhập</h1>
        <form action="" method="post">
            <label for="username">Tên đăng nhập</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Mật khẩu</label>
            <input type="text" id="password" name="password" required>

            <input type="submit" value="Đăng nhập" class="btn-login">
        </form>
    </div>
</body>
</html>
