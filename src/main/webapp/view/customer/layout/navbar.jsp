<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        box-shadow: 0 4px 15px rgba(0,0,0,0.2);
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
        display: none;
    }

    .cart-count.active {
        display: block;
    }

    .cart-wrapper {
        position: relative;
    }

    .mini-cart {
        position: absolute;
        top: 42px;
        right: 0;
        width: 380px;
        background: white;
        display: none;
        border-radius: 12px;
        z-index: 1000;
        box-shadow: 0 4px 15px rgba(0,0,0,0.2);
        max-height: 500px;
        overflow-y: auto;
    }

    .mini-cart.show {
        display: block;
    }

    .cart-wrapper:hover .mini-cart {
        display: block;
    }

    .mini-cart-header {
        padding: 12px 15px;
        border-bottom: 1px solid #eee;
        font-weight: 600;
        color: #333;
    }

    .mini-cart-content {
        padding: 0;
        max-height: 350px;
        overflow-y: auto;
    }

    .mini-cart-item {
        padding: 12px 15px;
        border-bottom: 1px solid #f0f0f0;
        display: flex;
        gap: 12px;
        align-items: flex-start;
        transition: background 0.2s ease;
    }

    .mini-cart-item:hover {
        background: #f9f9f9;
    }

    .mini-cart-item-title {
        font-weight: 600;
        font-size: 13px;
        color: #333;
        flex: 1;
        line-height: 1.4;
    }

    .mini-cart-item-price {
        font-size: 12px;
        color: #666;
        margin-top: 4px;
    }

    .mini-cart-item-qty {
        background: #f0f0f0;
        padding: 2px 8px;
        border-radius: 4px;
        font-size: 12px;
        font-weight: 600;
        color: #333;
        white-space: nowrap;
    }

    .mini-cart-footer {
        padding: 12px 15px;
        border-top: 1px solid #eee;
        display: flex;
        gap: 8px;
    }

    .mini-cart-empty {
        padding: 30px 15px;
        text-align: center;
        color: #999;
        font-size: 13px;
    }

    .mini-cart-total {
        padding: 12px 15px;
        border-top: 1px solid #eee;
        border-bottom: 1px solid #eee;
        display: flex;
        justify-content: space-between;
        font-weight: 600;
        color: #333;
        background: #f9f9f9;
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
            border-radius: 0;
        }

        .cart-wrapper:hover .mini-cart {
            display: none;
        }

        .mini-cart-item {
            padding: 10px;
        }
    }
</style>

<nav class="navbar navbar-expand-md navbar-dark bg-danger fixed-top shadow-sm">
    <div class="container-fluid">

        <!-- LOGO -->
        <a class="navbar-brand fw-bold fs-4"
           href="${pageContext.request.contextPath}/home">
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
                  action="${pageContext.request.contextPath}/customer/products"
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
                       href="${pageContext.request.contextPath}/customer/cart">
                        <i class="bi bi-cart-fill"></i> Giỏ hàng
                        <span class="cart-count" id="cartCount">0</span>
                    </a>

                    <div class="mini-cart" id="miniCart">
                        <div class="mini-cart-header">
                            <i class="bi bi-bag-fill"></i> Sản phẩm mới thêm
                        </div>
                        
                        <div class="mini-cart-content" id="miniCartContent">
                            <div class="mini-cart-empty">Đang tải...</div>
                        </div>

                        <div class="mini-cart-total" id="miniCartTotal" style="display: none;">
                            <span>Tổng tiền:</span>
                            <span id="miniCartTotalPrice">0₫</span>
                        </div>

                        <div class="mini-cart-footer">
                            <a class="btn btn-sm btn-outline-secondary flex-grow-1"
                               href="${pageContext.request.contextPath}/customer/cart">
                                <i class="bi bi-cart"></i> Xem giỏ hàng
                            </a>
                            <a class="btn btn-sm btn-danger flex-grow-1"
                               href="${pageContext.request.contextPath}/customer/payments">
                                <i class="bi bi-credit-card"></i> Thanh toán
                            </a>
                        </div>
                    </div>
                </li>

                <!-- ACCOUNT -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white"
                       data-bs-toggle="dropdown">
                        <i class="bi bi-person-circle"></i> Tài khoản
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <c:if test="${account != null}">
                            <li>
                                <p class="dropdown-item">
                                    Xin chào, <b>${account.username}</b>
                                </p>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <form action="${pageContext.request.contextPath}/auth" method="post">
                                    <input type="hidden" name="action" value="logout">
                                    <button type="submit" class="dropdown-item">
                                        <i class="bi bi-box-arrow-right"></i> Đăng xuất
                                    </button>
                                </form>
                            </li>
                        </c:if>
                        <c:if test="${account == null}">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/auth?action=login">
                                <i class="bi bi-box-arrow-in-right"></i> Đăng nhập</a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/auth?action=register">
                                <i class="bi bi-person-plus"></i> Đăng ký</a></li>
                        </c:if>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>

<script>
    // Lấy giỏ hàng từ localStorage
    function updateMiniCart() {
        const cart = JSON.parse(localStorage.getItem('cart') || '[]');
        const miniCartContent = document.getElementById('miniCartContent');
        const miniCartTotal = document.getElementById('miniCartTotal');
        const cartCount = document.getElementById('cartCount');
        
        if (cart.length === 0) {
            miniCartContent.innerHTML = '<div class="mini-cart-empty">Chưa có sản phẩm</div>';
            miniCartTotal.style.display = 'none';
            cartCount.textContent = '0';
            cartCount.classList.remove('active');
            return;
        }

        cartCount.textContent = cart.length;
        cartCount.classList.add('active');

        let html = '';
        let totalPrice = 0;

        cart.forEach((item, index) => {
            const itemTotal = item.price * item.quantity;
            totalPrice += itemTotal;

            // Format giá theo VNĐ
            const formattedPrice = new Intl.NumberFormat('vi-VN').format(Math.round(item.price));
            const formattedTotal = new Intl.NumberFormat('vi-VN').format(Math.round(itemTotal));

            html += `
                <div class="mini-cart-item">
                    <div style="flex: 1;">
                        <div class="mini-cart-item-title">${item.name}</div>
                        <div class="mini-cart-item-price">
                            ${formattedPrice}₫ × ${item.quantity} = ${formattedTotal}₫
                        </div>
                    </div>
                    <div class="mini-cart-item-qty">${item.quantity}</div>
                </div>
            `;
        });

        miniCartContent.innerHTML = html;
        
        if (cart.length > 0) {
            miniCartTotal.style.display = 'flex';
            const formattedTotal = new Intl.NumberFormat('vi-VN').format(Math.round(totalPrice));
            document.getElementById('miniCartTotalPrice').textContent = formattedTotal + '₫';
        }
    }

    // Cập nhật mini cart khi trang load
    document.addEventListener('DOMContentLoaded', updateMiniCart);

    // Lắng nghe sự kiện thêm vào giỏ hàng
    document.addEventListener('addToCart', updateMiniCart);
</script>

<script>
    // Hàm load mini cart từ server
    function loadMiniCart() {
        fetch('${pageContext.request.contextPath}/customer/cart?action=miniCart')
            .then(response => response.json())
            .then(data => {
                updateMiniCartUI(data);
            })
            .catch(error => {
                console.error('Error loading mini cart:', error);
                document.getElementById('miniCartContent').innerHTML = 
                    '<div class="mini-cart-empty">Lỗi tải dữ liệu</div>';
            });
    }

    function updateMiniCartUI(data) {
        const miniCartContent = document.getElementById('miniCartContent');
        const miniCartTotal = document.getElementById('miniCartTotal');
        const cartCount = document.getElementById('cartCount');

        if (!data.items || data.items.length === 0) {
            miniCartContent.innerHTML = '<div class="mini-cart-empty">Giỏ hàng trống</div>';
            miniCartTotal.style.display = 'none';
            cartCount.textContent = '0';
            cartCount.classList.remove('active');
            return;
        }

        cartCount.textContent = data.items.length;
        cartCount.classList.add('active');

        let html = '';

        data.items.forEach((item) => {
            const formattedPrice = new Intl.NumberFormat('vi-VN').format(Math.round(item.price));
            const formattedTotal = new Intl.NumberFormat('vi-VN').format(Math.round(item.subtotal));

            html += `
                <div class="mini-cart-item">
                    <div style="flex: 1;">
                        <div class="mini-cart-item-title">${item.bookName}</div>
                        <div class="mini-cart-item-price">
                            ${formattedPrice}₫ × ${item.quantity} = ${formattedTotal}₫
                        </div>
                    </div>
                    <div class="mini-cart-item-qty">${item.quantity}</div>
                </div>
            `;
        });

        miniCartContent.innerHTML = html;

        if (data.items.length > 0) {
            miniCartTotal.style.display = 'flex';
            const formattedTotal = new Intl.NumberFormat('vi-VN').format(Math.round(data.totalPrice));
            document.getElementById('miniCartTotalPrice').textContent = formattedTotal + '₫';
        }
    }

    // Load mini cart khi trang load
    document.addEventListener('DOMContentLoaded', loadMiniCart);
</script>
