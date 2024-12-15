<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
 <html>
<head>
    <title>Search Results</title>
</head>
<body>
    <h1>Search Results</h1>

    <c:if test="${not empty searchResults}">
        <ul>
            <c:forEach var="product" items="${searchResults}">
                <li>
                    <a href="/product-details?id=${product.product_id}">${product.name}</a> - 
                    Price: ${product.price}
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty searchResults}">
        <p>No products found.</p>
    </c:if>

</body>
</html>