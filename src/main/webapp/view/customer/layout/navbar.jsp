<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    /* ===================== */
    /* NAVBAR CUSTOM STYLE */
    /* ===================== */

    .category-wrapper {
        position: relative;
    }

    /* ---------- MEGA MENU ---------- */
    .mega-menu {
        position: absolute;
        top: 48px;
        left: 0;
        width: 720px;
        background: #fff;
        display: none;
        border-radius: 12px;
        z-index: 1000;
    }

    .category-wrapper:hover .mega-menu {
        display: block;
    }

    .mega-item {
        display: block;
        padding: 6px 0;
        text-decoration: none;
        color: #333;
        font-size: 14px;
    }

    .mega-item:hover {
        color: #dc3545;
    }

    /* ---------- CART ---------- */
    .cart-count {
        position: absolute;
        top: -6px;
        right: -10px;
        background: #dc3545;
        color: white;
        font-size: 12px;
        padding: 2px 6px;
        border-radius: 50%;
        font-weight: bold;
    }

    .cart-wrapper {
        position: relative;
    }

    .mini-cart {
        position: absolute;
        top: 42px;
        right: 0;
        width: 330px;
        background: white;
        display: none;
        border-radius: 12px;
        z-index: 1000;
    }

    .cart-wrapper:hover .mini-cart {
        display: block;
    }

    /* ---------- DROPDOWN ---------- */
    .nav-item.dropdown:hover .dropdown-menu {
        display: block;
    }

    /* ===================== */
    /* RESPONSIVE FIX */
    /* ===================== */
    @media (max-width: 768px) {

        .mega-menu {
            position: static;
            width: 100%;
            box-shadow: none;
            border-radius: 0;
        }

        .category-wrapper:hover .mega-menu {
            display: none;
        }

        .mini-cart {
            position: static;
            width: 100%;
            box-shadow: none;
        }

        .cart-wrapper:hover .mini-cart {
            display: none;
        }
    }
</style>

<nav class="navbar navbar-expand-md navbar-dark bg-danger fixed-top shadow-sm">
    <div class="container-fluid">

        <!-- LOGO -->
        <a class="navbar-brand fw-bold fs-4"
           href="${pageContext.request.contextPath}/products">
            TTT.COM
        </a>

        <!-- TOGGLE MOBILE -->
        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- NAVBAR CONTENT -->
        <div class="collapse navbar-collapse" id="navbarNav">

            <!-- LEFT -->
            <ul class="navbar-nav me-3">

                <!-- CATEGORY -->
                <li class="nav-item dropdown category-wrapper">
                    <a class="nav-link fw-bold text-white d-flex align-items-center"
                       href="#">
                        <i class="bi bi-list fs-5 me-2"></i> DANH MỤC
                    </a>

                    <div class="mega-menu p-3 shadow-lg">
                        <div class="row">
                            <div class="col-md-4">
                                <h6 class="fw-bold">Sách Trong Nước</h6>
                                <a class="mega-item" href="#">Văn học</a>
                                <a class="mega-item" href="#">Kinh tế</a>
                                <a class="mega-item" href="#">Tâm lý</a>
                            </div>
                            <div class="col-md-4">
                                <h6 class="fw-bold">Sách Ngoại Văn</h6>
                                <a class="mega-item" href="#">Fiction</a>
                                <a class="mega-item" href="#">Business</a>
                                <a class="mega-item" href="#">Self-help</a>
                            </div>
                            <div class="col-md-4">
                                <h6 class="fw-bold">Văn Phòng Phẩm</h6>
                                <a class="mega-item" href="#">Bút</a>
                                <a class="mega-item" href="#">Vở</a>
                                <a class="mega-item" href="#">Quà tặng</a>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>

            <!-- SEARCH -->
            <form class="d-flex flex-grow-1 mx-md-4 my-2 my-md-0"
                  role="search"
                  action="${pageContext.request.contextPath}/products"
                  method="get">

                <input type="hidden" name="action" value="search">

                <input class="form-control shadow-sm"
                       type="search"
                       name="keyword"
                       value="${keyword}"
                       placeholder="Tìm theo tên sách hoặc tác giả...">

                <button class="btn btn-light shadow-sm ms-2" type="submit">
                    <i class="bi bi-search"></i>
                </button>
            </form>

            <!-- RIGHT -->
            <ul class="navbar-nav ms-auto align-items-center">

                <!-- CART -->
                <li class="nav-item cart-wrapper me-3">
                    <a class="btn text-white position-relative"
                       href="${pageContext.request.contextPath}/view/customer/cart/cart.jsp">
                        <i class="bi bi-cart-fill"></i> Giỏ hàng
                        <span class="cart-count">0</span>
                    </a>

                    <div class="mini-cart shadow p-3">
                        <p class="fw-bold mb-2">Sản phẩm mới thêm</p>
                        <div class="text-muted small">Chưa có sản phẩm</div>
                        <a class="btn btn-danger w-100 mt-2"
                           href="${pageContext.request.contextPath}/view/customer/payment/payment.jsp">
                            Thanh toán
                        </a>
                    </div>
                </li>

                <!-- ACCOUNT -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white"
                       data-bs-toggle="dropdown">
                        <i class="bi bi-person-circle"></i> Tài khoản
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/view/login/login.jsp">
                            Đăng nhập</a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/view/login/register.jsp">
                            Đăng ký</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>
