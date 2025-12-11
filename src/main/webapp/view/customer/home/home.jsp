<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/2/2025
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <a href="#products" class="btn btn-light btn-lg fw-bold"><i class="bi bi-arrow-right-circle"></i> Khám phá ngay</a>
        </div>
    </div>
</section>

<!-- SẢN PHẨM NỔI BẬT -->
<section class="container my-5" id="products">
    <h2 class="fw-bold mb-4 text-danger"><i class="bi bi-stars"></i> Sách nổi bật</h2>

    <div class="row g-4">

        <!-- CARD SẢN PHẨM -->
        <div class="col-md-3">
            <div class="card h-100 shadow-sm product-card">
                <img src="https://m.media-amazon.com/images/I/81cL2H23nVL._AC_UF1000,1000_QL80_.jpg"
                     class="card-img-top" alt="book">

                <div class="card-body">
                    <h5 class="card-title">Iron Flame</h5>
                    <p class="text-muted mb-1">Rebecca Yarros</p>
                    <span class="badge bg-secondary">Bìa cứng</span>
                    <h5 class="fw-bold mt-2 text-danger">$18.99</h5>
                </div>

                <div class="card-footer bg-white border-0">
                    <a href="../product/detail.jsp" class="btn btn-outline-danger w-100">Xem chi tiết</a>
                </div>
            </div>
        </div>

        <!-- CARD 2 -->
        <div class="col-md-3">
            <div class="card h-100 shadow-sm product-card">
                <img src="https://m.media-amazon.com/images/S/compressed.photo.goodreads.com/books/1733493019i/127305853.jpg"
                     class="card-img-top" alt="book">
                <div class="card-body">
                    <h5 class="card-title">The Women</h5>
                    <p class="text-muted mb-1">Kristin Hannah</p>
                    <span class="badge bg-primary">Bìa cứng</span>
                    <h5 class="fw-bold mt-2 text-danger">$21.00</h5>
                </div>
                <div class="card-footer bg-white border-0">
                    <a href="../product/detail.jsp" class="btn btn-outline-danger w-100">Xem chi tiết</a>
                </div>
            </div>
        </div>

        <!-- CARD 3 -->
        <div class="col-md-3">
            <div class="card h-100 shadow-sm product-card">
                <img src="https://m.media-amazon.com/images/I/71aFt4+OTOL.jpg"
                     class="card-img-top" alt="book">
                <div class="card-body">
                    <h5 class="card-title">The Alchemist</h5>
                    <p class="text-muted mb-1">Paulo Coelho</p>
                    <span class="badge bg-success">Bìa mềm</span>
                    <h5 class="fw-bold mt-2 text-danger">$14.50</h5>
                </div>
                <div class="card-footer">
                    <a href="../product/detail.jsp" class="btn btn-outline-danger w-100">Xem chi tiết</a>
                </div>
            </div>
        </div>

        <!-- CARD 4 -->
        <div class="col-md-3">
            <div class="card h-100 shadow-sm product-card">
                <img src="https://m.media-amazon.com/images/I/91uwocAMtSL.jpg"
                     class="card-img-top" alt="book">
                <div class="card-body">
                    <h5 class="card-title">Harry Potter</h5>
                    <p class="text-muted mb-1">J.K. Rowling</p>
                    <span class="badge bg-warning">Bộ sách</span>
                    <h5 class="fw-bold mt-2 text-danger">$65.00</h5>
                </div>
                <div class="card-footer">
                    <a href="../product/detail.jsp" class="btn btn-outline-danger w-100">Xem chi tiết</a>
                </div>
            </div>
        </div>

    </div>
</section>
<c:import url="../layout/footer.jsp" />
</body>
</html>
