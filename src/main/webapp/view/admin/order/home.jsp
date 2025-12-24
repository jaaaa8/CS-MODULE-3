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

<%-- INCLUDE ADMIN NAVBAR --%>
<%@ include file="/view/admin/layout/navbar.jsp" %>

<div class="container mt-5">
  <h1 class="mb-4 text-primary"><i class="bi bi-receipt me-2"></i> Order List Management</h1>

  <%-- Hiển thị thông báo từ Controller (mess) --%>
  <c:if test="${param.mess != null && param.mess != ''}">
    <div class="alert alert-info alert-dismissible fade show" role="alert">
        ${param.mess}
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
  </c:if>

  </div>
  <div class="card shadow border-0">
    <div class="card-body p-0">
      <div class="table-responsive">
        <table class="table table-hover table-striped mb-0">
          <thead class="table-dark">
          <tr>
            <th class="text-center" style="width: 5%;">ID</th>
            <th>Customer ID</th>
            <th>Order Date</th>
            <th>Total Amount</th>
            <th>Status</th>
            <th>Confirmed By</th>
            <th class="text-center action-column">Action</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="order" items="${requestScope.orderList}">
            <tr>
              <td class="text-center">${order.id}</td>
              <td>${order.customerId}</td>
              <td>
                  <%-- Sử dụng tag fmt để format ngày tháng --%>
                    <fmt:formatDate value="${order.createAt}" pattern="yyyy-MM-dd HH:mm"/>
              </td>
              <td>
                  <%-- Format tiền tệ --%>
                <fmt:formatNumber value="${order.total}" type="currency" currencySymbol="$"/>
              </td>
              <td>
                  <%-- Hiển thị trạng thái với màu sắc tương ứng --%>
                <span class="badge
                                        <c:choose>
                                            <c:when test="${order.status == 'COMPLETED'}">bg-success</c:when>
                                            <c:when test="${order.status == 'SHIPPED'}">bg-info text-dark</c:when>
                                            <c:when test="${order.status == 'CONFIRMED'}">bg-primary</c:when>
                                            <c:when test="${order.status == 'PENDING'}">bg-warning text-dark</c:when>
                                            <c:when test="${order.status == 'CANCELLED'}">bg-danger</c:when>
                                            <c:otherwise>bg-secondary</c:otherwise>
                                        </c:choose>
                                        ">${order.status}
                </span>
              </td>
              <td>${order.confirmById}</td> <%-- Cần join với bảng Account để lấy tên Admin/Staff --%>

              <td class="text-center">
                <c:choose>
                  <c:when test="${order.status == 'PENDING'}">
                    <%-- Hành động 1: Admin xác nhận (PENDING -> CONFIRMED) --%>
                    <button onclick="setConfirmData('${order.id}', 'confirm')"
                            type="button" class="btn btn-warning action-btn text-dark"
                            data-bs-toggle="modal" data-bs-target="#confirmModal">
                      <i class="bi bi-check-circle"></i> Xác nhận ĐH
                    </button>
                  </c:when>
                  <c:when test="${order.status == 'CONFIRMED'}">
                    <%-- Hành động 2: Admin chuyển vận chuyển (CONFIRMED -> SHIPPED) --%>
                    <button onclick="setConfirmData('${order.id}', 'shipped')"
                            type="button" class="btn btn-info action-btn text-dark"
                            data-bs-toggle="modal" data-bs-target="#confirmModal">
                      <i class="bi bi-truck"></i> Vận chuyển
                    </button>
                  </c:when>
                  <c:when test="${order.status == 'SHIPPED'}">
                    <%-- Hành động 3: Admin đánh dấu Hoàn thành (SHIPPED -> COMPLETED) --%>
                    <button onclick="setConfirmData('${order.id}', 'complete')"
                            type="button" class="btn btn-success action-btn"
                            data-bs-toggle="modal" data-bs-target="#confirmModal">
                      <i class="bi bi-person-check-fill"></i> Hoàn thành
                    </button>
                  </c:when>
                  <c:when test="${order.status == 'COMPLETED'}">
                    <span class="text-success fw-bold"><i class="bi bi-check-all"></i> Đã Xong</span>
                  </c:when>
                  <c:otherwise>
                    <a href='<c:url value="/order?action=detail&id=${order.id}"/>' class="btn btn-secondary action-btn">Xem Chi tiết</a>
                  </c:otherwise>
                </c:choose>

                  <%-- Nút Xóa (chỉ cho PENDING, CART, CANCELLED) --%>
                <c:if test="${order.status == 'PENDING' || order.status == 'CART' || order.status == 'CANCELLED'}">
                  <button onclick="getInfoToDelete('${order.id}')"
                          type="button" class="btn btn-outline-danger action-btn ms-1"
                          data-bs-toggle="modal" data-bs-target="#deleteModal">
                    <i class="bi bi-trash"></i>
                  </button>
                </c:if>
              </td>
            </tr>
          </c:forEach>

          <c:if test="${requestScope.orderList == null || requestScope.orderList.isEmpty()}">
            <tr><td colspan="7" class="text-center text-muted py-4">
              <i class="bi bi-info-circle"></i> No order found.
            </td></tr>
          </c:if>
          </tbody>
        </table>
      </div>
    </div>
  </div>

</div>

<%-- MODAL XÁC NHẬN CHUNG CHO VIỆC CHUYỂN TRẠNG THÁI --%>
<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form id="confirmForm" action="" method="post">
        <div class="modal-header bg-primary text-white">
          <h5 class="modal-title" id="confirmModalLabel">Xác nhận Chuyển Trạng thái</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>

        <div class="modal-body">
          <input type="hidden" name="orderId" id="confirmOrderId">
          Bạn có chắc chắn muốn <span id="confirmActionText" class="fw-bold text-danger"></span> đơn hàng ID:
          **<span id="confirmOrderDisplayId" class="text-primary fw-bold"></span>** ?
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="submit" class="btn btn-primary" id="confirmSubmitBtn">Xác nhận</button>
        </div>
      </form>
    </div>
  </div>
</div>
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action='<c:url value="/order?action=delete"/>' method="post">
        <div class="modal-header bg-danger text-white">
          <h5 class="modal-title" id="deleteModalLabel"><i class="bi bi-exclamation-triangle"></i> Confirm Deletion</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>

        <div class="modal-body">
          <input type="hidden" name="deleteId" id="deleteId">
          Bạn có chắc chắn muốn xóa đơn hàng ID:
          **<span id="deleteOrderId" class="text-danger fw-bold"></span>** ?
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
  function setConfirmData(id, type){
    const form = document.getElementById("confirmForm");
    const submitBtn = document.getElementById("confirmSubmitBtn");
    const actionText = document.getElementById("confirmActionText");

    document.getElementById("confirmOrderId").value = id;
    document.getElementById("confirmOrderDisplayId").innerHTML = id;

    if (type === 'confirm') {
      // PENDING -> CONFIRMED
      form.action = "<c:url value='/order?action=admin_confirm'/>";
      actionText.innerHTML = "XÁC NHẬN và DUYỆT";
      submitBtn.className = "btn btn-warning text-dark";
    } else if (type === 'shipped') {
      // CONFIRMED -> SHIPPED
      form.action = "<c:url value='/order?action=admin_shipped'/>";
      actionText.innerHTML = "CHUYỂN sang ĐANG VẬN CHUYỂN";
      submitBtn.className = "btn btn-info text-dark";
    } else if (type === 'complete') {
      // SHIPPED -> COMPLETED
      form.action = "<c:url value='/order?action=client_complete'/>";
      actionText.innerHTML = "ĐÁNH DẤU HOÀN THÀNH";
      submitBtn.className = "btn btn-success";
    }
  }

  // Hàm lấy thông tin để xóa
  function getInfoToDelete(id){
    document.getElementById("deleteId").value = id;
    document.getElementById("deleteOrderId").innerHTML = id;
  }
</script>
</body>
</html>
