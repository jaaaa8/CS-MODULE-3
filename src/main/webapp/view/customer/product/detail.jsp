<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

        /* Quantity Control */
        .quantity-control {
            display: flex;
            align-items: center;
            gap: 8px;
            width: fit-content;
            margin: 15px 0;
        }

        .qty-btn {
            background: #dc3545;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.2s;
        }

        .qty-btn:hover {
            background: #c82333;
        }

        .qty-input {
            width: 60px;
            padding: 6px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            text-align: center;
            font-weight: bold;
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

                <!-- THUMB -->
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
                <fmt:formatNumber value="${product.price}" type="number" pattern="#,##0"/>₫
            </h4>

            <!-- DESCRIPTION -->
            <p class="mt-3">
                <b>Mô tả:</b><br>
                ${product.description}
            </p>

            <!-- QUANTITY & ADD TO CART -->
            <%-- Hiển thị thông báo lỗi nếu có --%>
            <c:if test="${param.error == 'stock'}">
                <div class="alert alert-danger" role="alert">
                    Số lượng trong kho không đủ để thực hiện yêu cầu!
                </div>
            </c:if>

            <h4 class="mb-3">Số lượng tồn kho: <span class="text-muted">${product.stock}</span></h4>

            <form action="${pageContext.request.contextPath}/customer/cart" method="post">
                <input type="hidden" name="action" value="add">
                <%-- Lưu ý: Kiểm tra biến là 'product.id' hay 'book.id' tuỳ thuộc vào ProductController của bạn --%>
                <input type="hidden" name="bookId" value="${product.id}">

                <div class="d-flex align-items-center mb-3">
                    <label for="quantity" class="me-3 fw-bold">Số lượng:</label>

                    <%-- Input nhập số lượng --%>
                    <input type="number"
                           id="quantity"
                           name="quantity"
                           class="form-control text-center me-3"
                           value="1"
                           min="1"
                           max="${product.stock}"
                           style="width: 80px;"
                           onchange="checkStock(this, ${product.stock})">

                    <%-- Nút thêm vào giỏ --%>
                    <button type="submit" class="btn btn-primary shadow-0"
                    ${product.stock <= 0 ? 'disabled' : ''}>
                        <i class="me-1 fa fa-shopping-basket"></i>
                        ${product.stock > 0 ? 'Thêm vào giỏ' : 'Hết hàng'}
                    </button>
                </div>
            </form>

            <!-- EXTRA INFO -->
            <div class="mt-4">
                <h6 class="fw-bold">Thông tin chi tiết:</h6>
                <ul class="mb-0">
                    <li>Tác giả: ${product.authorName}</li>
                    <li>Danh mục: ${product.categoryName}</li>
                    <li>Nhà xuất bản: ${product.publisherName}</li>
                    <li>Số lượng còn: <strong>${product.stock}</strong> cuốn</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<c:import url="../layout/footer.jsp" />

<script>
    const maxStock = ${product.stock};
    const qtyInput = document.getElementById('quantityInput');

    function increaseQty() {
        const current = parseInt(qtyInput.value);
        if (current < maxStock) {
            qtyInput.value = current + 1;
        } else {
            alert('Số lượng không thể vượt quá tồn kho (' + maxStock + ' cuốn)');
        }
    }

    function decreaseQty() {
        const current = parseInt(qtyInput.value);
        if (current > 1) {
            qtyInput.value = current - 1;
        } else {
            alert('Số lượng tối thiểu là 1');
        }
    }

        // Script nhỏ để ngăn người dùng nhập quá số lượng tồn kho bằng bàn phím
        function checkStock(input, maxStock) {
        if (parseInt(input.value) > maxStock) {
        alert("Số lượng mua không được vượt quá tồn kho (" + maxStock + ")");
        input.value = maxStock;
    }
        if (parseInt(input.value) < 1) {
        input.value = 1;
    }
    }
</script>

</body>
</html>