<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<style>
.admin-theme {
	display: none;
}
</style>
</head>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
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
							<a href="${pageContext.request.contextPath}/admin/myaccount">${sessionScope.account.fullname}</a>
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
		<a class="site-logo" href="${pageContext.request.contextPath}/admin/home"><img src="${URL}assets/img/logo/logoute.png" alt="Furniture Shop UI" style="width: 150px; height: auto;"></a> 
			<a href="javascript:void(0);"
			class="mobi-toggler"><i class="fa fa-bars"></i></a>

		<!-- BEGIN CART -->
		<div class="top-cart-block">
			<div class="top-cart-info">
				<c:choose>
					<c:when
						test="${sessionScope.cartItemCount != null && sessionScope.cartItemCount > 0}">
						<a href="${pageContext.request.contextPath}/admin/cart"
							class="top-cart-info-count">${sessionScope.cartItemCount}
							items</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/admin/cart"
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
				<li><a href="${pageContext.request.contextPath}/admin/product"> Product Management </a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/admin/product">View
								Products</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/product/add">Add Products</a></li>
					</ul></li>

				<li><a href="#"> Order Management </a>
					<ul class="dropdown-menu">
						<li><a href="/kiemtraJDBC/views/admin/categorylist.jsp">View Orders</a></li>						
						<li><a href="/kiemtraJDBC/views/admin/categoryedit.jsp"></a></li>
					</ul></li>
				<li><a href="${pageContext.request.contextPath}/admin/user"> User Management </a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/admin/user">View Users</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/user/add">Add A New User</a></li>
					</ul></li>
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
