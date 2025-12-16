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
    <div class="col-lg-8" id="cart-items-container">
      <c:choose>

        <c:when test="${empty cartItems}">
          <div class="alert alert-warning mt-4">
            Giỏ hàng của bạn đang trống.
          </div>
        </c:when>

        <c:otherwise>

          <p class="text-muted">
            Bạn có <b>${cartItems.size()}</b> sản phẩm trong giỏ hàng
          </p>

          <c:forEach items="${cartItems}" var="item">
            <div class="card mb-3">
              <div class="row g-0">
                <div class="col-md-3">
                  <img src="${item.imageUrl}" class="img-fluid">
                </div>
                <div class="col-md-6">
                  <h5>${item.title}</h5>
                  <p>Giá: ${item.price}</p>
                </div>
                <div class="col-md-3">
                  SL: ${item.quantity}<br>
                  Tổng: ${item.total}
                </div>
              </div>
            </div>
          </c:forEach>


        </c:otherwise>

      </c:choose>

    </div>

    <div class="col-lg-4 position-sticky" style="top:75px;">
      <div class="card p-4 shadow">
        <h5 class="fw-bold mb-3">Tóm tắt đơn hàng</h5>
        <div class="d-flex justify-content-between">
          <span>Thành tiền</span><span id="subtotal">$0.00</span>
        </div>
        <div class="d-flex justify-content-between">
          <span>Thuế VAT (8%)</span><span id="tax">$0.00</span>
        </div>
        <div class="d-flex justify-content-between mb-3">
          <span>Phí vận chuyển</span><span id="shipping">$5.99</span>
        </div>
        <hr>
        <div class="d-flex justify-content-between fw-bold fs-5">
          <span>TỔNG CỘNG</span><span class="text-primary" id="total">$0.00</span>
        </div>
        <div class="input-group my-3">
          <input type="text" class="form-control" id="promoCode" placeholder="Mã giảm giá">
          <button class="btn btn-outline-secondary" id="applyPromo">Áp dụng</button>
        </div>
        <a href="../payment/payment.jsp" class="btn btn-primary w-100 py-2 btn-gradient">
          <i class="bi bi-lock-fill"></i> TIẾN HÀNH THANH TOÁN
        </a>
      </div>
    </div>
  </div>
</div>
</div>
<c:import url="../layout/footer.jsp" />
</body>
</html>

