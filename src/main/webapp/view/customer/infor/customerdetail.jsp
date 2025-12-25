<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/24/2025
  Time: 4:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">

    <!-- Header -->
    <div class="row mb-4">
        <div class="col">
            <h3 class="fw-bold">
                <i class="bi bi-plus-circle"></i> Add New Customer
            </h3>
        </div>
    </div>



    <!-- Form Card -->
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">

                    <form method="post"
                          action="${pageContext.request.contextPath}/customer"
                          class="needs-validation"
                          novalidate>

                        <input type="hidden" name="action" value="addByCustomer">

                        <!-- Product Name -->
                        <div class="mb-3">
                            <label class="form-label fw-semibold">Customer Name</label>
                            <input type="text"
                                   name="name"
                                   class="form-control"
                                   placeholder="Enter your name"
                                   value="${param.name}"
                                   required
                                   minlength="3">
                            <div class="invalid-feedback">
                                Name must be at least 3 characters.
                            </div>
                        </div>

                        <!-- Price -->
                        <div class="mb-3">
                            <label class="form-label fw-semibold">Email</label>
                            <input type="text"
                                   name="email"
                                   class="form-control"
                                   placeholder="Enter email"
                                   value="${param.email}"
                                   required>
                            <div class="invalid-feedback">
                                Invalid email.
                            </div>
                        </div>


                        <div class="mb-3">
                            <label class="form-label fw-semibold">Phone</label>
                            <input type="text"
                                   name="phone"
                                   class="form-control"
                                   placeholder="Enter phone"
                                   value="${param.phone}"
                                   required>
                            <div class="invalid-feedback">
                                Invalid phone.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Address</label>
                            <input type="text"
                                   name="address"
                                   class="form-control"
                                   placeholder="Enter product description"
                                   value="${param.address}"
                                   required
                                   minlength="3">
                            <div class="invalid-feedback">
                                Product description must be at least 3 characters.
                            </div>
                        </div>

<%--                        <!-- Category -->--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label fw-semibold">Category</label>--%>
<%--                            <select name="categoryId" class="form-select" required>--%>
<%--                                <option value="">-- Select Category --</option>--%>
<%--                                <c:forEach var="category" items="${categories}">--%>
<%--                                    <option value="${category.id}"--%>
<%--                                        ${param.categoryId == category.id ? "selected" : ""}>--%>
<%--                                            ${category.name}--%>
<%--                                    </option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                            <div class="invalid-feedback">--%>
<%--                                Please select a category.--%>
<%--                            </div>--%>
<%--                        </div>--%>

                        <!-- Buttons -->
                        <div class="d-flex justify-content-end gap-2 mt-4">
                            <a href="${pageContext.request.contextPath}/customer/profile"
                               class="btn btn-secondary">
                                <i class="bi bi-arrow-left"></i> Back
                            </a>
                            <button type="submit" class="btn btn-primary">
                                <i class="bi bi-save"></i> Save Customer
                            </button>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </div>

</div>

<!-- Bootstrap validation -->
<script>
    (() => {
        'use strict';
        const forms = document.querySelectorAll('.needs-validation');
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
    // Disable submit button on form submit to prevent multiple submissions
        document.querySelector("form").addEventListener("submit", function () {
        document.getElementById("submitBtn").disabled = true;
    });


</script>
</body>
</html>
