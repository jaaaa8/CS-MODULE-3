<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/2/2025
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    /* ---- CATEGORY MEGA MENU ---- */
    .category-wrapper {
        position: relative;
    }

    .mega-menu {
        position: absolute;
        top: 48px;
        left: 0;
        width: 750px;
        background: white;
        display: none;
        border-radius: 10px;
        z-index: 1000;
    }

    .category-wrapper:hover .mega-menu {
        display: block;
    }

    .mega-item {
        display: block;
        padding: 6px 0;
        text-decoration: none;
        color: #222;
    }
    .mega-item:hover {
        color: #d32f2f;
    }

    /* ---- SEARCH BOX ---- */
    .search-box input {
        width: 480px;
    }

    /* ---- LOGIN DROPDOWN ---- */
    .user-wrapper:hover .dropdown-login {
        display: block;
    }

    .dropdown-login {
        position: absolute;
        top: 40px;
        right: 0;
        background: white;
        width: 170px;
        border-radius: 10px;
        display: none;
        z-index: 1000;
    }
    .dropdown-login .dropdown-item {
        padding: 10px;
        color: #333;
    }
    .dropdown-login .dropdown-item:hover {
        background: #f1f1f1;
    }

    /* ---- GIỎ HÀNG ---- */
    .cart-count {
        position: absolute;
        top: -5px;
        right: -10px;
        background: #dc3545;
        color: white;
        font-size: 12px;
        padding: 1px 6px;
        border-radius: 50%;
        font-weight: bold;
    }

    /* MINI CART */
    .cart-wrapper:hover .mini-cart {
        display: block;
    }

    .mini-cart {
        position: absolute;
        top: 40px;
        right: 0;
        width: 330px;
        background: white;
        display: none;
        border-radius: 12px;
        z-index: 1000;
    }

    /* Scroll đẹp */
    #mini-items::-webkit-scrollbar {
        width: 5px;
    }
    #mini-items::-webkit-scrollbar-thumb {
        background: #bbb;
        border-radius: 10px;
    }

    /* Hover mở dropdown */
    .nav-item.dropdown:hover .dropdown-menu {
        display: block;
        opacity: 1;
        visibility: visible;
    }

    /* Smooth animation */
    .dropdown-menu {
        margin-top: 0;
        opacity: 0;
        visibility: hidden;
        transition: 0.2s ease-in-out;
    }

    .nav-item.dropdown:hover > .nav-link {
        color: #ffe082 !important; /* Đổi màu khi hover cho đẹp */
    }

</style>
<nav class="navbar navbar-expand-md navbar-dark bg-danger fixed-top shadow-sm">
    <div class="container-fluid">

        <!-- LOGO -->
        <a class="navbar-brand d-flex align-items-center" href="../home/home.jsp">
            <p class="mb-0 logo-text fw-bold fs-4">TTT.COM</p>
        </a>

        <!-- MOBILE BUTTON -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- NAV ITEMS -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <!-- LEFT MENU -->
            <ul class="navbar-nav me-3">
                <li class="nav-item dropdown category-wrapper">
                    <a class="nav-link fw-bold text-white d-flex align-items-center" href="#" >
                        <i class="bi bi-list fs-4 me-2"></i> DANH MỤC
                    </a>

                    <!-- MEGA MENU -->
                    <div class="mega-menu p-3 shadow-lg">
                        <div class="row">

                            <div class="col-4">
                                <h6 class="fw-bold mb-2">Sách Trong Nước</h6>
                                <a href="#" class="mega-item">Văn học</a>
                                <a href="#" class="mega-item">Kinh tế</a>
                                <a href="#" class="mega-item">Tâm lý</a>
                                <a href="#" class="mega-item">Thiếu nhi</a>
                                <a href="#" class="mega-item">Học ngoại ngữ</a>
                            </div>

                            <div class="col-4">
                                <h6 class="fw-bold mb-2">Sách Ngoại Văn</h6>
                                <a href="#" class="mega-item">Fiction</a>
                                <a href="#" class="mega-item">Business</a>
                                <a href="#" class="mega-item">Self-help</a>
                                <a href="#" class="mega-item">Children Books</a>
                                <a href="#" class="mega-item">Lifestyle</a>
                            </div>

                            <div class="col-4">
                                <h6 class="fw-bold mb-2">Văn Phòng Phẩm</h6>
                                <a href="#" class="mega-item">Bút - Viết</a>
                                <a href="#" class="mega-item">Tập - Vở</a>
                                <a href="#" class="mega-item">Dụng cụ học sinh</a>
                                <a href="#" class="mega-item">Sổ tay</a>
                                <a href="#" class="mega-item">Quà tặng</a>
                            </div>

                        </div>
                    </div>
                </li>

            </ul>

            <!-- SEARCH -->
            <form class="d-flex mx-auto" role="search" style="width:500px; max-width: 100%;">
                <input class="form-control me-2 shadow-sm" type="search" placeholder="Tìm kiếm sách...">
                <button class="btn btn-light shadow-sm" type="submit"><i class="bi bi-search"></i></button>
            </form>

            <ul class="navbar-nav ms-auto align-items-center">

                <!-- NOTIFICATION -->
                <li class="nav-item me-2">
                    <a class="btn text-white" href="#">
                        <i class="bi bi-bell-fill me-1"></i>Thông báo
                    </a>
                </li>

                <!-- CART -->
                <li class="nav-item me-3 position-relative cart-wrapper">

                    <!-- CLICK → chuyển trang -->
                    <a class="btn text-white position-relative cart-link" href="../cart/cart.jsp">
                        <i class="bi bi-cart-fill me-1"></i>Giỏ hàng
                        <span class="cart-count" id="cart-count-header">3</span>
                    </a>

                    <!-- MINI CART -->
                    <div class="mini-cart shadow-lg rounded" id="mini-cart">
                        <h6 class="fw-bold p-2 border-bottom">Sản phẩm mới thêm</h6>

                        <div id="mini-items" class="p-2" style="max-height: 250px; overflow-y: auto;">
                            <!-- Dữ liệu sản phẩm thêm bằng JS hoặc JSTL -->
                        </div>

                        <div class="border-top p-2 d-flex justify-content-between fw-bold">
                            <span>Tổng cộng:</span>
                            <span id="mini-total" class="text-danger">0₫</span>
                        </div>

                        <a href="../payment/payment.jsp" class="btn btn-danger w-100 mt-2">Thanh Toán</a>

                    </div>
                </li>

                <!-- ACCOUNT -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" data-bs-toggle="dropdown">
                        <i class="bi bi-person-circle me-1"></i>Tài khoản
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/view/login/login.jsp">Đăng nhập</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/view/login/register.jsp">Đăng ký</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>
