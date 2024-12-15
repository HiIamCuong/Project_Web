<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c2" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!-- Related Products Section -->
<div class="related-products">
    <h3>RELATED PRODUCTS</h3>
    <div class="row">
        <c2:choose>
            <c2:when test="${not empty relatedProducts}">
                <c2:forEach var="relatedProduct" items="${relatedProducts}">
                    <div class="col-md-3 col-sm-6">
                        <div class="product-item">
                            <div class="pi-img-wrapper">
                                <c2:choose>
                                    <c2:when test="${not empty relatedProduct.image}">
                                        <img src="${relatedProduct.image}" class="img-responsive" alt="${relatedProduct.name}" />
                                        <div>
											<a href="${relatedProduct.image}" class="img-responsive" alt="${relatedProduct.name}"
												class="btn btn-default fancybox-button">Zoom</a> 
												<a href="${pageContext.request.contextPath}/product-details?id=${relatedProduct.product_id}" class="btn btn-default fancybox-fast-view">View</a>
										</div>
                                    </c2:when>
                                    <c2:otherwise>
                                        <img src="/images/default-product.jpg" class="img-responsive" alt="Default Product Image" />
                                    </c2:otherwise>
                                </c2:choose>
                            </div>
                            <h3>
                                <a href="/product-details?id=${relatedProduct.product_id}">
                                    ${relatedProduct.name}
                                </a>
                            </h3>
                            <div class="pi-price">${relatedProduct.price} VND</div>
                        </div>
                    </div>
                </c2:forEach>
            </c2:when>
            <c2:otherwise>
                <p>Không có sản phẩm liên quan để hiển thị.</p>
            </c2:otherwise>
        </c2:choose>
    </div>
</div>
