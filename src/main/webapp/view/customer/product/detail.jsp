<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/2/2025
  Time: 2:10 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chi tiết sản phẩm | TTT Bookstore</title>
    <c:import url="../layout/library.jsp"/>
</head>
<body class="bg-light">

<c:import url="../layout/navbar.jsp"/>

<div class="container my-5">

    <!-- ✅ TRƯỜNG HỢP CÓ SẢN PHẨM -->
    <c:if test="${product != null}">
        <div class="row">

            <!-- IMAGE -->
            <div class="col-md-5">
                <div class="card shadow-sm">
                    <img src="${product.imgURL}"
                         class="card-img-top"
                         alt="${product.title}"
                         onerror="this.src='https://via.placeholder.com/400x550'">
                </div>
            </div>

            <!-- DETAIL -->
            <div class="col-md-7">
                <h3 class="fw-bold">${product.title}</h3>

                <p class="text-muted mb-1">
                    Tác giả: <b>${product.authorName}</b>
                </p>

                <p class="text-muted mb-2">
                    Thể loại: <b>${product.categoryName}</b>
                </p>

                <h4 class="text-danger fw-bold">
                        ${product.price} ₫
                </h4>

                <p class="mt-3">
                    <b>Mô tả:</b><br>
                        ${product.description}
                </p>

                <!-- QUANTITY -->
                <div class="d-flex align-items-center mb-3">
                    <label class="me-2 fw-bold">Số lượng:</label>
                    <input type="number"
                           value="1"
                           min="1"
                           max="${product.stock}"
                           class="form-control"
                           style="width:90px;">
                    <span class="ms-2 text-muted">
                        (Còn ${product.stock} sản phẩm)
                    </span>
                </div>

                <!-- BUTTONS -->
                <div class="d-flex gap-3">
                    <button class="btn btn-danger px-4">
                        <i class="bi bi-cart-plus"></i> Thêm vào giỏ
                    </button>

                    <button class="btn btn-warning px-4">
                        <i class="bi bi-lightning-fill"></i> Mua ngay
                    </button>
                </div>

                <!-- EXTRA INFO -->
                <div class="mt-4">
                    <h5>Thông tin chi tiết</h5>
                    <ul>
                        <li>Tác giả: ${product.authorName}</li>
                        <li>Nhà xuất bản: ${product.publisherName}</li>
                        <li>Tồn kho: ${product.stock}</li>
                    </ul>
                </div>
            </div>
        </div>
    </c:if>

</div>

<c:import url="../layout/footer.jsp"/>

</body>
</html>
