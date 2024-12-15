<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
    <h1>Edit User Page</h1>
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
								    <form id="personalInfoFormm" role="form" action="${pageContext.request.contextPath}/admin/user/edit" method="post">
								    	<label for="images">Images:</label><br>
										  <c:if test="${user.image.substring(0,5) != 'https'}">
										 					<c:url value="/${user.image}" var="imgUrl"></c:url>
										 		</c:if>
														<c:if test="${user.image.substring(0,5) == 'https'}"> 
															<c:url value="${user.image}" var="imgUrl"></c:url>
														</c:if>
														<img id="imagess" height="150" width="200" src="${imgUrl}" />
										  <input type="file" onchange="chooseFile(this)" name="images" value="${user.image}" id="avatarInput">
										  <a href="#" class="btn default" id="cancelBtn2" style="font-size: 12px; padding: 5px 10px;">Cancel</a>
										  <br>
										  <br>
										<div class="form-group">
								            <label for="email">ID:</label>
								            <input type="id" id="id" name="id" class="form-control" value="${user.id}"readonly/>
								        </div>
								        <div class="form-group">
								            <label for="email">Email:</label>
								            <input type="email" id="email" name="email" class="form-control" value="${user.email}"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">Full Name</label>
								            <input type="text" name="fullname" value="${user.fullname}" class="form-control" id="fullname"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">Mobile Number</label>
								            <input type="text" name="phone" value="${user.phone}" class="form-control" id="phone"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">Password</label>
								            <input type="text" name="password" value="${user.password}" class="form-control" id="password"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">Roles</label>
								            <div>
									            <input type="radio" name="role" value="Admin" 
									                <c:if test="${user.role.name == 'Admin'}">checked</c:if>> Admin    
									            &emsp;
									            <input type="radio" name="role" value="User" 
									                <c:if test="${user.role.name == 'User'}">checked</c:if>> User
									        </div>
								        </div>
								        <div class="form-group">
								            <label class="control-label">Address</label>
								            <br>
								            <label class="control-label">Ward</label>
								            <input type="text" name="ward" value="${user.address.ward}" class="form-control" id="ward"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">District</label>
								            <input type="text" name="district" value="${user.address.district}" class="form-control" id="district"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">City</label>
								            <input type="text" name="city" value="${user.address.city}" class="form-control" id="city"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">Detail</label>
								            <textarea name="detail" class="form-control" rows="3" placeholder="No description" id="detail">${user.address.detail}</textarea>
								        </div>
								        <div class="form-group">
								            <label class="control-label">Status</label>
								            <div>
									            <input type="radio" name="status" value="1" 
									                <c:if test="${user.status == '1'}">checked</c:if>> Được truy cập    
									            &emsp;
									            <input type="radio" name="status" value="0" 
									                <c:if test="${user.status == '0'}">checked</c:if>> Không được truy cập
									        </div>
								        </div>
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
<style>
    /* Tùy chỉnh màu cam tươi đậm */
    #saveChangesButton {
        background-color: #FF8C00; /* Màu cam tươi đậm */
        border-color: #FF8C00; /* Màu viền */
        color: white; /* Màu chữ */
    }

    #saveChangesButton:hover {
        background-color: #E67600; /* Màu cam đậm hơn khi hover */
        border-color: #E67600;
    }
</style>

<script>
	function cancelEdit() {
	    window.location.href = '${pageContext.request.contextPath}/admin/user'; // Chuyển hướng đến trang cancelEdit
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
	 document.getElementById('imagess').src = "${user.image}";  // Hiển thị ảnh mặc định
	 document.getElementById('avatarInput').value = '';  // Reset input file
	
	});
    function validateForm() {
        const currentPassword = document.getElementById("currentPassword").value;
        const newPassword = document.getElementById("newPassword").value;
        const retypeNewPassword = document.getElementById("retypeNewPassword").value;

        let hasError = false;

        // Kiểm tra Current Password
        if (!currentPassword) {
            setError("currentPasswordError", "Please enter your current password!");
            hasError = true;
        } else {
            setError("currentPasswordError", "");
        }

        // Kiểm tra New Password
        if (!newPassword) {
            setError("newPasswordError", "Please enter your new password!");
            hasError = true;
        } else if (!validatePasswordStrength(newPassword)) {
            setError("newPasswordError", "Password must be at least 8 characters and include uppercase, lowercase, a number, and a special character.");
            hasError = true;
        } else {
            setError("newPasswordError", "");
        }

        // Kiểm tra Re-type New Password
        if (!retypeNewPassword) {
            setError("retypeNewPasswordError", "Please retype your new password!");
            hasError = true;
        } else if (retypeNewPassword !== newPassword) {
            setError("retypeNewPasswordError", "Passwords do not match!");
            hasError = true;
        } else {
            setError("retypeNewPasswordError", "");
        }

        return !hasError;
    }

 		// Hàm setError để thay đổi thông báo lỗi và màu sắc
    function setError(elementId, message) {
        const errorElement = document.getElementById(elementId);
        errorElement.textContent = message;
        errorElement.style.color = "red";  // Đảm bảo thông báo lỗi có màu đỏ
    }

    // Hàm kiểm tra độ mạnh của mật khẩu
    function validatePasswordStrength(password) {
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?_&])[A-Za-z\d@$!%*?_&]{8,}$/;
        return passwordRegex.test(password);
    }
 // Lấy form và vai trò người dùng từ server
    const form = document.getElementById('personalInfoForm');
    const userRole = '${sessionScope.account.role}'; // Lấy giá trị role từ server-side

    // Thay đổi action dựa trên role
    if (userRole === 'Admin') {
        form.action = '${pageContext.request.contextPath}/admin/myaccount';
    } else if (userRole === 'User')  {
        form.action = '${pageContext.request.contextPath}/myaccount';
    }
</script>