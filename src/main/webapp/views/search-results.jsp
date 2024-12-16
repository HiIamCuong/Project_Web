<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Search Results for "${query}"</title>
    <style>
        .pagination {
            margin-top: 20px;
        }
        .pagination a, .pagination strong {
            margin: 0 5px;
            text-decoration: none;
            padding: 8px 12px;
            border: 1px solid #ccc;
            color: #333;
        }
        .pagination strong {
            background-color: #007bff;
            color: white;
            border: none;
        }
    </style>
</head>
<body>
    <h1>Search Results for "${query}"</h1>

    <!-- Filter Form -->
    <form action="${pageContext.request.contextPath}/search" method="get">
        <label for="searchTerm">Search:</label>
        <input type="text" id="searchTerm" name="query" value="${query}" />

        <label for="category">Category:</label>
        <select id="category" name="category">
            <option value="">All Categories</option>
            <option value="Shirt" <c:if test="${category == 'Shirt'}">selected</c:if>>Shirt</option>
            <option value="Pen" <c:if test="${category == 'Pen'}">selected</c:if>>Pen</option>
            <option value="Pencil" <c:if test="${category == 'Pencil'}">selected</c:if>>Pencil</option>
            <option value="Book" <c:if test="${category == 'Book'}">selected</c:if>>Books</option>
            <option value="Pen sharpener" <c:if test="${category == 'Pen sharpener'}">selected</c:if>>Pen sharpener</option>
            <option value="Scissor" <c:if test="${category == 'Scissor'}">selected</c:if>>Scissor</option>
            <option value="Eraser" <c:if test="${category == 'Eraser'}">selected</c:if>>Eraser</option>
            <option value="Ruler" <c:if test="${category == 'Ruler'}">selected</c:if>>Ruler</option>
        </select>

        <input type="submit" value="Filter" />
    </form>

    <c:if test="${not empty searchResults}">
        <ul>
            <c:forEach var="product" items="${searchResults}">
                <li class="product-item">
                    <img src="${product.image}" alt="${product.name}" style="max-width: 100px; height: auto; margin-right: 10px;"/>
                    <a href="${pageContext.request.contextPath}/product-details?id=${product.product_id}">
                        ${product.name} - ${product.price} VND</a>
                </li>
            </c:forEach>
        </ul>

        <!-- Pagination Controls -->
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="?query=${query}&category=${category}&page=${currentPage - 1}">Previous</a>
            </c:if>
            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <strong>${i}</strong>
                    </c:when>
                    <c:otherwise>
                        <a href="?query=${query}&category=${category}&page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <a href="?query=${query}&category=${category}&page=${currentPage + 1}">Next</a>
            </c:if>
        </div>
    </c:if>

    <c:if test="${empty searchResults}">
        <p>No products found.</p>
    </c:if>

    <a href="${pageContext.request.contextPath}/home">Back to Home</a>
</body>
</html>