<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 12/12/2025
  Time: 4:30 CH
  To change this template use File | Settings | File Templates.
--%>
<%--
  File: add.jsp
  Description: Form thêm mới Customer
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container my-5 d-flex justify-content-center">
    <div class="card shadow-lg border-0" style="max-width: 550px; width: 100%;">

        <div class="card-header bg-success text-white text-center py-3">
            <h4 class="mb-0 fw-bold"><i class="bi bi-person-plus-fill me-2"></i> Add New Customer</h4>
        </div>

        <div class="card-body p-4">
            <form action="/customer?action=add" method="post">

                <div class="mb-3">
                    <label for="accountId" class="form-label fw-semibold">Account ID <span class="text-danger">*</span></label>
                    <input type="number" name="accountId" id="accountId" class="form-control" placeholder="Enter associated Account ID (e.g., 1)" required min="1">
                </div>

                <div class="mb-3">
                    <label for="name" class="form-label fw-semibold">Full Name <span class="text-danger">*</span></label>
                    <input type="text" name="name" id="name" class="form-control" placeholder="Enter customer's full name" required>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label fw-semibold">Email</label>
                    <input type="email" name="email" id="email" class="form-control" placeholder="Enter email address (e.g., example@domain.com)">
                </div>

                <div class="mb-3">
                    <label for="phone" class="form-label fw-semibold">Phone Number</label>
                    <input type="tel" name="phone" id="phone" class="form-control" placeholder="Enter phone number">
                </div>

                <div class="mb-4">
                    <label for="address" class="form-label fw-semibold">Address</label>
                    <textarea name="address" id="address" class="form-control" rows="3" placeholder="Enter full address"></textarea>
                </div>

                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-success btn-lg fw-semibold">
                        <i class="bi bi-save me-2"></i> Save New Customer
                    </button>
                    <a href="/customer" class="btn btn-outline-secondary">
                        <i class="bi bi-arrow-left"></i> Back to Customer List
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>