<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/25/2025
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>403 - Forbidden</title>

  <!-- Bootstrap CSS CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet">

  <!-- Bootstrap Icons CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
        rel="stylesheet">

  <style>
    body {
      background: linear-gradient(135deg, #f8d7da, #fff);
    }
    .error-card {
      border-radius: 16px;
      box-shadow: 0 10px 30px rgba(0,0,0,0.15);
    }
    .error-icon {
      font-size: 80px;
      color: #dc3545;
    }
  </style>
</head>
<body>

<div class="container d-flex justify-content-center align-items-center"
     style="min-height: 100vh;">
  <div class="card error-card p-5 text-center" style="max-width: 520px;">

    <div class="mb-3">
      <i class="bi bi-shield-lock-fill error-icon"></i>
    </div>

    <h1 class="fw-bold text-danger">403</h1>
    <h4 class="mb-3">Truy cập bị từ chối</h4>

    <p class="text-muted">
      Bạn không có quyền truy cập vào trang này.<br>
      Vui lòng đăng nhập bằng tài khoản phù hợp.
    </p>

    <div class="d-flex justify-content-center gap-2 mt-4">
      <a href="${pageContext.request.contextPath}/home"
         class="btn btn-outline-secondary">
        <i class="bi bi-house-door"></i> Trang chủ
      </a>

      <a href="${pageContext.request.contextPath}/auth?action=login"
         class="btn btn-danger">
        <i class="bi bi-box-arrow-in-right"></i> Đăng nhập
      </a>
    </div>
  </div>
</div>

<!-- Bootstrap JS CDN -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


