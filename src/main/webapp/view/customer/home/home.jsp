<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/2/2025
  Time: 2:17 PM
  To change this template use File & Settings & File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TTT Bookstore | Trang chủ</title>
    <c:import url="../layout/library.jsp"/>
</head>
<body class="d-flex flex-column min-vh-100">

<c:import url="../layout/navbar.jsp" />

<!-- HERO BANNER -->
<section class="mt-5 pt-5">
    <div class="container mt-4">
        <div class="p-5 bg-danger text-white rounded-4 shadow-lg text-center">
            <h1 class="fw-bold">Chào mừng đến với TTT Bookstore</h1>
            <p class="lead">Khám phá hàng ngàn tựa sách hay, best-seller và nhiều ưu đãi hấp dẫn!</p>
            <a href="${pageContext.request.contextPath}/customer/products" class="btn btn-light btn-lg fw-bold"><i class="bi bi-arrow-right-circle"></i> Khám phá ngay</a>
        </div>
    </div>
</section>

<!-- SẢN PHẨM NỔI BẬT -->
<section class="container my-5" id="products">
    <h2 class="fw-bold mb-4 text-danger"><i class="bi bi-stars"></i> Sách nổi bật</h2>

    <!-- CARD SẢN PHẨM -->

    <div class="row g-4">

        <c:forEach items="${productList}" var="p">
            <div class="col-md-3">
                <div class="card h-100 shadow-sm product-card">

                    <img src="${p.imgURL}"
                         class="card-img-top"
                         alt="${p.title}"
                         onerror="this.src='https://via.placeholder.com/300x400'">

                    <div class="card-body">
                        <h5 class="card-title">${p.title}</h5>
                        <p class="text-muted mb-1">${p.authorName}</p>

                        <span class="badge bg-secondary">
                            ${p.categoryName}
                        </span>

                        <h5 class="fw-bold mt-2 text-danger">
                            <fmt:formatNumber value="${p.price}" type="number" pattern="#,##0"/>₫
                        </h5>
                    </div>

                    <div class="card-footer bg-white border-0">
                        <div class="d-grid gap-2">
                            <a href="${pageContext.request.contextPath}/customer/products?action=detail&id=${p.id}"
                               class="btn btn-outline-danger btn-sm">
                                <i class="bi bi-info-circle"></i> Xem chi tiết
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </c:forEach>

    </div>
</section>


<c:import url="../layout/footer.jsp" />
<script>
    function addToCartAjax(bookId, productName) {
        // Kiểm tra đăng nhập
        fetch('${pageContext.request.contextPath}/cart', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                action: 'add',
                bookId: bookId
            })
        })
        .then(response => {
            if (response.ok) {
                showNotification('Đã thêm ' + productName + ' vào giỏ hàng', 'success');
                // Reload mini cart
                loadMiniCart();
            } else if (response.status === 403) {
                showNotification('Vui lòng đăng nhập để thêm vào giỏ hàng', 'warning');
                setTimeout(() => {
                    window.location.href = '${pageContext.request.contextPath}/auth?action=login';
                }, 1500);
            } else {
                showNotification('Có lỗi xảy ra, vui lòng thử lại', 'danger');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showNotification('Có lỗi xảy ra', 'danger');
        });
    }

    // Hàm hiển thị thông báo
    function showNotification(message, type = 'success') {
        const alertDiv = document.createElement('div');
        alertDiv.className = `alert alert-${type} alert-dismissible fade show position-fixed`;
        alertDiv.style.cssText = 'top: 100px; right: 20px; z-index: 10000; min-width: 300px;';
        alertDiv.innerHTML = `
            <i class="bi bi-check-circle-fill"></i> ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;
        document.body.appendChild(alertDiv);
        
        // Tự động ẩn sau 3 giây
        setTimeout(() => {
            alertDiv.remove();
        }, 3000);
    }

    // Hàm load mini cart từ server
    function loadMiniCart() {
        fetch('${pageContext.request.contextPath}/customer/cart/mini')
            .then(response => response.text())
            .then(html => {
                document.getElementById('miniCartContent').innerHTML = html;
                updateCartCount();
            })
            .catch(error => console.error('Error loading mini cart:', error));
    }
</script>
</body>
</html>
