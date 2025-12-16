<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/2/2025
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
            <a href="${pageContext.request.contextPath}/products" class="btn btn-light btn-lg fw-bold"><i class="bi bi-arrow-right-circle"></i> Khám phá ngay</a>
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
                            $${p.price}
                        </h5>
                    </div>

                    <div class="card-footer bg-white border-0">
                        <a href="/products?action=detail&id=${p.id}"
                           class="btn btn-outline-danger w-100">
                            Xem chi tiết
                        </a>
                    </div>

                </div>
            </div>
        </c:forEach>

    </div>
</section>


<c:import url="../layout/footer.jsp" />
</body>
</html>
