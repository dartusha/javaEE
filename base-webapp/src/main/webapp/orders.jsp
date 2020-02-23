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
    ORDERS
    <table class="table table-hover table-bordered">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Create date</th>
            <th scope="col">Amount</th>
            <th scope="col">Status</th>
            <th scope="col">Action</th>
            <th scope="col">Details</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="order" items="${requestScope.orders}" >
            <tr>
                <th scope="row"><c:out value="${order.id}"></c:out> </th>
                <td><c:out value="${order.name}"></c:out> </td>
                <td><c:out value="${order.createDate}"></c:out> </td>
                <td><c:out value="${order.amount}"></c:out> </td>
                <td><c:out value="${order.statusName}"></c:out> </td>
                <td>
                    <a class="btn btn-success" href="order?id=${order.id}&command=EDIT">edit<i class="fas fa-edit"></i></a>
                    <a class="btn btn-danger" href="order?id=${order.id}&command=DELETE">delete<i class="far fa-trash-alt"></i></a>
                </td>
                <td>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Product Name</th>
                            <th scope="col">Count</th>
                            <th scope="col">Price</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="detail" items="${order.orderDetails}" >
                        <tr>
                            <form action="order" method="post" >
                            <input type="hidden" id="detailId" name="detailId" value="${detail.orderId}">
                            <input type="hidden" id="detailProductId" name="detailProductId" value="${detail.productId}">
                                <input type="hidden" id="detailAmount" name="detailAmount" value="${detail.amount}">

                                <td><c:out value="${detail.rowid}"></c:out></td>
                            <td><c:out value="${detail.productName}"></c:out></td>
                            <td>
                                <input type="text" id="cnt" name="cnt" size="4" value="${detail.cnt}" required>
                            </td>
                            <td>
                               <input type="text" id="amountPerOne" readonly name="amountPerOne" size="10" value="${detail.amountPerOne}" required>
                            </td>
                            <td>
                                <input class="btn btn-success" type="submit" value="Save">
                                <a class="btn btn-danger" href="order?id=${order.id}&productId=${detail.productId}&command=DELETEDT">delete<i class="far fa-trash-alt"></i></a>
                            </td>
                            </form>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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
