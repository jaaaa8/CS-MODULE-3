<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/2/2025
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:import url="${pageContext.request.contextPath}/view/customer/layout/library.jsp"/>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/view/customer/layout/navbar.jsp" />
<div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
    <div class="card p-4 shadow-sm" style="width: 400px; border-radius: 12px;">

        <h3 class="text-center mb-3 fw-bold text-danger">Đăng Nhập</h3>
        <p class="text-center text-secondary">Chào mừng bạn quay lại</p>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <!-- Email -->
            <div class="mb-3">
                <label class="form-label">Email hoặc Số điện thoại</label>
                <input type="text" class="form-control" name="username" required>
            </div>

            <!-- Password -->
            <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input type="password" class="form-control" name="password" required minlength="6">
            </div>

            <button class="btn btn-danger w-100 py-2 fw-bold">Đăng nhập</button>

            <div class="text-center mt-3">
                <a href="#" class="text-decoration-none text-danger">Quên mật khẩu?</a>
            </div>

            <div class="text-center mt-3">
                Bạn chưa có tài khoản?
                <a href="register.jsp" class="text-danger fw-bold text-decoration-none">Đăng ký</a>
            </div>

        </form>

    </div>
</div>
<c:import url="${pageContext.request.contextPath}/view/customer/layout/footer.jsp" />
</body>
</html>
