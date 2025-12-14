<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/9/2025
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
<style>
    /* RESET MARGIN/PADDING */
    body {
        margin: 0 !important;
        padding: 0 !important;
        padding-top: 56px !important;
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }
    html {
        height: 100%;
    }

    /* FOOTER FIXED */
    .footer-fixed {
        position: fixed;
        bottom: 0;
        left: 0;
        width: 100%;
        z-index: 1030;
    }

    /* CART + MINI CART */
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

    /* MINI CART */
    .mini-cart {
        position: absolute;
        right: 0;
        top: 48px;
        width: 320px;
        background: #fff;
        border: 1px solid #ddd;
        border-radius: 12px;
        box-shadow: 0 8px 25px rgba(0,0,0,0.2);
        padding: 15px;
        display: none;
        opacity: 0;
        transform: translateY(-10px);
        transition: all 0.25s ease-in-out;
        z-index: 9999;
    }

    /* ⭐ MINI-CART CHỈ HIỆN KHI HOVER */
    .cart-wrapper:hover .mini-cart,
    .mini-cart:hover {
        display: block !important;
        opacity: 1;
        transform: translateY(0);
    }

    /* MINI CART ITEMS */
    .mini-cart .mini-item {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
        transition: all 0.3s;
    }
    .mini-cart .mini-item:hover {
        background: #f1f1f1;
        border-radius: 5px;
    }
    .mini-cart img {
        width: 50px;
        height: 70px;
        object-fit: cover;
        border-radius: 3px;
        margin-right: 10px;
    }
    .mini-item-title { font-size: 0.9rem; font-weight: 500; }
    .mini-item-price { font-size: 0.85rem; color: #555; }
    .mini-delete { font-size: 1rem; color: red; cursor: pointer; }

    /* SCROLL ĐẸP */
    #mini-items {
        max-height: 250px;
        overflow-y: auto;
    }
    #mini-items::-webkit-scrollbar {
        width: 6px;
    }
    #mini-items::-webkit-scrollbar-thumb {
        background: #aaa;
        border-radius: 10px;
    }

    /* LOGO NEON */
    .logo-text {
        font-weight: bold;
        font-size: 1.3rem;
        color: #fff;
        text-shadow:
                0 0 5px #ff6a00,
                0 0 10px #ff6a00,
                0 0 20px #ff6a00,
                0 0 40px #ff6a00,
                0 0 60px #ee0979;
        animation: neonGlow 2s ease-in-out infinite alternate;
        transition: all 0.3s;
    }
    .logo-text:hover {
        transform: scale(1.15) rotate(-2deg);
        text-shadow:
                0 0 12px #ff6a00,
                0 0 20px #ff6a00,
                0 0 40px #ff6a00,
                0 0 70px #ee0979;
    }
    @keyframes neonGlow {
        0% { opacity: 1; }
        50% { opacity: .9; }
        100% { opacity: 1; }
    }

    /* RESPONSIVE */
    @media (max-width: 992px) {
        .mini-cart {
            right: -80px;
            width: 260px;
        }
        .footer-fixed {
            position: static !important;
        }
        body {
            padding-bottom: 0 !important;
        }
    }

</style>
