<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 16/12/2025
  Time: 4:43 CH
  To change this template use File | Settings | File Templates.
--%>
<%-- /view/admin/order/add.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add New Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<%@ include file="/view/admin/layout/navbar.jsp" %>
<div class="container mt-5">
    <h2 class="mb-4">Add New Order (For Testing)</h2>

    <form action='<c:url value="/order?action=add"/>' method="post">

        <div class="mb-3">
            <label for="customerId" class="form-label">Customer ID:</label>
            <input type="number" class="form-control" id="customerId" name="customerId" required>
            <div class="form-text">Nhập ID của khách hàng đã tồn tại.</div>
        </div>

        <div class="mb-3">
            <label for="status" class="form-label">Status:</label>
            <select class="form-select" id="status" name="status" required>
                <option value="PENDING">PENDING</option>
                <option value="CONFIRMED">CONFIRMED</option>
                <option value="SHIPPED">SHIPPED</option>
                <option value="COMPLETED">COMPLETED</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="total" class="form-label">Total Amount:</label>
            <input type="number" step="0.01" class="form-control" id="total" name="total" required>
        </div>

        <button type="submit" class="btn btn-primary">Add Order</button>
        <a href='<c:url value="/order"/>' class="btn btn-secondary">Cancel</a>
    </form>

    <p class="mt-4 alert alert-warning">
        LƯU Ý: Chức năng này chỉ thêm đơn hàng gốc. Bạn cần thêm chi tiết OrderItem thủ công bằng SQL nếu muốn đơn hàng đó có sản phẩm.
    </p>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
