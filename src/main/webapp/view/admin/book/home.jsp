<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 11/12/2025
  Time: 8:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Book Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        /* Tùy chỉnh nhỏ để tăng tính thẩm mỹ */
        .table th, .table td {
            vertical-align: middle;
        }
        .action-column {
            min-width: 150px; /* Đảm bảo đủ không gian cho 2 nút */
        }
        /* Giảm padding cho các nút action */
        .action-btn {
            padding: .25rem .5rem;
            font-size: .875rem;
            line-height: 1.5;
        }
    </style>
</head>
<body>
<%@ include file="/view/admin/layout/navbar.jsp" %>

<div class="container mt-5">
    <h1 class="mb-4 text-primary">📚 Book Management List</h1>

    <%-- Hiển thị thông báo --%>
    <c:if test="${param.mess != null && param.mess != ''}">
        <div class="alert alert-info alert-dismissible fade show" role="alert">
                ${param.mess}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <%-- Thanh công cụ (Thêm mới và Tìm kiếm) --%>
    <div class="row mb-3 d-flex justify-content-between align-items-center">
        <div class="col-auto">
            <a class="btn btn-success" href="/book?action=add">
                <i class="bi bi-plus-circle"></i> Add New Book
            </a>
        </div>
        <div class="col-md-8">
            <form action="/book" method="get" class="row g-2 align-items-center">
                <input type="hidden" name="action" value="search">
                <!-- Search by Title -->
                <div class="col-auto">
                    <input type="text" name="name" class="form-control" placeholder="Search by Title..."
                           value="${param.name}">
                </div>
                <!-- Filter by Category -->
                <div class="col-auto">
                    <select name="categoryId" class="form-select">
                        <option value="">All Categories</option>
                        <c:forEach var="cat" items="${categoryList}">
                            <option value="${cat.id}" ${param.categoryId == cat.id.toString() ? 'selected' : ''}>
                                    ${cat.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <!-- Filter by Author -->
                <div class="col-auto">
                    <select name="authorId" class="form-select">
                        <option value="">All Authors</option>
                        <c:forEach var="auth" items="${authorList}">
                            <option value="${auth.id}" ${param.authorId == auth.id.toString() ? 'selected' : ''}>
                                    ${auth.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <!-- Filter by Publisher -->
                <div class="col-auto">
                    <select name="publisherId" class="form-select">
                        <option value="">All Publishers</option>
                        <c:forEach var="pub" items="${publisherList}">
                            <option value="${pub.id}" ${param.publisherId == pub.id.toString() ? 'selected' : ''}>
                                    ${pub.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <!-- Submit -->
                <div class="col-auto">
                    <button type="submit" class="btn btn-outline-primary">
                        <i class="bi bi-search"></i> Search
                    </button>
                </div>
            </form>
        </div>
    </div>

    <%-- Card chứa Bảng --%>
    <div class="card shadow-lg border-0">
        <div class="card-header bg-dark text-white">
            <h5 class="mb-0">📖 Book Inventory Details</h5>
        </div>
        <div class="card-body p-0">

            <div class="table-responsive">
                <table class="table table-hover table-striped mb-0">
                    <thead class="table-light">
                    <tr>
                        <th class="text-center" style="width: 5%;">#</th>
                        <th>Title</th>
                        <th style="width: 10%;">Category</th>
                        <th style="width: 10%;">Author</th>
                        <th style="width: 10%;">Publisher</th>
                        <th class="text-end" style="width: 10%;">Price</th>
                        <th class="text-center" style="width: 8%;">Stock</th>
                        <th class="text-center action-column">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="book" items="${productList}" varStatus="status">
                        <tr>
                            <td class="text-center">${status.count}</td>
                            <td>
                                **${book.title}**
                                <p class="mb-0 text-truncate small text-secondary" style="max-width: 250px;">
                                        ${book.description}
                                </p>
                            </td>
                            <td>${book.categoryName}</td>
                            <td>${book.authorName}</td>
                            <td>${book.publisherName}</td>
                            <td class="text-end fw-bold text-success">
                                <fmt:formatNumber value="${book.price}" type="number" pattern="#,##0"/>
                            </td>
                            <td class="text-center ${book.stock <= 5 ? 'text-danger fw-bold' : ''}">
                                    ${book.stock}
                            </td>
                            <td class="text-center">
                                <button onclick="getInfoToDelete('${book.id}','${book.title}')"
                                        type="button" class="btn btn-outline-danger action-btn"
                                        data-bs-toggle="modal" data-bs-target="#deleteModal">
                                    <i class="bi bi-trash"></i> Delete
                                </button>
                                <a href="/book?action=showUpdate&id=${book.id}"
                                   class="btn btn-outline-primary action-btn">
                                    <i class="bi bi-pencil-square"></i> Update
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${bookList == null || bookList.isEmpty()}">
                        <tr><td colspan="8" class="text-center text-muted py-4">
                            <i class="bi bi-info-circle"></i> No book found in the inventory.
                        </td></tr>
                    </c:if>
                    </tbody>
                </table>
            </div>

        </div>
    </div>

</div>

<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/book?action=delete" method="post">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="deleteModalLabel"><i class="bi bi-exclamation-triangle"></i> Confirm Deletion</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <input type="hidden" name="deleteId" id="deleteId">
                    Bạn có chắc chắn muốn xóa cuốn sách: <br>
                    **<span id="deleteTitle" class="text-danger fw-bold"></span>** ?
                    <p class="mt-2 text-warning"><small>Hành động này không thể hoàn tác.</small></p>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    // Hàm lấy thông tin để xóa
    function getInfoToDelete(id, title){
        document.getElementById("deleteId").value = id;
        document.getElementById("deleteTitle").innerHTML = title;
    }
</script>
</body>
</html>
