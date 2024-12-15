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
					<h1>All Users</h1>
					<div class="goods-page">
						<div class="goods-data clearfix">
							<div class="table-wrapper-responsive">
								<table summary="Shopping cart">
									<tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Image</th>
                                        <th>Email</th>
                                        <th>Address</th>
                                        <th>Phone</th>
                                        <th>Role</th>
                                        <th>Status</th>
                                        <th width="70">Function</th>
                                    </tr>
									<c:forEach var="user" items="${listAllUser}">
										 <tr>
                                            <td>${user.id}</td>
                                            <td>${user.fullname}</td>
                                            <td class="goods-page-image"><a href="#"> 
                                            		<c:if
														test="${user.image.substring(0,5) != 'https'}">
														<c:url value="/${user.image}"
															var="imgUrl"></c:url>
													</c:if> 
													<c:if
														test="${user.image.substring(0,5) == 'https'}">
														<c:url value="${user.image}" var="imgUrl"></c:url>
													</c:if> <img src="${imgUrl}" width="50px" height="50px">													
											</a></td>
                                      
                                            <td>${user.email}</td>
                                            <td>Phường (xã): ${user.address.ward}, Quận (huyện): ${user.address.district}, Tỉnh (thành Phố): ${user.address.city}</td>
                                            <td>${user.phone}</td>
                                            <td>${user.role.name}</td>
                                            <td>
											    <c:choose>
											        <c:when test="${user.status == 1}">Được truy cập</c:when>
											        <c:otherwise>Không được truy cập</c:otherwise>
											    </c:choose>
											</td>
                                             <td>
                                             	<a href="javascript:void(0);" onclick="editUser('${user.id}')">&#x270F;</a>
                                             	<a class="del-goods" style="background-color: #e4605e; border:black;" href="<c:url value='deleteUser?id=${user.id}'/>">&nbsp;</a>

                                            </td>
                                        </tr>
									</c:forEach>
								</table>
							</div>

							
						</div>
						<button class="btn btn-primary" onclick="addUser()" style="background-color: #4CAF50; border:black;">
							<i class="material-icons" style="vertical-align: middle; margin-right: 5px;">person_add</i>
							Thêm Người Dùng Mới
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
	function editUser(userId) {
	    window.location.href = '${pageContext.request.contextPath}/admin/user/edit?id=' + userId;
	}
    function addUser() {
        window.location.href = '${pageContext.request.contextPath}/admin/user/add'; 
    }
</script>