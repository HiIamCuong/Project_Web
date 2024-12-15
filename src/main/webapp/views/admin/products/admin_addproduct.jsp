<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
	<div class="content-form-page">
		<div class="row">
			<div class="col-md-7 col-sm-7">
				<c:if test="${alert !=null}">
					<h3 class="alert alertdanger">${alert}</h3>
				</c:if>				
				<form action="${pageContext.request.contextPath}/admin/product/add"
					method="post" class="form-horizontal" role="form"
					onsubmit="return validateForm()">
					<fieldset>
						<legend>Product details</legend>
						<div class="form-group">
							<label for="name" class="col-lg-4 control-label"> Image
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<img id="imagess" height="150" width="200" src="${imgUrl}" />
										  <input type="file" onchange="chooseFile(this)" name="images" value="${user.image}" id="avatarInput">
										  <a href="#" class="btn default" id="cancelBtn2" style="font-size: 12px; padding: 5px 10px;">Cancel</a>					
								<span id="nameError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-lg-4 control-label"> Product Name
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="name" name="name">
								<span id="nameError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="price" class="col-lg-4 control-label">
								Price <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="price"
									name="price"> <span id="priceError"
									class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="material" class="col-lg-4 control-label"> Material <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="material" name="material">
								<span id="materialError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="color" class="col-lg-4 control-label"> Color
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="color" name="color">
								<span id="colorError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="size" class="col-lg-4 control-label"> Size
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="size" name="size">
								<span id="sizeError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="quantity" class="col-lg-4 control-label"> Quantity
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="quantity" name="quantity">
								<span id="quantitylError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-lg-4 control-label"> Description
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="description" name="description">
								<span id="descriptionError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="status" class="col-lg-4 control-label"> Status <span
								class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="radio" class="form-control" name="status" value="1"> Đang được bán    
								&emsp;
								<input type="radio" class="form-control" name="status" value="0"> Đang ẩn
								<span id="statusError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="status" class="col-lg-4 control-label"> Category <span
								class="require">*</span>
							</label>
							<div class="col-lg-8">
								<table summary="Shopping cart">
													<c:forEach var="category" items="${listCategory}">
														<tr>														
															<td>${category.name}</td>
															<td><input type="radio" name="category"
																value="${category.category_id}"></td>
														</tr>
														<div id="paymentError" class="error-message"
															style="color: red; font-style: italic; font-size: 12px;"></div>
													</c:forEach>
												</table>
								<span id="statusError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
					</fieldset>
					<div class="row">
						<div
							class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
							<button type="submit" id="addProductBtn" class="registerbtn"
    style="background-color: black; height: 40px; width: 180px; color: white; font-size: 15px; display: none;">
    ADD A NEW PRODUCT
</button>
							<a href="#" class="btn default" onclick="cancelEdit()" id="cancelBtn">Cancel</a>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-5 col-sm-5 pull-right">
				<div class="form-info">
					<h2>
						<em>Important</em> Information
					</h2>
					<p>All fields marked with an asterisk (*) are required.</p>
					<p>Please ensure that all information is accurate and complete.</p>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END CONTENT -->


<script>
	//Lấy tất cả các nút radio và nút ADD A NEW PRODUCT
	const radioButtons = document.querySelectorAll('input[name="category"]');
	const addProductBtn = document.getElementById('addProductBtn');
	
	// Lắng nghe sự kiện click trên tất cả các nút radio
	radioButtons.forEach(radio => {
	    radio.addEventListener('change', () => {
	        // Nếu có nút radio được chọn, hiển thị nút ADD A NEW PRODUCT
	        if (document.querySelector('input[name="category"]:checked')) {
	            addProductBtn.style.display = 'block'; // Hiển thị nút
	        } else {
	            addProductBtn.style.display = 'none'; // Ẩn nút
	        }
	    });
	});
	// Khi người dùng chọn ảnh mới, cập nhật ảnh preview
	document.getElementById('avatarInput').addEventListener('change', function(event) {
	    var reader = new FileReader();
	    reader.onload = function(e) {
	        document.getElementById('imagess').src = e.target.result;  // Hiển thị ảnh đã chọn
	    };
	    reader.readAsDataURL(this.files[0]);
	});
	
		// Khi nhấn "Cancel", reset input file
	 document.getElementById('cancelBtn2').addEventListener('click', function(event) {
	 event.preventDefault();   
	 document.getElementById('imagess').src = "${user.image}";  // Hiển thị ảnh mặc định
	 document.getElementById('avatarInput').value = '';  // Reset input file
	
	});
	function cancelEdit() {
	    window.location.href = '${pageContext.request.contextPath}/admin/product'; // Chuyển hướng đến trang cancelEdit
	}
</script>