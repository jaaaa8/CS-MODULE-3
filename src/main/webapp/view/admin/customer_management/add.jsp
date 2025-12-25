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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <form action="${pageContext.request.contextPath}/admin/customer?action=add" method="post">

                <div class="mb-3">
                    <label class="form-label fw-semibold">Account</label>
                    <select name="accountId" class="form-select">
                        <c:forEach var="account" items="${accountList}">
                            <option value="${account.id}">${account.username}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="name" class="form-label fw-semibold">Full Name <span class="text-danger">*</span></label>
                    <input type="text" name="name" id="name" class="form-control" onchange="checkValidate()" placeholder="Enter customer's full name" required>
                </div>
                <small id="errorName" class="text-danger"></small>
                <div class="mb-3">
                    <label for="email" class="form-label fw-semibold">Email</label>
                    <input type="email" name="email" id="email" class="form-control" onchange="checkValidate()" placeholder="Enter email address (e.g., example@domain.com)">
                </div>
                <small id="errorEmail" class="text-danger"></small>
                <div class="mb-3">
                    <label for="phone" class="form-label fw-semibold">Phone Number</label>
                    <input type="tel" name="phone" id="phone" class="form-control" onchange="checkValidate()" placeholder="Enter phone number">
                </div>
                <small id="errorPhone" class="text-danger"></small>
                <div class="mb-4">
                    <label for="address" class="form-label fw-semibold">Address</label>
                    <textarea name="address" id="address" class="form-control" onchange="checkValidate()" rows="3" placeholder="Enter full address"></textarea>
                </div>
                <small id="errorAddress" class="text-danger"></small>
                <div class="d-grid gap-2">
                    <button type="submit" id="btn-save" class="btn btn-success btn-lg fw-semibold">
                        <i class="bi bi-save me-2"></i> Save New Customer
                    </button>
                    <a href="${pageContext.request.contextPath}/admin/customer" class="btn btn-outline-secondary">
                        <i class="bi bi-arrow-left"></i> Back to Customer List
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    function checkValidate() {
        // Lấy giá trị từ form
        let name = document.getElementById("name").value.trim();
        let email = document.getElementById("email").value.trim();
        let phone = document.getElementById("phone").value.trim();
        let address = document.getElementById("address").value.trim();

        let isName = false;
        let isEmail = false;
        let isPhone = false;
        let isAddress = false;

        // Validate tên
        if (name === "") {
            document.getElementById("errorName").innerText = "Full Name can not blank";
            isName = false;
        } else {
            document.getElementById("errorName").innerText = "";
            isName = true;
        }

        // Validate email
        if (email !== "") {
            let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(email)) {
                document.getElementById("errorEmail").innerText = "Invalid email!";
                isEmail = false;
            } else {
                document.getElementById("errorEmail").innerText = "";
                isEmail = true;
            }
        } else {
            document.getElementById("errorEmail").innerText = "";
            isEmail = true;
        }

        // Validate phone
        if (phone !== "") {
            let phonePattern = /^[0-9]{10,15}$/;
            if (!phonePattern.test(phone)) {
                document.getElementById("errorPhone").innerText = "Invalid Phone Number, type again!";
                isPhone = false;
            } else {
                document.getElementById("errorPhone").innerText = "";
                isPhone = true;
            }
        } else {
            document.getElementById("errorPhone").innerText = "";
            isPhone = true;
        }

        if(address===""){
            document.getElementById("errorAddress").innerText = "Address can not blank";
            isAddress = false;
        }else{
            document.getElementById("errorAddress").innerText = "";
            isAddress = true;
        }

        // Bật/tắt nút submit
        if (isName && isAddress && isEmail && isPhone) {
            document.getElementById("btn-save").disabled = false;
        } else {
            document.getElementById("btn-save").disabled = true;
        }
    }
</script>
</html>