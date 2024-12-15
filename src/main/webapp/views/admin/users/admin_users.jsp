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
                    <h3 class="alert alert-danger">${alert}</h3>
                </c:if>
                <c:if test="${alert == null}">
                    <h1>All Users</h1>
                    <div class="goods-page">
                        <div class="goods-data clearfix">
                            <!-- Search Input -->
                            <div class="search-and-pagination">
                            	<input type="text" id="searchInput" placeholder="Type..." />
                            	<button id="searchButton" class="btn btn-primary">Search</button>           
                                <div id="pagination" class="pagination"></div>
                            </div>

                            <!-- Table Wrapper -->
                            <div class="table-wrapper-responsive">
                                <table summary="Shopping cart">
                                    <thead>
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
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${listAllUser}">
                                            <tr>
                                                <td>${user.id}</td>
                                                <td>${user.fullname}</td>
                                                <td class="goods-page-image">
                                                    <a href="#">
                                                        <c:if test="${user.image.substring(0,5) != 'https'}">
                                                            <c:url value="/${user.image}" var="imgUrl"></c:url>
                                                        </c:if>
                                                        <c:if test="${user.image.substring(0,5) == 'https'}">
                                                            <c:url value="${user.image}" var="imgUrl"></c:url>
                                                        </c:if>
                                                        <img src="${imgUrl}" width="50px" height="50px">
                                                    </a>
                                                </td>
                                                <td>${user.email}</td>
                                                <td>Phường (xã): ${user.address.ward}, Quận (huyện): ${user.address.district}, Tỉnh (thành phố): ${user.address.city}</td>
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
                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <button class="btn btn-primary" onclick="addUser()" style="background-color: #4CAF50; border:black;">
                            <i class="material-icons" style="vertical-align: middle; margin-right: 5px;">person_add</i>
                            Add A New User
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
document.addEventListener("DOMContentLoaded", () => {
    const rowsPerPage = 10;
    const table = document.querySelector("table");
    const rows = Array.from(table.querySelectorAll("tbody tr"));
    const pagination = document.getElementById("pagination");

    let currentPage = 1;
    let filteredRows = rows; // Lưu danh sách hàng đã lọc

    function displayTable() {
        const start = (currentPage - 1) * rowsPerPage;
        const end = start + rowsPerPage;

        // Ẩn tất cả các hàng
        rows.forEach(row => row.style.display = "none");

        // Chỉ hiển thị các hàng được lọc nằm trong trang hiện tại
        filteredRows.slice(start, end).forEach(row => {
            row.style.display = "";
        });
    }

    function createPagination() {
        pagination.innerHTML = ""; // Xóa nội dung phân trang cũ
        const totalPages = Math.ceil(filteredRows.length / rowsPerPage);

        for (let i = 1; i <= totalPages; i++) {
            const btn = document.createElement("button");
            btn.textContent = i;
            btn.className = i === currentPage ? "active" : "";
            btn.onclick = () => {
                currentPage = i;
                displayTable();
                createPagination();
            };
            pagination.appendChild(btn);
        }
    }

    function searchTable() {
        const searchInput = document.getElementById("searchInput").value.toLowerCase();

        // Lọc hàng dựa trên từ khóa tìm kiếm
        filteredRows = rows.filter(row => {
            return Array.from(row.cells).some(cell =>
                cell.textContent.toLowerCase().includes(searchInput)
            );
        });

        // Đặt lại trang hiện tại
        currentPage = 1;

        // Hiển thị bảng và phân trang mới
        displayTable();
        createPagination();
    }

    // Lắng nghe sự kiện nút "Tìm kiếm"
    document.getElementById("searchButton").addEventListener("click", searchTable);

    // Khởi tạo bảng và phân trang khi tải trang
    displayTable();
    createPagination();
});


    function editUser(userId) {
        window.location.href = '${pageContext.request.contextPath}/admin/user/edit?id=' + userId;
    }

    function addUser() {
        window.location.href = '${pageContext.request.contextPath}/admin/user/add';
    }
</script>

<style>
	#searchButton {
    padding: 5px 10px;
    margin-left: 5px; /* Giảm khoảng cách giữa nút và ô input */
    background-color: #808080; /* Màu xám */
    color: white;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
	}
	
	#searchButton:hover {
	    background-color: #696969; /* Màu xám đậm hơn khi hover */
	}
	
	.search-and-pagination {
	    margin-bottom: 20px;
	    display: flex;
	    align-items: center; /* Căn giữa ô input và nút tìm kiếm */
	    gap: 5px; /* Thêm khoảng cách nhỏ giữa các phần tử */
	}
	
	#searchInput {
	    padding: 5px 10px;
	    width: 300px;
	    border: 1px solid #ccc;
	    border-radius: 5px;
	}

    .pagination {
        display: flex;
        gap: 5px;
    }

    .pagination button {
        padding: 5px 10px;
        border: 1px solid #ccc;
        background-color: #f9f9f9;
        cursor: pointer;
        border-radius: 3px;
    }

    .pagination button.active {
        background-color: #007bff;
        color: white;
        font-weight: bold;
    }
</style>
