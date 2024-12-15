<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History</title>
</head>
<body>
    <h1>Order History</h1>

    <!-- Hiển thị danh sách đơn hàng -->
    <table border="1">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Customer Name</th>
                <th>Total Price</th>
                <th>Order Date</th>
                <th>Note</th>
                <th>Actions</th> <!-- Thêm cột hành động -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.order_id}</td>
                    <td>${order.user.name}</td>
                    <td>${order.total_price}</td>
                    <td>${order.order_date}</td>
                    <td>${order.note}</td>
                    <td>
                        <!-- Nút xem chi tiết -->
                        <form action="/history/details" method="get" style="display: inline;">
                            <input type="hidden" name="orderId" value="${order.order_id}">
                            <button type="submit">View Details</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
