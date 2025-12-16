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

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-8">

      <div class="card shadow">
        <div class="card-header bg-warning text-dark">
          <h4 class="mb-0">✏️ Update Book</h4>
        </div>

        <div class="card-body">
          <form action="/book?action=update" method="post">

            <!-- ID (hidden) -->
            <input type="hidden" name="id" value="${book.id}">

            <!-- Title -->
            <div class="mb-3">
              <label class="form-label">Title</label>
              <input type="text"
                     name="title"
                     value="${book.title}"
                     class="form-control"
                     required>
            </div>

            <!-- Description -->
            <div class="mb-3">
              <label class="form-label">Description</label>
              <textarea name="description"
                        class="form-control"
                        rows="3"
                        required>${book.description}</textarea>
            </div>

            <!-- Price -->
            <div class="mb-3">
              <label class="form-label">Price</label>
              <input type="number"
                     step="0.01"
                     name="price"
                     value="${book.price}"
                     class="form-control"
                     required>
            </div>

            <!-- Stock -->
            <div class="mb-3">
              <label class="form-label">Stock</label>
              <input type="number"
                     name="stock"
                     value="${book.stock}"
                     class="form-control"
                     required>
            </div>

            <!-- Category -->
            <div class="mb-3">
              <label class="form-label">Category ID</label>
              <input type="number"
                     name="categoryId"
                     value="${book.categoryId}"
                     class="form-control"
                     required>
            </div>

            <!-- Author -->
            <div class="mb-3">
              <label class="form-label">Author ID</label>
              <input type="number"
                     name="authorId"
                     value="${book.authorId}"
                     class="form-control"
                     required>
            </div>

            <!-- Publisher -->
            <div class="mb-3">
              <label class="form-label">Publisher ID</label>
              <input type="number"
                     name="publisherId"
                     value="${book.publisherId}"
                     class="form-control"
                     required>
            </div>

            <!-- Image -->
            <div class="mb-3">
              <label class="form-label">Image URL</label>
              <input type="text"
                     name="imgURL"
                     value="${book.imgURL}"
                     class="form-control">
            </div>

            <!-- Buttons -->
            <div class="d-flex justify-content-between">
              <a href="/book" class="btn btn-secondary">⬅ Back</a>
              <button type="submit" class="btn btn-warning">💾 Update</button>
            </div>

          </form>
        </div>
      </div>

    </div>
  </div>
</div>

</body>
</html>