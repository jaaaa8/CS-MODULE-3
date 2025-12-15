<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 11/12/2025
  Time: 8:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<h1>Book List</h1>
<a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/book?action=add">Add new product</a>
<input type="text" name="search"><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/book?action=search">Search</a>
<h2>${param.mess}</h2>

<table class="table table-sub-heading-color table-striped">
    <tr>
        <th>No.</th>
        <th>ID</th>
        <th>category_id</th>
        <th>author_id</th>
        <th>publisher_id</th>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
        <th>Stock</th>
        <th>image</th>
        <th>Action</th>
    </tr>

    <c:forEach var="book" items="${bookList}" varStatus="status">
    <tr>
            <td>${status.count}</td>
            <td>${book.id}</td>
            <td>${book.categoryId}</td>
            <td>${book.authorId}</td>
            <td>${book.publisherId}</td>
            <td>${book.title}</td>
            <td>${book.description}</td>
            <td>${book.price}</td>
            <td>${book.stock}</td>
            <td>${book.imgURL}</td>
            <td>
                <button onclick="getInfoToDelete('${book.id}','${book.title}')"
                        type="button" class="btn btn-danger btn-sm"
                        data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Delete
                </button>
                <a href="/book?action=showUpdate&id=${book.id}"
                   class="btn btn-success btn-sm">
                    Update
                </a>
            </td>

        </tr>
    </c:forEach>

    <c:if test="${bookList == null || bookList.isEmpty()}">
    <tr><td colspan="6">No book found</td></tr>
    </c:if>
</table>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/book?action=delete" method="post">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">Delete Product</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    <input type="hidden" name="deleteId" id="deleteId">
                    Delete product: <span id="deleteName" class="text-danger"></span> ?
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Delete</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script>
    function getInfoToDelete(id, name){
        document.getElementById("deleteId").value = id;
        document.getElementById("deleteTitle").innerHTML = title;
    }
    function getInfoToUpdate(id, name, description, price, category_id){
        document.getElementById("updateId").value =id;
        document.getElementById("updateName").innerHTML = name;
        document.getElementById("updateDescription").innerHTML = description;
        document.getElementById("updatePrice").innerHTML = price;
        document.getElementById("updateCategory").innerHTML = category_id;
    }
</script>
</body>
</html>
