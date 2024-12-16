<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c3" %>

<html>
<head>
    <title>Home - New Arrivals</title>
</head>
<body>
    <h1>All Products</h1>

    <div class="row margin-bottom-40">
        <div class="col-md-12 sale-product">
           
            <div class="owl-carousel owl-carousel5">
                <c3:forEach var="product" items="${products}">
                    <div>
                        <div class="product-item">
                            <div class="pi-img-wrapper">
                                <c3:choose>
                                    <c3:when test="${not empty product.image}">
                                        <img src="${product.image}" class="img-responsive" alt="${product.name}" />
                                       
                                        <div>
											<a href="${product.image}" class="img-responsive" alt="${product.name}"
												class="btn btn-default fancybox-button">Zoom</a> 
												<a href="${pageContext.request.contextPath}/product-details?id=${product.product_id}" class="btn btn-default fancybox-fast-view">View</a>
										</div>
                                    </c3:when>
                                    <c3:otherwise>
                                        <img src="/images/default-product.jpg" class="img-responsive" alt="Default Product Image" />
                                    </c3:otherwise>
                                </c3:choose>
                            </div>
                            <h3><a href="shop-item.html">${product.name}</a></h3>
                            <div class="pi-price">${product.price}VND</div>
                             <!-- Form for POST method to add to cart -->
                            <form action="${pageContext.request.contextPath}/addtocart" method="POST">
                                <input type="hidden" name="id" value="${product.product_id}">
                                <button type="submit" class="btn btn-default add2cart">Add to cart</button>
                            </form>
                        </div>
                    </div>
                </c3:forEach>
            </div>
        </div>
    </div>
</body>
</html>
<script>
function addtoCartId(productId) {
        window.location.href = '${pageContext.request.contextPath}/addtocart?id=' + productId;
    }
</script> 
