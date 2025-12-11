<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/2/2025
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-expand-md bg-danger navbar-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center" href="">
            <p class="mb-0 logo-text">TTT.COM</p>
        </a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto align-items-center">
                <form class="d-flex mx-auto" role="search" style="width:500px">
                    <input class="form-control me-2" type="search" placeholder="Tìm kiếm sách..." aria-label="Search">
                    <button class="btn btn-light" type="submit"><i class="bi bi-search"></i></button>
                </form>
                <li class="nav-item me-2">
                    <a class="btn text-white" href=""><i class="bi bi-bell-fill me-1"></i>Thông báo</a>
                </li>
                <li class="nav-item me-2 position-relative">
                    <div class="cart-icon btn text-white">
                        <i class="bi bi-cart-fill me-1"></i>Giỏ hàng
                        <span class="cart-count" id="cart-count-header">3</span>
                        <div class="mini-cart" id="mini-cart">
                            <div id="mini-items"></div>
                            <hr>
                            <div class="d-flex justify-content-between fw-bold">
                                <span>Tổng cộng:</span>
                                <span id="mini-total">$0.00</span>
                            </div>
                            <button class="btn btn-primary w-100 mt-2 py-1 btn-gradient">Thanh Toán</button>
                        </div>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" id="accountDropdown" role="button" data-bs-toggle="dropdown">
                        <i class="bi bi-person-circle me-1"></i>Tài khoản
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="accountDropdown">
                        <li><a class="dropdown-item" href="#">Đăng nhập</a></li>
                        <li><a class="dropdown-item" href="#">Đăng ký</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
