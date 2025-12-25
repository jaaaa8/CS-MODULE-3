<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 25/12/2025
  Time: 9:20 SA
  To change this template use File | Settings | File Templates.
--%>
<%--
  File: detail.jsp
  Description: Hiển thị chi tiết các sản phẩm trong đơn hàng
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
  <title>Order Detail</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body>

<%@ include file="/view/admin/layout/navbar.jsp" %>

<div class="container mt-5">

  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2 class="text-primary">
      <i class="bi bi-receipt"></i>
      Order Detail – ID #${orderId}
    </h2>

    <a href="${pageContext.request.contextPath}/admin/order"
       class="btn btn-secondary">
      <i class="bi bi-arrow-left"></i> Back to Order List
    </a>
  </div>

  <div class="card shadow-sm">
    <div class="card-body p-0">

      <div class="table-responsive">
        <table class="table table-hover table-striped mb-0">
          <thead class="table-dark">
          <tr>
            <th>#</th>
            <th>Book Name</th>
            <th class="text-center">Quantity</th>
            <th class="text-end">Price</th>
            <th class="text-end">Subtotal</th>
          </tr>
          </thead>

          <tbody>
          <c:set var="total" value="0"/>

          <c:forEach var="item" items="${itemList}" varStatus="loop">
            <c:set var="subtotal" value="${item.quantity * item.price}"/>
            <c:set var="total" value="${total + subtotal}"/>

            <tr>
              <td>${loop.index + 1}</td>
              <td>${item.bookName}</td>
              <td class="text-center">${item.quantity}</td>
              <td class="text-end">
                <fmt:formatNumber value="${item.price}" type="currency" currencySymbol="VND"/>
              </td>
              <td class="text-end">
                <fmt:formatNumber value="${subtotal}" type="currency" currencySymbol="VND"/>
              </td>
            </tr>
          </c:forEach>

          <c:if test="${empty itemList}">
            <tr>
              <td colspan="5" class="text-center text-muted py-4">
                <i class="bi bi-info-circle"></i>
                No items found in this order.
              </td>
            </tr>
          </c:if>
          </tbody>

          <tfoot>
          <tr class="table-secondary fw-bold">
            <td colspan="4" class="text-end">Total</td>
            <td class="text-end">
              <fmt:formatNumber value="${total}" type="currency" currencySymbol="$"/>
            </td>
          </tr>
          </tfoot>
        </table>
      </div>

    </div>
  </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
