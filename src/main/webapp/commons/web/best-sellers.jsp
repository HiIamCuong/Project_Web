<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- Best Selling Products Section -->
<div class="best-selling-products">
    <h3>Gợi ý sản phẩm bán chạy</h3>
    <div class="row">
        <c:forEach var="bestProduct" items="${bestSellingProducts}">
            <div class="col-md-3 col-sm-6">
                <div class="product-item">
                    <div class="pi-img-wrapper">
                        <c:choose>
                            <c:when test="${not empty bestProduct.image}">
                                <img src="${bestProduct.image}" class="img-responsive" alt="${bestProduct.name}" />
                            </c:when>
                            <c:otherwise>
                                <img src="/images/default-product.jpg" class="img-responsive" alt="Default Product Image" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <h3>
                        <a href="/product-details?id=${bestProduct.product_id}">
                            ${bestProduct.name}
                        </a>
                    </h3>
                    <div class="pi-price">${bestProduct.price} VND</div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
