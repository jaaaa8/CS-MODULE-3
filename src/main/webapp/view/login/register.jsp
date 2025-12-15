
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
    <div class="card p-4 shadow-sm" style="width: 450px; border-radius: 12px;">

        <h3 class="text-center mb-3 fw-bold text-danger">Tạo Tài Khoản</h3>
        <p class="text-center text-secondary">Điền thông tin để đăng ký</p>

        <form action="${pageContext.request.contextPath}/auth" method="post">
            <input type="hidden" name="action" value="register">

            <div class="mb-3">
                <label class="form-label">Tên đăng nhập:</label>
                <input type="text" class="form-control" name="username" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input type="password" class="form-control" name="password" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Nhập lại mật khẩu</label>
                <input type="password" class="form-control" name="confirm" required>
            </div>

            <button class="btn btn-danger w-100 py-2 fw-bold" type="submit">
                Đăng ký
            </button>
        </form>

    </div>
</div>
<c:import url="${pageContext.request.contextPath}/view/customer/layout/footer.jsp" />
</body>
</html>
