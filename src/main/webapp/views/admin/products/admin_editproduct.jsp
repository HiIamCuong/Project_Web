<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
    <h1>Edit product Page</h1>
    <div class="content-page">
        <div class="profile-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light">
                        <div class="portlet-body">
                         <!-- Thêm thông báo alert -->
                            <% 
                                String alert = (String) request.getAttribute("alert"); 
                                if (alert != null) { 
                            %>
                                <div class="alert alert-info">
                                    <%= alert %>
                                </div>
                            <% 
                                } 
                            %>
                            <div class="tab-content">
                               <!-- PERSONAL INFO TAB -->
								    <form id="personalInfoFormm" role="form" action="${pageContext.request.contextPath}/admin/product/edit" method="post">
								    	<label for="images">Image</label><br>
										  <c:if test="${product.image.substring(0,5) != 'https'}">
										 					<c:url value="/${product.image}" var="imgUrl"></c:url>
										 		</c:if>
														<c:if test="${product.image.substring(0,5) == 'https'}"> 
															<c:url value="${product.image}" var="imgUrl"></c:url>
														</c:if>
														<img id="imagess" height="150" width="200" src="${imgUrl}" />
										  <input type="file" onchange="chooseFile(this)" name="images" value="${product.image}" id="avatarInput">
										  <a href="#" class="btn default" id="cancelBtn2" style="font-size: 12px; padding: 5px 10px;">Cancel</a>
										  <br>
										  <br>									 
										<div class="form-group">
								            <label for="id">ID</label>
								            <input type="text" id="id" name="id" class="form-control" value="${product.product_id}"readonly/>
								        </div>
								        <div class="form-group">
								            <label for="productName">Product Name</label>
								            <input type="text" id="productName" name="productName" class="form-control" value="${product.name}"/>
								        </div>
								        <div class="form-group">
								            <label for="productName">Price</label>
								            <input type="text" id="price" name="price" class="form-control" value="${product.price}"/>
								        </div>
								        <div class="form-group">
								            <label for="productName">Material</label>
								            <input type="text" id="material" name="material" class="form-control" value="${product.material}"/>
								        </div>
								        <div class="form-group">
								            <label for="productName">Color</label>
								            <input type="text" id="color" name="color" class="form-control" value="${product.color}"/>
								        </div>
								        <div class="form-group">
								            <label for="productName">Size</label>
								            <input type="text" id="size" name="size" class="form-control" value="${product.size}"/>
								        </div>
								        <div class="form-group">
								            <label for="productName">Create Date</label>
								            <input type="text" id="createDate" name="createDate" class="form-control" value="${product.createDate}"readonly/>
								        </div>
								        <div class="form-group">
								            <label for="productName">Quantity</label>
								            <input type="text" id="quantity" name="quantity" class="form-control" value="${product.quantity}"/>
								        </div>
								        <div class="form-group">
								            <label for="productName">Description</label>
								            <input type="text" id="description" name="description" class="form-control" value="${product.description}"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">Status</label>
								            <div>
									            <input type="radio" name="status" value="1" 
									                <c:if test="${product.status == '1'}">checked</c:if>> Đang được bán    
									            &emsp;
									            <input type="radio" name="status" value="0" 
									                <c:if test="${product.status == '0'}">checked</c:if>> Đang ẩn
									        </div>
								        </div>
								         <br>
								        <div class="margiv-top-10">
								            <button type="submit" class="btn btn-primary" style="background-color: black">Save Changes</button>
								            <a href="#" class="btn default" onclick="cancelEdit()" id="cancelBtn">Cancel</a>
								        </div>
								    </form>								
								<!-- END PERSONAL INFO TAB -->                          
                         
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
    </div>
</div>
<!-- END CONTENT -->
<script>
	function cancelEdit() {
	    window.location.href = '${pageContext.request.contextPath}/admin/product'; // Chuyển hướng đến trang cancelEdit
	}
			
			
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
	 document.getElementById('imagess').src = "${product.image}";  // Hiển thị ảnh mặc định
	 document.getElementById('avatarInput').value = '';  // Reset input file
	
	});

</script>