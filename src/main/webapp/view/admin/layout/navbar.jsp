<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 15/12/2025
  Time: 8:40 CH
  To change this template use File | Settings | File Templates.
--%>
<%--
  File: admin_navbar.jsp
  Description: Admin Navigation Bar dùng Bootstrap 5
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container-fluid">

        <a class="navbar-brand fw-bold text-warning" href="#">
            <i class="bi bi-gear-fill me-2"></i> ADMIN DASHBOARD
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/book">
                        <i class="bi bi-book me-1"></i> Book Management
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/customer">
                        <i class="bi bi-people-fill me-1"></i> Customer Management
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/order">
                        <i class="bi bi-receipt me-1"></i> Order Management
                    </a>
                </li>

            </ul>

            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <ul>
                        <li>
                            <p>
                                Xin chào ADMIN ${account.username}!
                            </p>
                        </li>
                    </ul>
                    <ul>
                        <li><a class="dropdown-item text-white" href="${pageContext.request.contextPath}/auth?action=logout"><i class="bi bi-box-arrow-right me-1"></i> Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
