<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Tài khoản của tôi | TTT Bookstore</title>
    <c:import url="../layout/library.jsp"/>
</head>

<body class="d-flex flex-column min-vh-100">

<c:import url="../layout/navbar.jsp"/>

<div class="container my-5 flex-grow-1">

    <div class="row">

        <!-- ========== MENU TRÁI ========== -->
        <div class="col-md-3 mb-4">
            <div class="list-group shadow-sm rounded">
                <a class="list-group-item list-group-item-action active">
                    <i class="bi bi-person"></i> Thông tin tài khoản
                </a>
                <a href="#orders" class="list-group-item list-group-item-action">
                    <i class="bi bi-bag"></i> Đơn hàng của tôi
                </a>
                <a href="#change-password" class="list-group-item list-group-item-action">
                    <i class="bi bi-lock"></i> Đổi mật khẩu
                </a>
            </div>
        </div>

        <!-- ========== NỘI DUNG PHẢI ========== -->
        <div class="col-md-9">

            <!-- ===== THÔNG TIN KHÁCH HÀNG ===== -->
            <div class="card shadow-sm mb-4">
                <div class="card-header fw-bold">
                    Thông tin tài khoản
                </div>
                <div class="card-body">
                    <p><b>Họ tên:</b> ${customer.name}</p>
                    <p><b>Email:</b> ${customer.email}</p>
                    <p><b>Số điện thoại:</b> ${customer.phone}</p>
                    <p><b>Địa chỉ:</b> ${customer.address}</p>
                </div>
            </div>

            <!-- ===== ĐƠN HÀNG ===== -->
            <div class="card shadow-sm mb-4" id="orders">
                <div class="card-header fw-bold">
                    Đơn hàng của tôi
                </div>

                <div class="card-body p-0">
                    <table class="table table-hover mb-0">
                        <thead class="table-light">
                        <tr>
                            <th>Mã đơn</th>
                            <th>Ngày đặt</th>
                            <th>Trạng thái</th>
                            <th>Tổng tiền</th>
                            <th></th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="o" items="${orderList}">
                            <tr>
                                <td>#${o.orderId}</td>
                                <td>${o.createdAt}</td>
                                <td>
                                    <span class="badge
                                        ${o.status == 'COMPLETED' ? 'bg-success' :
                                          o.status == 'PENDING' ? 'bg-warning text-dark' :
                                          'bg-secondary'}">
                                            ${o.status}
                                    </span>
                                </td>
                                <td class="text-danger fw-bold">
                                        ${o.total} ₫
                                </td>
                                <td>
                                    <a href="../order/detail?id=${o.orderId}"
                                       class="btn btn-sm btn-outline-danger">
                                        Chi tiết
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty orderList}">
                            <tr>
                                <td colspan="5" class="text-center text-muted py-3">
                                    Bạn chưa có đơn hàng nào
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- ===== ĐỔI MẬT KHẨU ===== -->
            <form action="${pageContext.request.contextPath}/profile" method="post">
                <input type="hidden" name="action" value="changePassword">

                <div class="mb-3">
                    <label>Mật khẩu hiện tại</label>
                    <input type="password" name="currentPassword" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label>Mật khẩu mới</label>
                    <input type="password" name="newPassword" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label>Xác nhận mật khẩu</label>
                    <input type="password" name="confirmPassword" class="form-control" required>
                </div>

                <button class="btn btn-danger">Cập nhật mật khẩu</button>
            </form>


        </div>
    </div>
</div>

<c:import url="../layout/footer.jsp"/>

</body>
</html>
