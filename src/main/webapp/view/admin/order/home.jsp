<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 16/12/2025
  Time: 9:27 SA
  To change this template use File | Settings | File Templates.
--%>
<%--
  File: home.jsp (Order Management)
  Description: Hiển thị danh sách Đơn hàng và các nút chuyển trạng thái Admin.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>Order Management</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <style>
    .table th, .table td { vertical-align: middle; }
    .action-column { min-width: 200px; }
    .action-btn { padding: .25rem .5rem; font-size: .875rem; }
  </style>
</head>
<body>

<%@ include file="/view/admin/layout/navbar.jsp" %>

<div class="container mt-5">
  <h1 class="mb-4 text-primary">
    <i class="bi bi-receipt me-2"></i> Order List Management
  </h1>

  <c:if test="${not empty param.mess}">
    <div class="alert alert-info alert-dismissible fade show" role="alert">
        ${param.mess}
      <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
  </c:if>

  <div class="card shadow border-0">
    <div class="card-body p-0">
      <div class="table-responsive">
        <table class="table table-hover table-striped mb-0">
          <thead class="table-dark">
          <tr>
            <th>Customer Name</th>
            <th>Order Date</th>
            <th>Total Amount</th>
            <th>Status</th>
            <th>Confirmed By</th>
            <th class="text-center action-column">Action</th>
          </tr>
          </thead>

          <tbody>
          <c:forEach var="order" items="${orderList}">
            <tr>
              <td>${order.customerName}</td>

              <td>
                <c:if test="${order.createAt != null}">
                  <fmt:formatDate value="${order.createAt}" pattern="yyyy-MM-dd HH:mm"/>
                </c:if>
              </td>

              <td>
                <fmt:formatNumber value="${order.total}" type="currency" currencySymbol="$"/>
              </td>

              <td>
                <c:set var="statusClass" value="bg-secondary"/>

                <c:if test="${order.status == 'COMPLETED'}">
                  <c:set var="statusClass" value="bg-success"/>
                </c:if>
                <c:if test="${order.status == 'SHIPPED'}">
                  <c:set var="statusClass" value="bg-info text-dark"/>
                </c:if>
                <c:if test="${order.status == 'CONFIRMED'}">
                  <c:set var="statusClass" value="bg-primary"/>
                </c:if>
                <c:if test="${order.status == 'PENDING'}">
                  <c:set var="statusClass" value="bg-warning text-dark"/>
                </c:if>
                <c:if test="${order.status == 'CANCELLED'}">
                  <c:set var="statusClass" value="bg-danger"/>
                </c:if>

                <span class="badge ${statusClass}">
                    ${order.status}
                </span>
              </td>

              <td>
                <c:if test="${not empty order.confirmByName}">
                  ${order.confirmByName}
                </c:if>
                <c:if test="${empty order.confirmByName}">
                  -
                </c:if>
              </td>

              <td class="text-center">
                <a href="<c:url value='/order?action=detail&orderId=${order.id}'/>"
                   class="btn btn-outline-primary action-btn">
                  <i class="bi bi-eye"></i> Detail
                </a>
                <c:if test="${order.status == 'PENDING' || order.status == 'CART' || order.status == 'CANCELLED'}">
                  <button onclick="getInfoToDelete('${order.id}')"
                          type="button"
                          class="btn btn-outline-danger action-btn"
                          data-bs-toggle="modal"
                          data-bs-target="#deleteModal">
                    <i class="bi bi-trash"></i>
                  </button>
                </c:if>
              </td>
            </tr>
          </c:forEach>

          <c:if test="${empty orderList}">
            <tr>
              <td colspan="6" class="text-center text-muted py-4">
                <i class="bi bi-info-circle"></i> No order found.
              </td>
            </tr>
          </c:if>

          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<!-- DELETE MODAL -->
<div class="modal fade" id="deleteModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="<c:url value='/order?action=delete'/>" method="post">
        <div class="modal-header bg-danger text-white">
          <h5 class="modal-title">Confirm Deletion</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">
          <input type="hidden" name="deleteId" id="deleteId">
          Bạn có chắc chắn muốn xóa đơn hàng ID:
          <strong class="text-danger" id="deleteOrderId"></strong> ?
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
          <button type="submit" class="btn btn-danger">Delete</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
  function getInfoToDelete(id){
    document.getElementById("deleteId").value = id;
    document.getElementById("deleteOrderId").innerText = id;
  }
</script>
</body>
</html>
