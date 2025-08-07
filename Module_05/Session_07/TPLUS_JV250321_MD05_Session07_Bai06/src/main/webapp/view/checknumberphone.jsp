<%--
  Created by IntelliJ IDEA.
  User: SONY
  Date: 07/08/2025
  Time: 1:36 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Check number phone</title>
    <style>
        #container {
            width: 400px;
            margin: 50px auto;
            padding: 25px 30px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            font-family: 'Segoe UI', sans-serif;
        }

        /* Tiêu đề */
        #container h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        /* Nhãn */
        #container label {
            display: block;
            margin-top: 15px;
            font-weight: 500;
            color: #555;
        }

        /* Input fields */
        #container input[type="text"],
        #container input[type="email"],
        #container input[type="tel"]{
            width: 100%;
            padding: 10px 12px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }

        #container input:focus {
            border-color: #007bff;
            outline: none;
        }

        /* Nút submit và reset */
        #submit,
        #cancel {
            margin-top: 20px;
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        #submit {
            background-color: #007bff;
            color: white;
            margin-right: 10px;
        }

        #submit:hover {
            background-color: #0056b3;
        }

        #cancel {
            background-color: #6c757d;
            color: white;
        }

        #cancel:hover {
            background-color: #5a6268;
        }
        p{
            color: #e31212;
        }
    </style>
</head>
<body>
<div id="container">
    <h1>Check Phone Number</h1>
    <p>${message}</p>
    <form:form modelAttribute="userForm"  method="POST" action="/phoneNumber">
        <label for="phone" >Number </label>
        <form:input type="text" name="phone" id="phone" value=""  path="phoneNumber"/><br>
        <form:errors path="phoneNumber" cssStyle="font-size: 10px;color: #e31212"/><br>

        <form:button type="submit" id="submit">Kiểm tra</form:button>
        <form:button type="reset" id="cancel">Cancel</form:button>

    </form:form>
</div>
</body>
</html>
