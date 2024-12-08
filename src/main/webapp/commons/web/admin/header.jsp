<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
		<a class="site-logo" href="shop-index.html"><img
			src="${URL}assets/frontend/layout/img/logos/logo.png"
			alt="Furniture Shop UI"></a> <a href="javascript:void(0);"
			class="mobi-toggler"><i class="fa fa-bars"></i></a>

		<!-- BEGIN CART -->
		<div class="top-cart-block">
			<div class="top-cart-info">
				<a href="javascript:void(0);" class="top-cart-info-count">n
					items</a>
			</div>
			<i class="fa fa-shopping-cart" style="background-color: black"></i>

			<div class="top-cart-content-wrapper">
				<div class="top-cart-content" style="border: black">
					<ul class="scroller" style="height: 250px;">
						<li><a href="shop-item.html"> <img
								src="${URL}assets/frontend/pages/img/cart-img.jpg"
								alt="Rolex Classic Watch" width="37" height="34"></a> <span
							class="cart-content-count">x 1</span> <strong> <a
								href="shop-item.html">Rolex Classic Watch</a>
						</strong> <em>$1230</em> <a href="javascript:void(0);" class="del-goods">&nbsp;
						</a></li>
					</ul>
					<div class="text-right">
						<a href="shop-shopping-cart.html" class="btn btn-default"
							style="background-color: black">View Cart</a> <a
							href="shop-checkout.html" class="btn btn-primary"
							style="background-color: black">Checkout</a>
					</div>
				</div>
			</div>
		</div>
		<!--END CART -->

		<!-- BEGIN NAVIGATION -->
		<div class="header-navigation">
			<ul>
				<li><a href="#"> Product Management </a>
					<ul class="dropdown-menu">
						<li><a href="/kiemtraJDBC/views/admin/categorylist.jsp">View
								Products</a></li>
						<li><a href="/kiemtraJDBC/views/admin/categoryadd.jsp">Add Products</a></li>
					</ul></li>

				<li><a href="#"> Order Management </a>
					<ul class="dropdown-menu">
						<li><a href="/kiemtraJDBC/views/admin/categorylist.jsp">View Orders</a></li>
						<li><a href="/kiemtraJDBC/views/admin/categoryadd.jsp">Add Orders?nên có cái này ko? (Mi)</a></li>
						<li><a href="/kiemtraJDBC/views/admin/categoryedit.jsp"></a></li>
					</ul></li>
				<li><a href="#"> Customer Management </a>
					<ul class="dropdown-menu">
						<li><a href="/kiemtraJDBC/views/admin/categorylist.jsp">View Customers</a></li>
						
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
