<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Currency Convert</title>
    <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
<div class="convert">
    <h1>Chuyển đổi tiền tệ</h1>
    <form action="/convert" method="post">
        <label for="rate">Rate</label>
        <input type="text" id="rate" name="rate" placeholder="Enter your rate">
        <label for="usdCurrency">USD</label>
        <input type="number" id="usdCurrency" name="usdCurrency" placeholder="Enter your usd">
        <button type="submit" value="Convert" class="btn">Convert</button>
    </form>

</div>
<%
    if (request.getAttribute("vndCurrency") != null) {
%>
<div class="result">
    <p>Rate: ${rate}</p>
    <p>USD: ${usdCurrency} - USD</p>
    <p>VND: ${vndCurrency} - VND</p>
</div>
<%
    }
%>
</body>
</html>