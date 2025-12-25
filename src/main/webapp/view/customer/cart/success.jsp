<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/16/2025
  Time: 7:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TTT Bookstore | Đặt hàng thành công</title>
    <c:import url="../layout/library.jsp"/>
    <style>
        .success-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            min-height: 500px;
        }

        .success-icon {
            font-size: 80px;
            color: #28a745;
            margin-bottom: 20px;
            animation: bounce 0.6s ease-in-out;
        }

        @keyframes bounce {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-20px); }
        }

        .success-card {
            background: white;
            border-radius: 12px;
            padding: 40px;
            text-align: center;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            max-width: 600px;
        }

        .success-title {
            color: #28a745;
            font-size: 32px;
            font-weight: bold;
            margin-bottom: 15px;
        }

        .success-subtitle {
            color: #666;
            font-size: 18px;
            margin-bottom: 10px;
        }

        .success-message {
            color: #999;
            font-size: 14px;
            margin-bottom: 30px;
            line-height: 1.6;
        }

        .order-details {
            background: #f8f9fa;
            border-left: 4px solid #28a745;
            padding: 20px;
            margin: 25px 0;
            text-align: left;
            border-radius: 6px;
        }

        .detail-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 12px;
            font-size: 14px;
        }

        .detail-label {
            font-weight: 600;
            color: #333;
        }

        .detail-value {
            color: #666;
        }

        .button-group {
            display: flex;
            gap: 12px;
            justify-content: center;
            margin-top: 30px;
            flex-wrap: wrap;
        }

        .btn-shop {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border: none;
            color: white;
            padding: 12px 35px;
            border-radius: 6px;
            font-weight: 600;
            text-decoration: none;
            display: inline-block;
            transition: transform 0.3s ease;
        }

        .btn-shop:hover {
            transform: translateY(-2px);
            color: white;
        }

        .btn-account {
            background: #dc3545;
            border: none;
            color: white;
            padding: 12px 35px;
            border-radius: 6px;
            font-weight: 600;
            text-decoration: none;
            display: inline-block;
            transition: transform 0.3s ease;
        }

        .btn-account:hover {
            transform: translateY(-2px);
            color: white;
        }

        .contact-info {
            background: #e7f3ff;
            border: 1px solid #b3d9ff;
            border-radius: 6px;
            padding: 15px;
            margin-top: 20px;
            font-size: 14px;
            color: #0066cc;
        }

        .contact-info i {
            margin-right: 8px;
        }

        @media (max-width: 768px) {
            .success-card {
                padding: 25px;
            }

            .success-title {
                font-size: 24px;
            }

            .button-group {
                flex-direction: column;
            }

            .btn-shop, .btn-account {
                width: 100%;
            }
        }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">

<c:import url="../layout/navbar.jsp" />

<div class="flex-grow-1">
    <div class="container success-container">
        <div class="success-card">
            <!-- Icon -->
            <div class="success-icon">
                <i class="bi bi-check-circle-fill"></i>
            </div>

            <!-- Title -->
            <h1 class="success-title">
                <i class="bi bi-bag-check"></i> Đặt hàng thành công!
            </h1>

            <!-- Subtitle -->
            <p class="success-subtitle">
                Cảm ơn bạn đã mua sắm tại TTT Bookstore
            </p>

            <!-- Message -->
            <p class="success-message">
                Đơn hàng của bạn đã được xác nhận và sẽ được xử lý trong vòng 24 giờ. <br>
                Bạn sẽ nhận được email xác nhận với số theo dõi đơn hàng.
            </p>

            <!-- Order Details -->
            <div class="order-details">
                <div class="detail-row">
                    <span class="detail-label">
                        <i class="bi bi-bag-fill text-success"></i> Mã đơn hàng:
                    </span>
                    <span class="detail-value">#ORD-<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyyMMddHHmmss"/></span>
                </div>
                <div class="detail-row">
                    <span class="detail-label">
                        <i class="bi bi-calendar text-success"></i> Ngày đặt:
                    </span>
                    <span class="detail-value">
                        <fmt:formatDate value="<%= new java.util.Date() %>" pattern="dd/MM/yyyy HH:mm"/>
                    </span>
                </div>
                <div class="detail-row">
                    <span class="detail-label">
                        <i class="bi bi-truck text-success"></i> Phí vận chuyển:
                    </span>
                    <span class="detail-value">50.000₫</span>
                </div>
                <div class="detail-row">
                    <span class="detail-label">
                        <i class="bi bi-hourglass-split text-success"></i> Dự kiến giao:
                    </span>
                    <span class="detail-value">3-5 ngày làm việc</span>
                </div>
            </div>

            <!-- Contact Info -->
            <div class="contact-info">
                <i class="bi bi-info-circle"></i>
                Nếu bạn có bất kỳ câu hỏi nào, vui lòng liên hệ <strong>support@tttbookstore.com</strong>
            </div>

            <!-- Buttons -->
            <div class="button-group">
                <a href="${pageContext.request.contextPath}/products" class="btn-shop">
                    <i class="bi bi-arrow-left"></i> Tiếp tục mua sắm
                </a>
                <a href="${pageContext.request.contextPath}/customer/profile" class="btn-account">
                    <i class="bi bi-person"></i> Xem hồ sơ
                </a>
            </div>
        </div>
    </div>
</div>

<c:import url="../layout/footer.jsp" />

</body>
</html>
