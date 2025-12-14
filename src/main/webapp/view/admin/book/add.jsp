<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 12/12/2025
  Time: 4:30 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Book</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<h1>Add New Book Information</h1>
<div class="container mt-5 d-flex justify-content-center">
    <form action="/book?action=add" method="post" enctype="multipart/form-data" class="p-4 bg-light border rounded-4 shadow"
          style="max-width: 500px; width: 100%;">
        <h4 class="text-center mb-4 text-primary fw-bold">Book Form</h4>
        <div class="mb-3">
            <label class="form-label fw-semibold">category</label>
            <select name="categoryId" class="form-select-lg">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label fw-semibold">Author</label>
            <select name="authorId" class="form-select-lg">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label fw-semibold">publisher</label>
            <select name="publisherId" class="form-select-lg">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label fw-semibold">Title</label>
            <input name="title" class="form-control" placeholder="Enter book title">
        </div>

        <div class="mb-3">
            <label class="form-label fw-semibold">Description</label>
            <input type="text" name="description" class="form-control" placeholder="Enter description">
        </div>

        <div class="mb-3">
            <label class="form-label fw-semibold">Price</label>
            <input type="number" name="price"  class="form-control" placeholder="Enter price">
        </div>
        <div class="mb-3">
            <label class="form-label fw-semibold">Stock</label>
            <input type="number" name="stock" class="form-control" placeholder="Enter stock">
        </div>
        <div class="mb-3">
            <label class="form-label fw-semibold">Upload Files</label>
            <input type="file" name="imageURL" accept="image/*" multiple>
        </div>
        <button class="btn btn-primary w-100 fw-semibold">Save</button>
    </form>
</div>
</body>
</html>
