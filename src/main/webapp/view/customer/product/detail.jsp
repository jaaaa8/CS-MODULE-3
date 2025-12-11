<%--
  Created by IntelliJ IDEA.
  User: DELL G3
  Date: 12/2/2025
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TTT Bookstore | Chi tiết sản phẩm</title>
    <c:import url="../layout/library.jsp"/>
</head>
<body>
<c:import url="../layout/navbar.jsp" />
<!-- PRODUCT DETAIL -->
<div class="container my-5">
    <div class="row">

        <!-- IMAGE -->
        <div class="col-md-5">
            <div class="card shadow-sm">
                <img src="https://m.media-amazon.com/images/I/91uwocAMtSL.jpg" class="card-img-top" id="mainImg">

                <div class="d-flex justify-content-between p-2">
                    <img src="https://m.media-amazon.com/images/I/91uwocAMtSL.jpg" class="img-thumbnail small-img" width="100">
                    <img src="https://m.media-amazon.com/images/I/91uwocAMtSL.jpg" class="img-thumbnail small-img" width="100">
                    <img src="https://m.media-amazon.com/images/I/91uwocAMtSL.jpg" class="img-thumbnail small-img" width="100">
                    <img src="https://m.media-amazon.com/images/I/91uwocAMtSL.jpg" class="img-thumbnail small-img" width="100">
                </div>
            </div>
        </div>

        <!-- DETAIL -->
        <div class="col-md-7">
            <h3 class="fw-bold">Tên Sách – Book Title Example</h3>

            <!-- RATING -->
            <div class="text-warning mb-2">
                <i class="bi bi-star-fill"></i>
                <i class="bi bi-star-fill"></i>
                <i class="bi bi-star-fill"></i>
                <i class="bi bi-star-fill"></i>
                <i class="bi bi-star-half"></i>
                <span class="text-dark">(4.5/5 - 123 đánh giá)</span>
            </div>

            <h4 class="text-danger fw-bold">120.000₫</h4>
            <p class="text-decoration-line-through text-secondary">150.000₫</p>

            <p class="mt-3">
                <b>Mô tả:</b>
                Cuốn sách cung cấp kiến thức toàn diện về kỹ năng lập trình Java, phù hợp cho người mới bắt đầu hoặc chuẩn bị phỏng vấn.
            </p>

            <!-- QUANTITY -->
            <div class="d-flex align-items-center mb-3">
                <label class="me-2 fw-bold">Số lượng:</label>
                <input type="number" value="1" min="1" class="form-control" style="width:80px;">
            </div>

            <!-- BUTTONS -->
            <div class="d-flex gap-3">
                <button class="btn btn-danger px-4 py-2">
                    <i class="bi bi-cart-plus"></i> Thêm vào giỏ
                </button>

                <button class="btn btn-warning px-4 py-2">
                    <i class="bi bi-lightning-fill"></i> Mua ngay
                </button>
            </div>

            <div class="mt-4">
                <h5>Thông tin chi tiết:</h5>
                <ul>
                    <li>Tác giả: Nguyễn Văn A</li>
                    <li>Nhà xuất bản: NXB Trẻ</li>
                    <li>Số trang: 350</li>
                    <li>Năm xuất bản: 2025</li>
                </ul>
            </div>
        </div>
    </div>
</div>
<c:import url="../layout/footer.jsp" />
</body>
</html>
