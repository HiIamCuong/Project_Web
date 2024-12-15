<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<div class="main">
	<div class="container">
		<!-- BEGIN SIDEBAR & CONTENT -->
		<div class="row margin-bottom-40">
			<!-- BEGIN CONTENT -->
			<div class="col-md-12 col-sm-12">
				<c:if test="${alert != null}">
					<h3 class="alert alertdanger">${alert}</h3>
				</c:if>
				<c:if test="${alert == null}">
					<h1>All Products</h1>
					<div class="goods-page">
						<div class="goods-data clearfix">
							<div class="table-wrapper-responsive">
								<table summary="Shopping cart">
									<tr>
                                        <th>ID</th>
                                        <th>Product Name</th>
                                        <th>Image</th>
                                        <th>Price</th>
                                        <th>Material</th>
                                        <th>Color</th>
                                        <th>Size</th>
                                        <th>Create Date</th>                                        
                                        <th>Description</th>
                                        <th>Status</th>
                                        <th>Quantity</th>
                                        <th width="70"></th>
                                    </tr>
									<c:forEach var="product" items="${listAllProduct}">
										 <tr>
                                            <td>${product.product_id}</td>
                                            <td>${product.name}</td>
                                            <td class="goods-page-image"><a href="#"> <c:if
														test="${product.image.substring(0,5) != 'https'}">
														<c:url value="/${product.image}"
															var="imgUrl"></c:url>
													</c:if> <c:if
														test="${product.image.substring(0,5) == 'https'}">
														<c:url value="${product.image}" var="imgUrl"></c:url>
													</c:if> <img src="${imgUrl}" width="50px" height="50px">
											</a></td>
                                            <td>${product.price}</td>
                                            <td>${product.material}</td>
                                            <td>${product.color}</td>
                                            <td>${product.size}</td>
                                            <td>${product.createDate}</td>
                                           
                                            <td>${product.description}</td>
                                            <td>
											    <c:choose>
											        <c:when test="${product.status == 1}">Đang được bán</c:when>
											        <c:otherwise>Đang ẩn</c:otherwise>
											    </c:choose>
											</td>
											 <td>${product.quantity}</td>
                                             <td>
                                             	<a href="javascript:void(0);" onclick="editProduct('${product.product_id}')">&#x270F;</a>
                                             	<a class="del-goods" style="background-color: #e4605e; border:black;" href="<c:url value='deleteProduct?id=${product.product_id}'/>">&nbsp;</a>

                                            </td>
                                        </tr>
									</c:forEach>
								</table>
							</div>

							
						</div>
						<button class="btn btn-primary" onclick="addUser()" style="background-color: #de875c; border:black;">
							<i class="material-icons" style="vertical-align: middle; margin-right: 5px;">add_circle</i>
							Thêm Sản Phẩm Mới
						</button>
					</div>
				</c:if>
			</div>
			<!-- END CONTENT -->
		</div>
		<!-- END SIDEBAR & CONTENT -->
	</div>
</div>

<script>
	function editProduct(userId) {
	    window.location.href = '${pageContext.request.contextPath}/admin/product/edit?id=' + userId;
	}
    function addUser() {
        window.location.href = '${pageContext.request.contextPath}/admin/product/add'; 
    }
</script>