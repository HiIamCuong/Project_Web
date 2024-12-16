<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>New Arrival Products</h1>

<div class="row margin-bottom-40">
    <div class="col-md-12 sale-product">
        <div class="owl-carousel owl-carousel5">
            <c:forEach var="product" items="${newArrivalProducts}">
    <div>
        <h3>${product.name}</h3>
        <div class="pi-price">${product.price} VND</div>
        <c:choose>
            <c:when test="${not empty product.image}">
                <img src="${product.image}" class="img-responsive product-image" alt="${product.name}" />
            </c:when>
            <c:otherwise>
                <img src="/images/default-product.jpg" class="img-responsive product-image" alt="Default Product Image" />
            </c:otherwise>
        </c:choose>
        <a href="${pageContext.request.contextPath}/product-details?id=${product.productId}" class="btn btn-default">View</a>
    </div>
</c:forEach>
<c:if test="${empty newArrivalProducts}">
    <p>No new arrival products available at this time.</p>
</c:if>
        </div>
    </div>
</div>

<style>
    .product-image {
        width: 100%; /* Full width of the container */
        height: 200px; /* Fixed height for all images */
        object-fit: cover; /* Ensures the image covers the box without distortion */
        border-radius: 5px; /* Optional: add border radius for a softer look */
    }
    .image-links {
        margin-top: 10px; /* Space between image and links */
    }
    .image-links .btn {
        margin-right: 5px; /* Space between buttons */
    }
</style>