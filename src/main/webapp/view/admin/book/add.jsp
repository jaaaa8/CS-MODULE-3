<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 12/12/2025
  Time: 4:30 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Add New Book</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container my-5 d-flex justify-content-center">
    <div class="card shadow-lg border-0" style="max-width: 650px; width: 100%;">
        <div class="card-header bg-primary text-white text-center py-3"><h4 class="mb-0 fw-bold"><i
                class="bi bi-book me-2"></i> Add New Book
            Information</h4></div>
        <div class="card-body p-4">
            <form action="/book?action=add" method="post" enctype="multipart/form-data">
                <fieldset class="mb-4 p-3 border rounded-3">
                    <legend class="float-none w-auto px-2 fs-6 fw-semibold text-secondary"><i
                            class="bi bi-info-circle"></i> Basic Information
                    </legend>
                    <div class="mb-3"><label for="title" class="form-label fw-semibold">Title <span class="text-danger">*</span></label>
                        <input name="title" id="title" class="form-control" placeholder="Enter book title" required>
                    </div>
                    <div class="mb-3"><label for="description" class="form-label fw-semibold">Description</label>
                        <textarea name="description" id="description" class="form-control" rows="3"
                                  placeholder="Enter detailed description"></textarea></div>
                    <div class="row">
                        <div class="col-md-6 mb-3"><label for="price" class="form-label fw-semibold">Price (VND)</label>
                            <div class="input-group"><input type="number" name="price" id="price"
                                                            class="form-control text-end" placeholder="0" min="0"
                                                            step="1000"> <span class="input-group-text">₫</span></div>
                        </div>
                        <div class="col-md-6 mb-3"><label for="stock" class="form-label fw-semibold">Stock
                            Quantity</label> <input type="number" name="stock" id="stock" class="form-control text-end"
                                                    placeholder="0" min="0" required></div>
                    </div>
                </fieldset>
                <div class="row">
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Category</label>
                        <select name="categoryId" class="form-select">
                            <c:forEach var="category" items="${categoryList}">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Author</label>
                        <select name="authorId" class="form-select">
                            <c:forEach var="author" items="${authorList}">
                                <option value="${author.id}">${author.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Publisher</label>
                        <select name="publisherId" class="form-select">
                            <c:forEach var="publisher" items="${publisherList}">
                                <option value="${publisher.id}">${publisher.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="mb-4"><label for="imageURL" class="form-label fw-semibold">Upload Book Cover(s)</label>
                    <input type="file" name="imageURL" id="imageURL" class="form-control" accept="image/*" multiple>
                    <div class="form-text">Choose one or more image files (JPEG, PNG).</div>
                </div>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary btn-lg fw-semibold"><i class="bi bi-save me-2"></i>
                        Save Book
                    </button>
                    <a href="/book" class="btn btn-outline-secondary"> <i class="bi bi-arrow-left"></i> Back to List
                    </a></div>
            </form>
        </div>
    </div>
</div>
</body>
</html>