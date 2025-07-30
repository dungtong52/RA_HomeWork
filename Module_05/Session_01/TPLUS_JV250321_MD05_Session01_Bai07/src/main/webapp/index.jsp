<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>Thực hiện phép tính chia</h1>
    <form action="/result" method="post">
        <input type="number" id="number1" name="number1" placeholder="number 1">
        <input type="number" id="number2" name="number2" placeholder="number 2">
        <button type="submit" value="Calculate" >Tính</button>
    </form>
    <p>Kết quả: ${result}</p>
</body>
</html>