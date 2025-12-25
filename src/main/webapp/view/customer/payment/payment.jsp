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

<form action="${pageContext.request.contextPath}/customer/payments" method="post">
    <input type="hidden" name="action" value="checkout">
    <input type="hidden" name="orderId" value="${sessionScope.cart.id}">

    <div class="container py-5">
        <div class="row justify-content-center"> <div class="col-lg-8">

            <h3 class="fw-bold text-center mb-4 text-uppercase">Xác nhận thanh toán</h3>

            <div class="card p-4 shadow-sm mb-4">
                <h5 class="fw-bold mb-3 border-bottom pb-2">
                    <i class="fa fa-map-marker me-2 text-danger"></i>Thông tin giao hàng
                </h5>

                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Họ tên người nhận</label>
                        <input name="fullname" required class="form-control"
                               value="${customer.name}" placeholder="Nhập họ tên...">
                    </div>

                    <div class="col-md-6">
                        <label class="form-label fw-bold">Số điện thoại</label>
                        <input name="phone" required class="form-control"
                               value="${customer.phone}" placeholder="Nhập số điện thoại...">
                    </div>

                    <div class="col-12">
                        <label class="form-label fw-bold">Địa chỉ nhận hàng</label>
                        <input name="address" required class="form-control"
                               value="${customer.address}" placeholder="Số nhà, đường, phường/xã...">
                    </div>
                </div>
            </div>

            <div class="card p-4 shadow-sm mb-4">
                <h5 class="fw-bold mb-3 border-bottom pb-2">
                    <i class="fa fa-credit-card me-2 text-primary"></i>Phương thức thanh toán
                </h5>

                <div class="form-check mb-3 p-3 border rounded bg-white">
                    <input class="form-check-input" type="radio" name="paymentMethod" value="COD" checked id="cod">
                    <label class="form-check-label fw-semibold w-100" for="cod">
                        Thanh toán khi nhận hàng (COD)
                        <div class="small text-muted">Bạn sẽ thanh toán tiền mặt cho shipper khi nhận được hàng.</div>
                    </label>
                </div>

                <div class="form-check mb-3 p-3 border rounded bg-white">
                    <input class="form-check-input" type="radio" name="paymentMethod" value="BANK" id="bank">
                    <label class="form-check-label fw-semibold w-100" for="bank">
                        Chuyển khoản ngân hàng (QR Code)
                        <div class="small text-muted">Quét mã QR để chuyển khoản nhanh chóng.</div>
                    </label>
                </div>
            </div>

            <div class="d-grid gap-2">
                <c:forEach items="${orderItems}" var="item">
                    <div class="d-flex justify-content-between small mb-1">

                        <span>${item.bookName} × ${item.quantity}</span>
                        <span>
                    <fmt:formatNumber value="${item.price * item.quantity}" type="currency" currencySymbol="₫"/>
                </span>
                    </div>
                </c:forEach>
                <form action="${pageContext.request.contextPath}/payment" method="post">
                    <input type="hidden" name="action" value="checkout">

                    <input type="hidden" name="orderId" value="${cart.id}">

                    <button type="submit" class="btn btn-danger w-100 py-2 fw-bold">
                        ĐẶT HÀNG
                    </button>
                </form>
                <div class="text-center mt-2">
                    <a href="${pageContext.request.contextPath}/customer/cart" class="text-decoration-none text-secondary">
                        <i class="fa fa-arrow-left me-1"></i> Quay lại giỏ hàng để xem chi tiết & giá
                    </a>
                </div>
            </div>

        </div>
        </div>
    </div>
</form>

<c:import url="../layout/footer.jsp" />

</body>
</html>




