<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 13/12/2025
  Time: 8:41 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Book</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container my-5 d-flex justify-content-center">
    <div class="card shadow-lg border-0" style="max-width: 650px; width: 100%;">
        <div class="card-header bg-warning text-dark text-center py-3">
            <h4 class="mb-0 fw-bold">✏️ Update Book</h4>
        </div>

        <div class="card-body p-4">
            <form action="/book?action=update" method="post">

                <!-- ID (hidden) -->
                <input type="hidden" name="id" value="${book.id}">

                <!-- BASIC INFO -->
                <fieldset class="mb-4 p-3 border rounded-3">
                    <legend class="float-none w-auto px-2 fs-6 fw-semibold text-secondary">
                        Basic Information
                    </legend>

                    <div class="mb-3">
                        <label class="form-label fw-semibold">Title</label>
                        <input name="title" class="form-control" onchange="checkValidate()"
                               value="${book.title}" required>
                    </div>
                    <small id="errorName" class="text-danger"></small>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Description</label>
                        <textarea name="description" class="form-control" onchange="checkValidate()"
                                  rows="3">${book.description}</textarea>
                    </div>
                    <small id="errorDescription" class="text-danger"></small>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-semibold">Price (VND)</label>
                            <input type="number" name="price"
                                   class="form-control text-end"
                                   onchange="checkValidate()"
                                   value="${book.price}" min="0" step="1000">
                        </div>
                        <small id="errorPrice" class="text-danger"></small>

                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-semibold">Stock</label>
                            <input type="number" name="stock"
                                   class="form-control text-end"
                                   onchange="checkValidate()"
                                   value="${book.stock}" min="0" required>
                        </div>
                        <small id="errorStock" class="text-danger"></small>
                    </div>
                </fieldset>

                <!-- RELATION INFO -->
                <div class="mb-3">
                    <label class="form-label fw-semibold">Category</label>
                    <select name="categoryId" class="form-select">
                        <c:forEach var="category" items="${categoryList}">
                            <option value="${category.id}"
                                    <c:if test="${category.name == book.categoryName}">
                                        selected
                                    </c:if>
                            >
                                    ${category.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Author</label>
                    <select name="authorId" class="form-select">
                        <c:forEach var="author" items="${authorList}">
                            <option value="${author.id}"
                                    <c:if test="${author.name == book.authorName}">
                                        selected
                                    </c:if>
                            >
                                    ${author.name}
                            </option>
                        </c:forEach>
                    </select>

                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Publisher</label>
                    <select name="publisherId" class="form-select">
                        <c:forEach var="publisher" items="${publisherList}">
                            <option value="${publisher.id}"
                                    <c:if test="${publisher.name == book.publisherName}">
                                        selected
                                    </c:if>
                            >
                                    ${publisher.name}
                            </option>

                        </c:forEach>
                    </select>
                </div>

                <!-- IMAGE -->
                <div class="mb-4">
                    <label class="form-label fw-semibold">Image URL</label>
                    <input type="text" name="imgURL" class="form-control"
                           value="${book.imgURL}">
                </div>

                <!-- BUTTON -->
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-warning btn-lg fw-semibold">
                        Update Book
                    </button>
                    <a href="/book" class="btn btn-outline-secondary">
                        Back to List
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
<script>
    function checkValidate() {
        let name = document.getElementById("title").value;
        let description = document.getElementById("description").value;
        let price = document.getElementById("price").value;
        let stock = document.getElementById("stock").value;
        let isName = false;
        let isPrice = false;
        let isStock = false;
        let isDescription = false;
        if (name === "") {
            document.getElementById("errorName").innerHTML = "Product Name can not blank";
            isName = false;
        } else {
            document.getElementById("errorName").innerHTML = ""
            isName = true;
        }
        if (description === "") {
            document.getElementById("errorDescription").innerHTML = "Description can not blank";
            isDescription = false;
        } else {
            document.getElementById("errorDescription").innerHTML = ""
            isDescription = true;
        }
        if (price === "") {
            document.getElementById("errorPrice").innerHTML = "Price can not blank"
            isPrice = false;
        } else if (!(/^[0-9]+$/.test(price))) {
            document.getElementById("errorPrice").innerHTML = "Invalid Price!"
            isPrice = false;
        } else if (Number(price) < 10000) {
            document.getElementById("errorPrice").innerHTML = "Price should be larger than 10.000!"
            isPrice = false;

        } else {
            document.getElementById("errorPrice").innerHTML = ""
            isPrice = true;
        }
        if (stock === "") {
            document.getElementById("errorStock").innerHTML = "Quantity can not blank!"
            isStock = false;
        } else if (!(/^[0-9]+$/.test(price))) {
            document.getElementById("errorStock").innerHTML = "Invalid quantity!"
            isStock = false;
        } else if (Number(stock) <= 0) {
            document.getElementById("errorStock").innerHTML = "Quantity should be larger than 0!"
            isStock = false;

        } else {
            document.getElementById("errorStock").innerHTML = ""
            isStock = true;
        }

        if (isName && isDescription && isPrice && isStock) {
            document.getElementById("btn-save").disabled = false;
        } else {
            document.getElementById("btn-save").disabled = true;
        }
    }
</script>
</html>
