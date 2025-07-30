<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/style.css">
    <title>Document</title>
</head>
<body>
    <div class="signupForm">
        <h1>Login</h1>

        <p class="success">${success}</p>
        <form action="/signup" method="post">
            <input type="text" id="name" name="name" placeholder="User Name">
            <p class="error username">${errorName}</p>

            <input type="text" id="email" name="email" placeholder="Email">
            <p class="error email">${errorEmail}</p>

            <input type="text" id="password" name="password" placeholder="Password">
            <p class="error password">${errorPassword}</p>

            <input type="text" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password">
            <p class="error confirmPassword">${errorConfirmPassword}</p>

            <button type="submit" value="Signup" class="btn">Sign Up</button>
        </form>
    </div>
</body>
</html>