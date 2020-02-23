<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.geekbrains.persist.Product" %>
<%@ page import="java.util.List" %><%--

  Created by IntelliJ IDEA.
  User: user
  Date: 04.01.2020
  Time: 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<head>
    <title>Products</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <%@include file="menu.html" %>
    <div class="container">
    PRODUCTS
        <a class="btn btn-success" href="product?command=NEW">New product<i class="fas fa-edit"></i></a>
        <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Price</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="product" items="${requestScope.products}" >
        <tr>
            <th scope="row"><c:out value="${product.id}"></c:out> </th>
            <td><c:out value="${product.name}"></c:out> </td>
            <td><c:out value="${product.description}"></c:out> </td>
            <td><c:out value="${product.price}"></c:out> </td>
            <c:set var="total" value="${total + article.price}" />
            <td>
                <a class="btn btn-primary" href="cart?id=${product.id}&command=BUY">buy<i class="fas fa-edit"></i></a>
                <a class="btn btn-success" href="product?id=${product.id}&command=EDIT">edit<i class="fas fa-edit"></i></a>
                <a class="btn btn-danger" href="product?id=${product.id}&command=DELETE">delete<i class="far fa-trash-alt"></i></a>
            </td>
        </tr>
        </c:forEach>
        </tbody>
        </table>
    </div>
  <br>
  <br>
</body>
</html>
