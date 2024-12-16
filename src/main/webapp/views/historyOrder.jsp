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
    <table class="table table-striped table-bordered table-hover" id="datatable_products">
	<thead>
	<tr role="row" class="heading">
				<th width="10%">
								 Order ID
				</th>
				<th width="10%">
								 Total Price
				</th>
				<th width="15%">
								 Order Date
				</th>
				<th width="15%">
								 Size
				</th>
				<th width="15%">
								 Actions
				</th>
	</tr>
	<tr role="row" class="filter">
			<c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.order_id}</td>
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
	</tr>
	</thead>
	<tbody>
	</tbody>
	</table>
	
    
</body>
</html>
