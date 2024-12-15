<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
    <title>Revenue</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        form {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <h1 style="text-align: center;">Revenue ${timeUnit}</h1>

    <!-- Form để chọn ngày và loại thời gian -->
    <form id="revenueForm">
        <label for="date">Choose a date:</label>
        <input type="date" id="date" name="date" value="${param.date}" required>

        <label for="timeUnit">Select time unit:</label>
        <select id="timeUnit" name="timeUnit">
            <option value="day" ${param.timeUnit == 'day' ? 'selected' : ''}>Day</option>
            <option value="month" ${param.timeUnit == 'month' ? 'selected' : ''}>Month</option>
            <option value="year" ${param.timeUnit == 'year' ? 'selected' : ''}>Year</option>
        </select>

        <button type="submit">Get revenue</button>
    </form>
	<p>Total Revenue: ${totalRevenue}</p>

    <table>
        <thead>
            <tr>
                <th>Time</th>
                <th>Revenue</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="stat" items="${statistics}">
                <tr>
                    <td>${stat[0]}</td> <!-- Label -->
                    <td>${stat[1]}</td> <!-- Value -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
