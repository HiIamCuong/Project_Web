<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Best Selling Products Section -->
<div class="best-selling-products" style="padding: 30px; background-color: #f9f9f9; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);">
    <h3 style="text-align: center; margin-bottom: 20px;">BEST SELLING PRODUCTS</h3>
    <p style="text-align: center; font-size: 16px; color: #666;">Explore our best selling products available !</p>

    <c:if test="${not empty bestSellingProducts}">
        <div class="row">
            <c:forEach var="bestProduct" items="${bestSellingProducts}">
                <div class="col-md-3 col-sm-6" style="margin-bottom: 30px;">
                    <div class="product-item" style="border: 1px solid #ddd; border-radius: 5px; overflow: hidden; text-align: center; background-color: #fff; padding: 15px;">
                        <div class="pi-img-wrapper">
                            <a href="${pageContext.request.contextPath}/product-details?id=${bestProduct.product_id}">
                                <c:choose>
                                    <c:when test="${not empty bestProduct.image}">
                                        <img src="${bestProduct.image}" class="img-responsive product-image" alt="${bestProduct.name}" />
                                    </c:when>
                                    <c:otherwise>
                                        <img src="/images/default-product.jpg" class="img-responsive product-image" alt="Default Product Image" />
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </div>
                        <h3 style="margin: 10px 0;">
                            <a href="${pageContext.request.contextPath}/product-details?id=${bestProduct.product_id}" style="text-decoration: none; color: #333;">${bestProduct.name}</a>
                        </h3>
                        <div class="pi-price" style="font-size: 18px; font-weight: bold; color: #e74c3c;">${bestProduct.price} VND</div>
                        <div style="margin-top: 10px;">
                            <a href="${bestProduct.image}" class="btn btn-default fancybox-button" alt="${bestProduct.name}">Zoom</a>
                            <a href="${pageContext.request.contextPath}/product-details?id=${bestProduct.product_id}" class="btn btn-default fancybox-fast-view">View</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Pagination Controls -->
        <div class="pagination" style="text-align: center; margin-top: 20px;">
            <c:if test="${currentPage > 1}">
                <a href="?page=${currentPage - 1}" class="btn btn-default">Previous</a>
            </c:if>
            <c:forEach var="i" begin="1" end="${totalPages}">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <strong>${i}</strong>
                    </c:when>
                    <c:otherwise>
                        <a href="?page=${i}" class="btn btn-default">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <a href="?page=${currentPage + 1}" class="btn btn-default">Next</a>
            </c:if>
        </div>
    </c:if>

    <c:if test="${empty bestSellingProducts}">
        <p style="text-align: center; color: #999;">No best-selling products available at the moment.</p>
    </c:if>
</div>

<style>
    .product-image {
        width: 100%; /* Full width of the container */
        height: 200px; /* Fixed height */
        object-fit: cover; /* Maintain aspect ratio and cover the box */
        border-radius: 5px; /* Match the border radius of the product item */
    }
    .btn {
        margin: 5px 0; /* Spacing between buttons */
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