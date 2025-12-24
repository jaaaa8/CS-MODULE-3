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

<c:set var="customer" value="${customer}" />

<div class="container my-5 d-flex justify-content-center">
    <div class="card shadow-lg border-0" style="max-width: 550px; width: 100%;">

        <div class="card-header bg-primary text-white text-center py-3">
            <h4 class="mb-0 fw-bold">
                <i class="bi bi-person-lines-fill me-2"></i> Update Customer
            </h4>
        </div>

        <div class="card-body p-4">
            <form action="/customer?action=update" method="post">

                <!-- hidden id -->
                <input type="hidden" name="id" value="${customer.id}">

                <!-- ACCOUNT SELECT -->
                <div class="mb-3">
                    <label class="form-label fw-semibold">Account</label>
                    <select name="accountId" class="form-select" required>
                        <c:forEach var="account" items="${accountList}">
                            <option value="${account.id}"
                                ${account.id == customer.accountId ? "selected" : ""}>
                                    ${account.username}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- NAME -->
                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        Full Name <span class="text-danger">*</span>
                    </label>
                    <input type="text" name="name" class="form-control"
                           value="${customer.name}" required>
                </div>

                <!-- EMAIL -->
                <div class="mb-3">
                    <label class="form-label fw-semibold">Email</label>
                    <input type="email" name="email" class="form-control"
                           value="${customer.email}">
                </div>

                <!-- PHONE -->
                <div class="mb-3">
                    <label class="form-label fw-semibold">Phone</label>
                    <input type="text" name="phone" class="form-control"
                           value="${customer.phone}">
                </div>

                <!-- ADDRESS -->
                <div class="mb-4">
                    <label class="form-label fw-semibold">Address</label>
                    <textarea name="address" class="form-control" rows="3">
                        ${customer.address}</textarea>
                </div>

                <!-- BUTTON -->
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary btn-lg fw-semibold">
                        <i class="bi bi-save me-2"></i> Update Customer
                    </button>
                    <a href="/customer" class="btn btn-outline-secondary">
                        <i class="bi bi-arrow-left"></i> Back
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
