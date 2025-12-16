<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${product.title} | TTT Bookstore</title>

    <c:import url="../layout/library.jsp"/>

    <style>
        /* ===================== */
        /* IMAGE FIX */
        /* ===================== */
        .product-main-img {
            width: 100%;
            height: 420px;
            object-fit: contain;
            background: #f8f9fa;
        }

        .thumb-img {
            width: 80px;
            height: 100px;
            object-fit: contain;
            cursor: pointer;
            background: #f8f9fa;
        }

        @media (max-width: 768px) {
            .product-main-img {
                height: 320px;
            }
        }
    </style>
</head>

<body class="bg-light">

<c:import url="../layout/navbar.jsp" />

<!-- PRODUCT DETAIL -->
<div class="container my-5 pt-4">
    <div class="row g-4">

        <!-- IMAGE -->
        <div class="col-lg-5 col-md-6">
            <div class="card shadow-sm p-3">
                <img src="${product.imgURL}"
                     class="product-main-img"
                     alt="${product.title}"
                     onerror="this.src='https://via.placeholder.com/400x500'">

                <!-- THUMB (có thể mở rộng sau) -->
                <div class="d-flex justify-content-center gap-2 mt-3">
                    <img src="${product.imgURL}" class="thumb-img img-thumbnail">
                    <img src="${product.imgURL}" class="thumb-img img-thumbnail">
                    <img src="${product.imgURL}" class="thumb-img img-thumbnail">
                </div>
            </div>
        </div>

        <!-- DETAIL -->
        <div class="col-lg-7 col-md-6">
            <h3 class="fw-bold mb-2">${product.title}</h3>

            <p class="text-muted mb-1">
                Tác giả: <b>${product.authorName}</b>
            </p>

            <span class="badge bg-secondary mb-3">
                ${product.categoryName}
            </span>

            <!-- PRICE -->
            <h4 class="text-danger fw-bold mt-3">
                ${product.price} ₫
            </h4>

            <!-- DESCRIPTION -->
            <p class="mt-3">
                <b>Mô tả:</b><br>
                ${product.description}
            </p>

            <!-- QUANTITY -->
            <form action="${pageContext.request.contextPath}/cart" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="bookId" value="${product.id}">
                <button type="submit" class="btn btn-primary">
                    Thêm vào giỏ hàng
                </button>
            </form>


            <!-- EXTRA INFO -->
            <div class="mt-4">
                <h6 class="fw-bold">Thông tin chi tiết:</h6>
                <ul class="mb-0">
                    <li>Tác giả: ${product.authorName}</li>
                    <li>Danh mục: ${product.categoryName}</li>
                    <li>Số lượng còn: ${product.stock}</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<c:import url="../layout/footer.jsp" />
<script>
    const qtyInput = document.querySelector('input[name="quantity"]');
    const maxStock = ${product.stock};

    qtyInput.addEventListener('input', function () {
        if (this.value > maxStock) {
            this.value = maxStock;
            alert("Số lượng tối đa còn lại là " + maxStock);
        }
        if (this.value < 1) {
            this.value = 1;
        }
    });
</script>
</body>
</html>
