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
        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-success text-center">
                    ${sessionScope.message}
            </div>
            <c:remove var="message" scope="session"/>
        </c:if>


        <form action="${pageContext.request.contextPath}/auth" method="post">
            <input type="hidden" name="action" value="login">

        <!-- Email -->
            <div class="mb-3">
                <label class="form-label">Tên đăng nhập</label>
                <input type="text" class="form-control" name="username" required>
            </div>

            <!-- Password -->
            <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input type="password" class="form-control" name="password" required>
            </div>

            <button class="btn btn-danger w-100 py-2 fw-bold" type="submit">Đăng nhập</button>

            <div class="text-center mt-3">
                <a href="#" class="text-decoration-none text-danger">Quên mật khẩu?</a>
            </div>

            <div class="text-center mt-3">
                Bạn chưa có tài khoản?
                <a href="${pageContext.request.contextPath}/auth?action=register"
                   class="text-danger fw-bold text-decoration-none">
                    Đăng ký
                </a>

            </div>

        </form>

    </div>
</div>
<c:import url="${pageContext.request.contextPath}/view/customer/layout/footer.jsp" />
</body>
</html>
