<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 11/12/2025
  Time: 8:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%--
  File: home.jsp
  Description: Hiển thị danh sách Customer
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .table th, .table td {
            vertical-align: middle;
        }

        .action-column {
            min-width: 150px;
        }

        .action-btn {
            padding: .25rem .5rem;
            font-size: .875rem;
        }
    </style>
</head>
<body>
<%@ include file="/view/admin/layout/navbar.jsp" %>

<div class="container mt-5">
    <h1 class="mb-4 text-success"><i class="bi bi-people-fill me-2"></i> Customer List Management</h1>

    <%-- Hiển thị thông báo (ví dụ: ${requestScope.mess} hoặc ${param.mess}) --%>
    <c:if test="${param.mess != null && param.mess != ''}">
        <div class="alert alert-info alert-dismissible fade show">
                ${param.mess}
        </div>
    </c:if>

    <%-- Thanh công cụ (Thêm mới và Tìm kiếm) --%>
    <div class="row mb-3 d-flex justify-content-between align-items-center">
        <div class="col-auto">
            <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/customer?action=add">
                <i class="bi bi-person-plus-fill"></i> Add New Customer
            </a>
        </div>
        <div class="col-md-4">
            <form action="/customer" method="get" class="d-flex">
                <input type="hidden" name="action" value="search">
                <input type="text" name="name" class="form-control me-2" placeholder="Search by Name...">
                <button type="submit" class="btn btn-outline-primary">
                    <i class="bi bi-search"></i> Search
                </button>
            </form>
        </div>
    </div>

    <%-- Card chứa Bảng --%>
    <div class="card shadow border-0">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover table-striped mb-0">
                    <thead class="table-dark">
                    <tr>
                        <th class="text-center" style="width: 5%;">#</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th style="width: 10%;">Phone</th>
                        <th>Address</th>
                        <th style="width: 10%;">username</th>
                        <th class="text-center action-column">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%-- Sử dụng customerList được truyền từ Servlet --%>
                    <c:forEach var="customer" items="${requestScope.customerList}" varStatus="status">
                        <tr>
                            <td class="text-center">${status.count}</td>
                            <td class="fw-bold">${customer.name}</td>
                            <td>${customer.email}</td>
                            <td>${customer.phone}</td>
                            <td><span class="text-truncate"
                                      style="max-width: 250px; display: block;">${customer.address}</span></td>
                            <td class="text-center">${customer.username}</td>
                            <td class="text-center">
                                <button onclick="getInfoToDelete('${customer.id}','${customer.name}')"
                                        type="button" class="btn btn-outline-danger action-btn me-1"
                                        data-bs-toggle="modal" data-bs-target="#deleteModal">
                                    <i class="bi bi-trash"></i>
                                </button>
                                <a href="${pageContext.request.contextPath}/admin/customer?action=showUpdate&id=${customer.id}"
                                   class="btn btn-outline-primary action-btn">
                                    <i class="bi bi-pencil-square"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${requestScope.customerList == null || requestScope.customerList.isEmpty()}">
                        <tr>
                            <td colspan="8" class="text-center text-muted py-4">
                                <i class="bi bi-info-circle"></i> No customer found.
                            </td>
                        </tr>
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
            <form action="${pageContext.request.contextPath}/admin/customer?action=delete" method="post">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="deleteModalLabel"><i class="bi bi-exclamation-triangle"></i> Confirm
                        Deletion</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <input type="hidden" name="deleteId" id="deleteId">
                    Bạn có chắc chắn muốn xóa khách hàng: <br>
                    **<span id="deleteName" class="text-danger fw-bold"></span>** ?
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
    function getInfoToDelete(id, name) {
        document.getElementById("deleteId").value = id;
        document.getElementById("deleteName").innerHTML = name;
    }
</script>
</body>
</html>
