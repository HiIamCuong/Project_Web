<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Promote List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Promote List</h1>
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/admin/promote?action=add" class="btn btn-primary">Add Promote</a>
        </div>
        <table class="table table-striped table-bordered table-hover">
            <thead>
                <tr role="row" class="heading">
                    <th width="10%">ID</th>
                    <th width="15%">Voucher Code</th>
                    <th width="15%">Start Date</th>
                    <th width="15%">End Date</th>
                    <th width="15%">Discount Percent</th>
                    <th width="15%">Quantity</th>
                    <th width="15%">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="promote" items="${promotes}">
                    <tr>
                        <td>${promote.id}</td>
                        <td>${promote.voucherCode}</td>
                        <td>${promote.startDate}</td>
                        <td>${promote.endDate}</td>
                        <td>${promote.discountPercent}</td>
                        <td>${promote.quantity}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/promote?action=delete&id=${promote.id}" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
