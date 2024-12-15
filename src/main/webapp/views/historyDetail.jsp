<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details</title>
</head>
<body>
    <h1>Order Details</h1>

    <!-- Hiển thị danh sách chi tiết đơn hàng -->
    <table border="1">
        <thead>
            <tr>
                <th>Order Detail ID</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Size</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="detail" items="${orderDetails}">
                <tr>
                    <td>${detail.orderDetail_id}</td>
                    <td>${detail.product.name}</td>
                    <td>${detail.quantity}</td>
                    <td>${detail.price}</td>
                    <td>${detail.size}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <button onclick="window.history.back()">Back to Orders</button>

</body>
</html>
