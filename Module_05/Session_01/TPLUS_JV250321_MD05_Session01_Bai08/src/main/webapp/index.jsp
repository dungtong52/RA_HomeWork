<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/style.css">
    <title>Đăng ký vé xe</title>
</head>
<body>

<form action="registerTicket" method="post">
    <h2>Đăng ký vé xe</h2>

    <label for="name">Full Name:</label>
    <input type="text" id="name" name="name">

    <label for="class">Class:</label>
    <input type="text" id="class" name="class">

    <label for="type">Vehicle Type:</label>
    <input type="text" id="type" name="type">

    <label for="licensePlate">License Plate:</label>
    <input type="text" id="licensePlate" name="licensePlate">

    <button type="submit" value="signup">Sign Up</button>
</form>
</body>
</html>