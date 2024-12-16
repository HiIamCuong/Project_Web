<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="main">
    <div class="container">
        <div class="row margin-bottom-40">
            <div class="col-md-12 col-sm-12">
                <c:if test="${alert != null}">
                    <h3 class="alert alert-danger">${alert}</h3>
                </c:if>
                <c:if test="${alert == null}">
                    <h1>All Products</h1>
                    <div class="goods-page">
                        <div class="goods-data clearfix">
                            <div class="search-and-pagination">
                                <input type="text" id="searchInput" placeholder="Type..." />
                                <button id="searchButton" class="btn btn-primary">Search</button>
                                <div id="pagination" class="pagination"></div>
                            </div>

                            <div class="table-wrapper-responsive">
                                <table summary="Shopping cart">
                                    <thead>
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
                                    </thead>
                                    <tbody id="productTable">
                                        <c:forEach var="product" items="${listAllProduct}">
                                            <tr>
                                                <td>${product.product_id}</td>
                                                <td>${product.name}</td>
                                                <td class="goods-page-image">
                                                    <a href="#">
                                                        <c:url value="/${product.image}" var="imgUrl"></c:url>
                                                        <img src="${imgUrl}" width="50px" height="50px">
                                                    </a>
                                                </td>
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
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <button class="btn btn-primary" onclick="addUser()" style="background-color: #4CAF50; border:black;">
                            <i class="material-icons" style="vertical-align: middle; margin-right: 5px;">add_circle</i>
                            Add A New Product
                        </button>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<script>
document.addEventListener("DOMContentLoaded", () => {
    const rowsPerPage = 10;
    const table = document.querySelector("table");
    const rows = table.querySelectorAll("tbody tr");
    const pagination = document.getElementById("pagination");
    const searchInput = document.getElementById("searchInput");

    let currentPage = 1;
    let filteredRows = Array.from(rows);

    function displayTable() {
        const start = (currentPage - 1) * rowsPerPage;
        const end = start + rowsPerPage;

        rows.forEach(row => row.style.display = "none");
        filteredRows.slice(start, end).forEach(row => row.style.display = "");
        
        // Handle empty results
        if (filteredRows.length === 0) {
            const messageRow = document.createElement("tr");
            const messageCell = document.createElement("td");
            messageCell.colSpan = rows[0].cells.length;
            messageCell.textContent = "No products found.";
            messageRow.appendChild(messageCell);
            table.querySelector("tbody").appendChild(messageRow);
        }
    }

    function createPagination() {
        pagination.innerHTML = "";
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

    function debounce(func, delay) {
        let timeout;
        return function (...args) {
            clearTimeout(timeout);
            timeout = setTimeout(() => func.apply(this, args), delay);
        };
    }

    function searchTable() {
        const query = searchInput.value.toLowerCase();
        filteredRows = Array.from(rows).filter(row => {
            return Array.from(row.cells).some(cell => cell.textContent.toLowerCase().includes(query));
        });

        currentPage = 1;
        displayTable();
        createPagination();
    }

    const debouncedSearch = debounce(searchTable, 300);
    searchInput.addEventListener("input", debouncedSearch);
    document.getElementById("searchButton").addEventListener("click", searchTable);

    displayTable();
    createPagination();
});

function editProduct(productId) {
    window.location.href = '${pageContext.request.contextPath}/admin/product/edit?id=' + productId;
}

function addUser() {
    window.location.href = '${pageContext.request.contextPath}/admin/product/add';
}
</script>

<style>
/* Add your existing styles here */
</style>