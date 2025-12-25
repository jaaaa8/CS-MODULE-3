<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/2/2025
  Time: 7:59 PM
  To change this template use File & Settings & File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>TTT Bookstore | Giỏ hàng</title>
  <c:import url="../layout/library.jsp"/>
  <style>
    .cart-item {
      border: 1px solid #e0e0e0;
      border-radius: 8px;
      padding: 20px;
      margin-bottom: 15px;
      background-color: #f9f9f9;
      transition: all 0.3s ease;
    }

    .cart-item:hover {
      box-shadow: 0 4px 12px rgba(0,0,0,0.1);
      border-color: #dc3545;
    }

    .price-highlight {
      font-size: 18px;
      font-weight: bold;
      color: #d32f2f;
    }

    .btn-remove {
      background: #f8d7da;
      color: #721c24;
      border: none;
      padding: 6px 12px;
      border-radius: 4px;
      cursor: pointer;
      font-size: 12px;
      font-weight: bold;
      transition: all 0.2s;
    }

    .btn-remove:hover {
      background: #f5c6cb;
    }

    .empty-cart {
      text-align: center;
      padding: 60px 20px;
    }

    .empty-cart i {
      font-size: 80px;
      color: #ddd;
      margin-bottom: 20px;
    }
  </style>
</head>
<body class="d-flex flex-column min-vh-100">

<c:import url="../layout/navbar.jsp" />

<div class="flex-grow-1">
  <div class="container my-5">
    <div class="row">
      <!-- Danh sách sản phẩm -->
      <div class="col-lg-8">
        <h3 class="mb-4">
          <i class="bi bi-cart-fill text-danger"></i> Giỏ hàng của bạn
        </h3>

        <c:choose>
          <c:when test="${empty orderItems}">
            <div class="empty-cart">
              <i class="bi bi-bag-x"></i>
              <h4>Giỏ hàng của bạn đang trống</h4>
              <p class="text-muted">Hãy thêm sản phẩm để tiếp tục mua sắm</p>
              <a href="${pageContext.request.contextPath}/products" class="btn btn-danger mt-3">
                <i class="bi bi-arrow-left"></i> Tiếp tục mua sắm
              </a>
            </div>
          </c:when>

          <c:otherwise>
            <div class="alert alert-info mb-4">
              <i class="bi bi-info-circle"></i>
              Bạn có <strong>${orderItems.size()}</strong> sản phẩm trong giỏ hàng
            </div>

            <c:forEach items="${orderItems}" var="item">
              <div class="card mb-3">
                <div class="row g-0">
                  <div class="col-md-6">
                    <h5>${item.bookName}</h5>
                    <p class="text-muted mb-2">Mã: #${item.orderItemId}</p>
                    <p class="text-danger fw-bold">
                      Giá: <fmt:formatNumber value="${item.price}" type="number" pattern="#,##0"/>₫
                    </p>
                  </div>
                  <div class="col-md-3">
                    <p>
                      <strong>SL:</strong> ${item.quantity}
                    </p>
                  </div>
                  <div class="col-md-2">
                    <p class="text-success fw-bold">
                      <fmt:formatNumber value="${item.price * item.quantity}" type="number" pattern="#,##0"/>₫
                    </p>
                  </div>
                  <div class="col-md-1 text-end">
                    <form method="post" action="${pageContext.request.contextPath}/cart" style="display:inline;"
                          onsubmit="return confirm('Bạn chắc chắn muốn xóa?')">
                      <input type="hidden" name="action" value="remove">
                      <input type="hidden" name="orderItemId" value="${item.orderItemId}">
                      <button type="submit" class="btn btn-remove mt-4">
                        <i class="bi bi-trash"></i> Xóa
                      </button>
                    </form>
                  </div>
                </div>
              </div>
            </c:forEach>

          </c:otherwise>

        </c:choose>

      </div>

      <!-- Tóm tắt đơn hàng -->
      <div class="col-lg-4 position-sticky" style="top:75px;">
        <div class="card p-4 shadow">
          <h5 class="fw-bold mb-3">Tóm tắt đơn hàng</h5>

          <c:set var="subtotal" value="0" />
          <c:forEach items="${orderItems}" var="item">
            <c:set var="subtotal" value="${subtotal + (item.price * item.quantity)}" />
          </c:forEach>

          <c:set var="tax" value="${subtotal * 0.08}" />
          <c:set var="shipping" value="50000" />
          <c:set var="total" value="${subtotal + tax + shipping}" />

          <div class="d-flex justify-content-between">
            <span>Thành tiền</span>
            <span id="subtotal">
            <fmt:formatNumber value="${subtotal}" type="number" pattern="#,##0"/>₫
          </span>
          </div>
          <div class="d-flex justify-content-between">
            <span>Thuế VAT (8%)</span>
            <span id="tax">
            <fmt:formatNumber value="${tax}" type="number" pattern="#,##0"/>₫
          </span>
          </div>
          <div class="d-flex justify-content-between mb-3">
            <span>Phí vận chuyển</span>
            <span id="shipping">50.000₫</span>
          </div>
          <hr>
          <div class="d-flex justify-content-between fw-bold fs-5">
            <span>TỔNG CỘNG</span>
            <span class="text-primary" id="total">
            <fmt:formatNumber value="${total}" type="number" pattern="#,##0"/>₫
          </span>
          </div>
          <div class="input-group my-3">
            <input type="text" class="form-control" id="promoCode" placeholder="Mã giảm giá">
            <button class="btn btn-outline-secondary" id="applyPromo">Áp dụng</button>
          </div>

          <c:choose>
            <c:when test="${empty orderItems}">
              <button class="btn btn-primary w-100 py-2" disabled>
                <i class="bi bi-lock-fill"></i> TIẾN HÀNH THANH TOÁN
              </button>
            </c:when>
            <c:otherwise>
              <a href="${pageContext.request.contextPath}/payments" class="btn btn-primary w-100 py-2">
                <i class="bi bi-lock-fill"></i> TIẾN HÀNH THANH TOÁN
              </a>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
  </div>
</div>

<c:import url="../layout/footer.jsp" />

</body>
</html>