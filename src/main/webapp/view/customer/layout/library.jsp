<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- BOOTSTRAP -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

<style>
    /* ================= RESET + LAYOUT ================= */
    html, body {
        height: 100%;
    }

    body {
        margin: 0;
        padding-top: 80px; /* phù hợp navbar cao */
        display: flex;
        flex-direction: column;
        background: #f8f9fa;
    }

    /* Content grow để footer luôn nằm cuối */
    main, .flex-grow-1 {
        flex: 1;
    }

    /* ================= LOGO ================= */
    .logo-text {
        font-weight: bold;
        font-size: 1.4rem;
        color: #fff;
        text-shadow:
                0 0 5px #ff6a00,
                0 0 10px #ff6a00,
                0 0 20px #ff6a00,
                0 0 40px #ee0979;
        transition: .3s;
    }
    .logo-text:hover {
        transform: scale(1.1);
    }

    /* ================= CART ================= */
    .cart-wrapper {
        position: relative;
    }

    .cart-count {
        position: absolute;
        top: -5px;
        right: -10px;
        background: #ffc107;
        color: #000;
        font-size: 12px;
        font-weight: bold;
        border-radius: 50%;
        padding: 2px 6px;
    }

    /* ================= MINI CART ================= */
    .mini-cart {
        position: absolute;
        right: 0;
        top: 48px;
        width: 320px;
        background: #fff;
        border-radius: 12px;
        box-shadow: 0 8px 25px rgba(0,0,0,.2);
        padding: 12px;
        display: none;
        z-index: 1050;
    }

    /* Desktop hover */
    @media (min-width: 992px) {
        .cart-wrapper:hover .mini-cart {
            display: block;
        }
    }

    /* Mini cart items */
    .mini-item {
        display: flex;
        gap: 10px;
        margin-bottom: 10px;
    }
    .mini-item img {
        width: 45px;
        height: 65px;
        object-fit: cover;
    }
    .mini-item-title {
        font-size: .9rem;
        font-weight: 500;
    }
    .mini-item-price {
        font-size: .85rem;
        color: #666;
    }

    /* Scroll */
    #mini-items {
        max-height: 240px;
        overflow-y: auto;
    }
    #mini-items::-webkit-scrollbar {
        width: 5px;
    }
    #mini-items::-webkit-scrollbar-thumb {
        background: #aaa;
        border-radius: 10px;
    }

    /* ================= FOOTER ================= */
    footer {
        background: #212529;
        color: #ccc;
        padding: 30px 0;
    }

    /* ================= RESPONSIVE ================= */
    @media (max-width: 991px) {
        body {
            padding-top: 70px;
        }
        .mini-cart {
            width: 260px;
            right: -40px;
        }
    }
</style>

<!-- BOOTSTRAP JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
