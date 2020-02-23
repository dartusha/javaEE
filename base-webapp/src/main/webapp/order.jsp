<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.01.2020
  Time: 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.geekbrains.persist.Order" %>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<head>
    <title>Order</title>
</head>
<body>
<%@include file="menu.html" %>
<div class="container">
    <h2>Create/Edit Order</h2>

    <form method="post" action="order">
        <input type="hidden" id="id" name="id" value="${order.id}">
        <p>
            <label for="name">Name</label>
            <input type="text" id="name" name="name" value="${order.name}" required>
        </p>
        <p>
            <label for="phone">Phone</label>
            <input type="text" id="phone" name="phone" value="${order.phone}" >
        </p>
        <p>
            <label for="email">Email</label>
            <input type="text" id="email" name="email" value="${order.email}" >
        </p>
        <p>
            <label for="address">Delivery date</label>
            <input type="text" id="deliveryDate" name="deliveryDate" value="${order.deliveryDate}" >
        </p>
        <p>
            <label for="address">Address</label>
            <input type="text" id="address" name="address" value="${order.address}" >
        </p>
        <p>
            <label for="comments">Comments</label>
            <input type="text" id="comments" name="comments" value="${order.comments}" >
        </p>

        <input type="submit" value="Save">
        <input type="button" value="Cancel" onclick="window.location.href='order'">
    </form>
</div>
</body>
</html>
