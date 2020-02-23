<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.01.2020
  Time: 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.geekbrains.persist.Product" %>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<head>
    <title>Product</title>
</head>
<body>
<%@include file="menu.html" %>
<div class="container">
    <h2>Create/Edit Product</h2>

    <form method="post" action="product">
        <input type="hidden" id="id" name="id" value="${product.id}">

        <p>
            <label for="name">Name</label>
            <input type="text" id="name" name="name" value="${product.name}" required>
        </p>
        <p>
            <label for="description">Description</label>
            <input type="text" id="description" name="description" value="${product.description}" >
        </p>
        <p>
            <label for="price">Price</label>
            <input type="number" id="price" name="price" value="${product.price}" required>
        </p>
        <input type="submit" value="Save">
        <input type="button" value="Cancel" onclick="window.location.href='product'">
    </form>
</div>
</body>
</html>
