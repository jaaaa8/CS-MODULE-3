<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/2/2025
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>TTT Bookstore | Giỏ hàng</title>
  <c:import url="../layout/library.jsp"/>
</head>
<body class="d-flex flex-column min-vh-100">

<c:import url="../layout/navbar.jsp" />

<div class="flex-grow-1">
  <div class="container my-5">
    <div class="row">

      <!-- ORDER ITEM LIST -->
      <div class="col-lg-8" id="orderitem-container">
        <h2 class="fw-bold">Giỏ hàng của bạn</h2>
        <c:if test="${orderItems.size() == 0}">
          <div >
            Giỏ hàng của bạn đang trống. Hãy thêm sản phẩm vào giỏ hàng để tiếp tục mua sắm!
          </div>
        </c:if>
        <c:if test="${orderItems.size() > 0}">
          <div>
            <p class="text-muted">
            Bạn có <span id="orderitem-count">${orderItems.size()}</span> sản phẩm trong giỏ hàng
            </p>
          </div>
          </c:if>

        <c:forEach var="item" items="${orderItems}">
          <div class="card mb-3 shadow-sm">
            <div class="card-body">
              <div class="row align-items-center">

                <!-- bookName -->
                <div class="col-md-4">
                  <h6 class="fw-bold mb-1">${item.bookName}</h6>
                  <small class="text-muted">Order #${item.orderId}</small>
                </div>

                <!-- price -->
                <div class="col-md-2 text-center">
                  <span class="fw-semibold">$${item.price}</span>
                </div>

                <!-- quantity -->
                <div class="col-md-3 text-center">
                  <input type="number"
                         class="form-control text-center"
                         value="${item.quantity}"
                         min="1"
                         data-order-id="${item.orderId}">
                </div>

                <!-- subtotal -->
                <div class="col-md-2 text-end">
                  <span class="fw-bold text-primary">
                    $${item.subtotal}
                  </span>
                </div>

                <!-- remove -->
                <div class="col-md-1 text-end">
                  <button class="btn btn-sm btn-outline-danger"
                          data-order-id="${item.orderId}">
                    <i class="bi bi-trash"></i>
                  </button>
                </div>

              </div>
            </div>
          </div>
        </c:forEach>
      </div>

      <!-- ORDER SUMMARY (GIỮ LAYOUT CŨ) -->
      <div class="col-lg-4 position-sticky" style="top:75px;">
        <div class="card p-4 shadow">
          <h5 class="fw-bold mb-3">Tóm tắt đơn hàng</h5>

          <div class="d-flex justify-content-between">
            <span>Thành tiền</span>
            <span id="order-subtotal">
              $${orderSubtotal}
            </span>
          </div>

          <div class="d-flex justify-content-between">
            <span>Thuế VAT (8%)</span>
            <span id="order-tax">
              $${orderTax}
            </span>
          </div>

          <div class="d-flex justify-content-between mb-3">
            <span>Phí vận chuyển</span>
            <span id="order-shipping">
              $${orderShipping}
            </span>
          </div>

          <hr>

          <div class="d-flex justify-content-between fw-bold fs-5">
            <span>TỔNG CỘNG</span>
            <span class="text-primary" id="order-total">
              $${orderTotal}
            </span>
          </div>

          <div class="input-group my-3">
            <input type="text"
                   class="form-control"
                   id="order-promo-code"
                   placeholder="Mã giảm giá">
            <button class="btn btn-outline-secondary"
                    id="apply-order-promo">
              Áp dụng
            </button>
          </div>

          <a href="../payment/payment.jsp"
             class="btn btn-primary w-100 py-2 btn-gradient">
            <i class="bi bi-lock-fill"></i>THANH TOÁN
          </a>
        </div>
      </div>

    </div>
  </div>
</div>

<c:import url="../layout/footer.jsp" />
</body>
</html>

