<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 13/12/2025
  Time: 8:41 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<c:set var="customer" value="${requestScope.customer}"/>

<div class="container my-5 d-flex justify-content-center">
    <div class="card shadow-lg border-0" style="max-width: 550px; width: 100%;">

        <div class="card-header bg-primary text-white text-center py-3">
            <h4 class="mb-0 fw-bold"><i class="bi bi-person-lines-fill me-2"></i> Update Customer Information</h4>
        </div>

        <div class="card-body p-4">
            <%-- POST form đến action=update --%>
            <form action="/customer?action=update" method="post">

                <%-- Trường ẩn chứa ID khách hàng (rất quan trọng cho việc cập nhật) --%>
                <input type="hidden" name="id" value="${customer.id}">

                <div class="alert alert-warning text-center">
                    **Customer ID:** ${customer.id} | **Name:** ${customer.name}
                </div>

                <div class="mb-3">
                    <label for="accountId" class="form-label fw-semibold">Account ID <span class="text-danger">*</span></label>
                    <%-- Điền giá trị cũ vào trường input --%>
                    <input type="number" name="accountId" id="accountId" class="form-control"
                           placeholder="Enter associated Account ID (e.g., 1)"
                           value="${customer.accountId}" required min="1">
                </div>

                <div class="mb-3">
                    <label for="name" class="form-label fw-semibold">Full Name <span
                            class="text-danger">*</span></label>
                    <input type="text" name="name" id="name" class="form-control"
                           placeholder="Enter customer's full name"
                           value="${customer.name}" required>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label fw-semibold">Email</label>
                    <input type="email" name="email" id="email" class="form-control"
                           placeholder="Enter email address (e.g., example@domain.com)"
                           value="${customer.email}">
                </div>

                <div class="mb-3">
                    <label for="phone" class="form-label fw-semibold">Phone Number</label>
                    <input type="tel" name="phone" id="phone" class="form-control"
                           placeholder="Enter phone number"
                           value="${customer.phone}">
                </div>

                <div class="mb-4">
                    <label for="address" class="form-label fw-semibold">Address</label>
                    <textarea name="address" id="address" class="form-control" rows="3"
                              placeholder="Enter full address">${customer.address}</textarea>
                </div>

                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary btn-lg fw-semibold">
                        <i class="bi bi-save me-2"></i> Save Changes
                    </button>
                    <a href="/customer" class="btn btn-outline-secondary">
                        <i class="bi bi-arrow-left"></i> Cancel and Back
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
