<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.01.2020
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.geekbrains.persist.Cart" %>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<head>
    <title>Cart</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<%@include file="menu.html" %>
<div class="container">
CART
    <a class="btn btn-success" href="order?command=NEW">ORDER<i class="fas fa-edit"></i></a>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Product Name</th>
                <th scope="col">Count</th>
                <th scope="col">Amount</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>

            <c:set var="total" value="${0}"/>
            <c:forEach var="cart" items="${requestScope.carts}" >
                <tr>
                    <th scope="row"><c:out value="${cart.rowid}"></c:out> </th>
                    <td><c:out value="${cart.productName}"></c:out> </td>
                    <td><c:out value="${cart.cnt}"></c:out> </td>
                    <td><c:out value="${cart.amount}"></c:out> </td>
                    <c:set var="total" value="${total + cart.amount}" />
                    <td>
                        <a class="btn btn-primary" href="cart?id=${cart.productId}&command=PLUS">+<i class="fas fa-edit"></i></a>
                        <a class="btn btn-success" href="cart?id=${cart.productId}&command=MINUS">-<i class="fas fa-edit"></i></a>
                        <a class="btn btn-danger" href="cart?id=${cart.productId}&command=DELETE">delete<i class="far fa-trash-alt"></i></a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <th scope="row">Total</th>
                <td></td>
                <td></td>
                <td><c:out value="${total}"></c:out></td>
            </tr>
            </tbody>

        </table>
    <br>
    <br>
    <a class="btn btn-danger" href="cart?command=TRUNCATE">EMPTY CART<i class="far fa-trash-alt"></i></a>
    </div>

</body>
</html>
