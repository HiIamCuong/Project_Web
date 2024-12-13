<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<head>
<style>
.admin-theme {
	display: none;
}
</style>
</head>

<!-- DangNhap, DangKy -->
<!-- BEGIN TOP BAR -->
<div class="pre-header">
	<div class="container">
		<!-- BEGIN TOP BAR MENU -->
		<div class="col-md-12 col-sm-12 additional-nav">
			<ul class="list-unstyled list-inline pull-right">
				<li><c:choose>
						<c:when test="${sessionScope.account == null}">
							<a href="${pageContext.request.contextPath}/login">Login</a>
								| <a href="${pageContext.request.contextPath}/register">Register</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/myaccount">${sessionScope.account.fullname}</a>
								| <a href="${pageContext.request.contextPath}/logout">Logout</a>
						</c:otherwise>
					</c:choose></li>
			</ul>
		</div>
		<!-- END TOP BAR MENU -->
	</div>
</div>
</div>
<!-- END TOP BAR -->

<!-- BEGIN HEADER -->
<div class="header">
	<div class="container">
		<a class="site-logo" href="${pageContext.request.contextPath}/home"><img src="${URL}assets/img/logo/logoute2.png" alt="Furniture Shop UI" style="width: 150px; height: auto;"></a>
		<a href="javascript:void(0);" class="mobi-toggler"><i
			class="fa fa-bars"></i></a>

		<!-- BEGIN CART -->
		<div class="top-cart-block">
		    <div class="top-cart-info">
		        <c:choose>
		            <c:when test="${sessionScope.cartItemCount != null && sessionScope.cartItemCount > 0}">
		                <a href="${pageContext.request.contextPath}/cart"
		                   class="top-cart-info-count">${sessionScope.cartItemCount} items</a>
		            </c:when>
		            <c:otherwise>
		                <a href="${pageContext.request.contextPath}/cart"
		                   class="top-cart-info-count">0 items</a>
		            </c:otherwise>
		        </c:choose>
		    </div>
		    <i class="fa fa-shopping-cart" style="background-color: black"></i>
		</div>
		<!-- END CART -->

		<!-- BEGIN NAVIGATION -->
		<div class="header-navigation">
			<ul>
				<li><a href="#"> Shop </a></li>
				<li><a href="#"> Blogs </a></li>
				<li><a href="#"> Design ideas </a></li>
				<li class="admin-theme"><a href="#"> Admin theme </a></li>
			</ul>
		</div>
		<!-- END NAVIGATION -->
	</div>
</div>
<!-- Header END -->

<script>
	//Lấy URL hiện tại
	const currentUrl = window.location.href;

	// Lấy phần tử li
	const adminTheme = document.querySelector('.admin-theme');

	// Kiểm tra và hiển thị/ẩn
	if (currentUrl.includes('admin')) {
		adminTheme.style.display = 'block';
	}
</script>
