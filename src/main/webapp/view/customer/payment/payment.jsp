<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Thanh toán | TTT Bookstore</title>

    <c:import url="../layout/library.jsp" />

    <style>
        .card {
            border-radius: 14px;
        }
        .form-control {
            height: 45px;
            border-radius: 10px;
        }
        .btn-danger {
            background: #e53935;
            border: none;
        }
        .btn-danger:hover {
            background: #c62828;
        }
    </style>
</head>

<body class="bg-light">

<c:import url="../layout/navbar.jsp" />

<form action="${pageContext.request.contextPath}/checkout" method="post">
    <div class="container py-5">
        <div class="row g-4">

            <!-- LEFT -->
            <div class="col-lg-8">

                <!-- SHIPPING INFO -->
                <div class="card p-4 shadow-sm mb-4">
                    <h4 class="fw-bold mb-3">Thông tin giao hàng</h4>

                    <div class="row g-3">
                        <div class="col-md-6">
                            <label class="form-label">Họ tên</label>
                            <input name="fullname" required class="form-control">
                        </div>

                        <div class="col-md-6">
                            <label class="form-label">Số điện thoại</label>
                            <input name="phone" required class="form-control">
                        </div>

                        <div class="col-12">
                            <label class="form-label">Địa chỉ</label>
                            <input name="address" required class="form-control">
                        </div>
                    </div>
                </div>

                <!-- PAYMENT METHOD -->
                <div class="card p-4 shadow-sm">
                    <h4 class="fw-bold mb-3">Phương thức thanh toán</h4>

                    <div class="form-check mb-2">
                        <input class="form-check-input" type="radio"
                               name="paymentMethod" value="COD" checked>
                        <label class="form-check-label fw-semibold">
                            Thanh toán khi nhận hàng (COD)
                        </label>
                    </div>

                    <div class="form-check mb-2">
                        <input class="form-check-input" type="radio"
                               name="paymentMethod" value="BANK">
                        <label class="form-check-label fw-semibold">
                            Chuyển khoản ngân hàng
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio"
                               name="paymentMethod" value="EWALLET">
                        <label class="form-check-label fw-semibold">
                            Ví Momo / ZaloPay
                        </label>
                    </div>
                </div>
            </div>

            <!-- RIGHT -->
            <div class="col-lg-4">
                <div class="card p-4 shadow-sm position-sticky" style="top: 90px">
                    <h5 class="fw-bold mb-3">Tóm tắt đơn hàng</h5>

                    <c:forEach items="${cart.items}" var="item">
                        <div class="d-flex justify-content-between small mb-1">
                            <span>${item.product.title} × ${item.quantity}</span>
                            <span>${item.totalPrice} ₫</span>
                        </div>
                    </c:forEach>

                    <hr>

                    <div class="d-flex justify-content-between">
                        <span>Tạm tính</span>
                        <span>${subtotal} ₫</span>
                    </div>

                    <div class="d-flex justify-content-between">
                        <span>VAT (8%)</span>
                        <span>${tax} ₫</span>
                    </div>

                    <div class="d-flex justify-content-between mb-2">
                        <span>Phí ship</span>
                        <span>${shipping} ₫</span>
                    </div>

                    <hr>

                    <div class="d-flex justify-content-between fw-bold fs-5 mb-3">
                        <span>TỔNG CỘNG</span>
                        <span class="text-danger">${total} ₫</span>
                    </div>

                    <form action="${pageContext.request.contextPath}/payment" method="post">
                        <input type="hidden" name="action" value="checkout">

                        <!-- gửi kèm thông tin cần thiết -->
                        <input type="hidden" name="orderId" value="${cart.id}">
                        <input type="hidden" name="subtotal" value="${subtotal}">
                        <input type="hidden" name="tax" value="${tax}">
                        <input type="hidden" name="shipping" value="${shipping}">
                        <input type="hidden" name="total" value="${total}">

                        <button type="submit" class="btn btn-danger w-100 py-2 fw-bold">
                            ĐẶT HÀNG
                        </button>
                    </form>
                </div>
            </div>

        </div>
    </div>
</form>

<c:import url="../layout/footer.jsp" />

</body>
</html>
