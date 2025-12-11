<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/2/2025
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Thanh toán | TTT Bookstore</title>

    <c:import url="../layout/library.jsp" />
    <link rel="stylesheet" href="../payment/payment.jsp">
    <style>
        .card {
            border-radius: 14px;
        }

        button.btn-danger {
            background: #e53935;
            border: none;
        }

        button.btn-danger:hover {
            background: #c62828;
        }

        .form-control {
            height: 45px;
            border-radius: 10px;
        }

    </style>
</head>
<body class="bg-light">

<c:import url="../layout/navbar.jsp" />

<div class="container py-5">
    <div class="row g-4">

        <!-- LEFT -->
        <div class="col-lg-8">

            <!-- Thông tin giao hàng -->
            <div class="card p-4 shadow-sm mb-4">
                <h4 class="fw-bold mb-3">Thông tin giao hàng</h4>

                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label">Họ tên</label>
                        <input type="text" class="form-control" placeholder="Nguyễn Văn A">
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Số điện thoại</label>
                        <input type="text" class="form-control" placeholder="0123 456 789">
                    </div>

                    <div class="col-12">
                        <label class="form-label">Địa chỉ</label>
                        <input type="text" class="form-control" placeholder="Số nhà, đường, phường/xã...">
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Tỉnh/Thành phố</label>
                        <input type="text" class="form-control" placeholder="TP. Hồ Chí Minh">
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Quận/Huyện</label>
                        <input type="text" class="form-control" placeholder="Quận 1">
                    </div>
                </div>
            </div>

            <!-- Phương thức thanh toán -->
            <div class="card p-4 shadow-sm">
                <h4 class="fw-bold mb-3">Phương thức thanh toán</h4>

                <div class="form-check mb-2">
                    <input class="form-check-input" type="radio" name="payment" checked>
                    <label class="form-check-label fw-semibold">
                        Thanh toán khi nhận hàng (COD)
                    </label>
                </div>

                <div class="form-check mb-2">
                    <input class="form-check-input" type="radio" name="payment">
                    <label class="form-check-label fw-semibold">
                        Chuyển khoản ngân hàng
                    </label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="radio" name="payment">
                    <label class="form-check-label fw-semibold">
                        Thanh toán qua ví Momo / ZaloPay
                    </label>
                </div>
            </div>
        </div>

        <!-- RIGHT -->
        <div class="col-lg-4">
            <div class="card p-4 shadow-sm position-sticky" style="top: 80px">
                <h5 class="fw-bold mb-3">Tóm tắt đơn hàng</h5>

                <div class="d-flex justify-content-between mb-2">
                    <span>Tạm tính</span>
                    <span id="sumSubtotal">$0.00</span>
                </div>

                <div class="d-flex justify-content-between mb-2">
                    <span>Thuế VAT (8%)</span>
                    <span id="sumTax">$0.00</span>
                </div>

                <div class="d-flex justify-content-between mb-3">
                    <span>Phí vận chuyển</span>
                    <span id="sumShip">$5.99</span>
                </div>

                <hr>

                <div class="d-flex justify-content-between fw-bold fs-5 mb-3">
                    <span>TỔNG CỘNG</span>
                    <span class="text-danger" id="sumTotal">$0.00</span>
                </div>

                <button class="btn btn-danger w-100 py-2 fw-bold">
                    Đặt hàng ngay
                </button>
            </div>
        </div>
    </div>
</div>

<c:import url="../layout/footer.jsp" />

<script>
    // Lấy total từ local/cart → mục đích: hiển thị lại giá đã tính ở trang giỏ hàng
    const total = localStorage.getItem("cartTotal") || 0;

    const subtotal = total * 0.92;
    const tax = subtotal * 0.08;
    const shipping = 5.99;
    const finalTotal = subtotal + tax + shipping;

    document.getElementById("sumSubtotal").innerText = "$" + subtotal.toFixed(2);
    document.getElementById("sumTax").innerText = "$" + tax.toFixed(2);
    document.getElementById("sumShip").innerText = "$" + shipping.toFixed(2);
    document.getElementById("sumTotal").innerText = "$" + finalTotal.toFixed(2);
</script>

