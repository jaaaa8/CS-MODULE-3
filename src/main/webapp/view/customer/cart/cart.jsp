<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/2/2025
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>TTT Bookstore | Giỏ hàng</title>
  <c:import url="../layout/library.jsp"/>
</head>
<body class="d-flex flex-column min-vh-100">

<c:import url="../layout/navbar.jsp" />

<div class="flex-grow-1">
<div class="container my-5">
  <div class="row">
    <div class="col-lg-8" id="cart-items-container">
      <c:choose>

        <c:when test="${empty cartItems}">
          <div class="alert alert-warning mt-4">
            Giỏ hàng của bạn đang trống.
          </div>
        </c:when>

        <c:otherwise>

          <p class="text-muted">
            Bạn có <b>${cartItems.size()}</b> sản phẩm trong giỏ hàng
          </p>

          <c:forEach items="${cartItems}" var="item">
            <div class="card mb-3">
              <div class="row g-0">
                <div class="col-md-3">
                  <img src="${item.imageUrl}" class="img-fluid">
                </div>
                <div class="col-md-6">
                  <h5>${item.title}</h5>
                  <p>Giá: ${item.price}</p>
                </div>
                <div class="col-md-3">
                  SL: ${item.quantity}<br>
                  Tổng: ${item.total}
                </div>
              </div>
            </div>
          </c:forEach>


        </c:otherwise>

      </c:choose>

    </div>

    <div class="col-lg-4 position-sticky" style="top:75px;">
      <div class="card p-4 shadow">
        <h5 class="fw-bold mb-3">Tóm tắt đơn hàng</h5>
        <div class="d-flex justify-content-between">
          <span>Thành tiền</span><span id="subtotal">$0.00</span>
        </div>
        <div class="d-flex justify-content-between">
          <span>Thuế VAT (8%)</span><span id="tax">$0.00</span>
        </div>
        <div class="d-flex justify-content-between mb-3">
          <span>Phí vận chuyển</span><span id="shipping">$5.99</span>
        </div>
        <hr>
        <div class="d-flex justify-content-between fw-bold fs-5">
          <span>TỔNG CỘNG</span><span class="text-primary" id="total">$0.00</span>
        </div>
        <div class="input-group my-3">
          <input type="text" class="form-control" id="promoCode" placeholder="Mã giảm giá">
          <button class="btn btn-outline-secondary" id="applyPromo">Áp dụng</button>
        </div>
        <a href="../payment/payment.jsp" class="btn btn-primary w-100 py-2 btn-gradient">
          <i class="bi bi-lock-fill"></i> TIẾN HÀNH THANH TOÁN
        </a>
      </div>
    </div>
  </div>
</div>
</div>
<c:import url="../layout/footer.jsp" />
<%--<script>--%>
<%--  let discountRate = 0;--%>

<%--  function updateTotals() {--%>
<%--    let subtotal = 0;--%>
<%--    const cartItems = document.querySelectorAll('.cart-item');--%>
<%--    const miniItemsContainer = document.getElementById('mini-items');--%>
<%--    miniItemsContainer.innerHTML = '';--%>
<%--    cartItems.forEach(item=>{--%>
<%--      const id=item.dataset.id;--%>
<%--      const price=parseFloat(item.dataset.price);--%>
<%--      const qty=parseInt(item.querySelector('.qty-input').value);--%>
<%--      const itemTotal=price*qty;--%>
<%--      subtotal+=itemTotal;--%>
<%--      item.querySelector('.item-price').innerText='$'+itemTotal.toFixed(2);--%>

<%--      // Mini cart--%>
<%--      const imgSrc=item.querySelector('img').src;--%>
<%--      const title=item.querySelector('h5').innerText;--%>
<%--      const miniItem=document.createElement('div');--%>
<%--      miniItem.classList.add('mini-item');--%>
<%--      // Dịch nội dung mini-cart--%>
<%--      miniItem.innerHTML=`--%>
<%--            <img src="${imgSrc}" alt="book">--%>
<%--            <div class="mini-item-info">--%>
<%--                <p class="mini-item-title">${title}</p>--%>
<%--                <p class="mini-item-price">$${itemTotal.toFixed(2)} x ${qty}</p>--%>
<%--            </div>--%>
<%--            <span class="mini-delete" dto-id="${id}">&times;</span>--%>
<%--        `;--%>
<%--      miniItemsContainer.appendChild(miniItem);--%>
<%--    });--%>

<%--    const tax=subtotal*0.08;--%>
<%--    const shipping=subtotal>0?5.99:0;--%>
<%--    const total=subtotal*(1-discountRate)+tax+shipping;--%>

<%--    document.getElementById('subtotal').innerText='$'+subtotal.toFixed(2);--%>
<%--    document.getElementById('tax').innerText='$'+tax.toFixed(2);--%>

<%--    document.getElementById('shipping').innerText='$'+shipping.toFixed(2);--%>
<%--    document.getElementById('total').innerText='$'+total.toFixed(2);--%>
<%--    document.getElementById('cart-count').innerText=cartItems.length;--%>
<%--    document.getElementById('cart-count-header').innerText=cartItems.length;--%>

<%--    // Cập nhật tổng cộng trong mini-cart--%>
<%--    document.getElementById('mini-total').innerText='$'+total.toFixed(2);--%>

<%--    bindMiniCartDelete();--%>
<%--  }--%>

<%--  function bindCartEvents(){--%>
<%--    document.querySelectorAll('.btn-plus').forEach(btn=>{--%>
<%--      btn.addEventListener('click',()=>{--%>
<%--        const input=btn.parentElement.querySelector('.qty-input');--%>
<%--        input.value=parseInt(input.value)+1;--%>
<%--        updateTotals();--%>
<%--      });--%>
<%--    });--%>
<%--    document.querySelectorAll('.btn-minus').forEach(btn=>{--%>
<%--      btn.addEventListener('click',()=>{--%>
<%--        const input=btn.parentElement.querySelector('.qty-input');--%>
<%--        input.value=Math.max(0,parseInt(input.value)-1);--%>
<%--        if(parseInt(input.value)===0){--%>
<%--          const card=btn.closest('.cart-item');--%>
<%--          card.classList.add('fade-out');--%>
<%--          setTimeout(()=>{card.remove();updateTotals();},300);--%>
<%--        } else updateTotals();--%>
<%--      });--%>
<%--    });--%>
<%--    document.querySelectorAll('.btn-delete').forEach(btn=>{--%>
<%--      btn.addEventListener('click',()=>{--%>
<%--        const card=btn.closest('.cart-item');--%>
<%--        card.classList.add('fade-out');--%>
<%--        setTimeout(()=>{card.remove();updateTotals();},300);--%>
<%--      });--%>
<%--    });--%>
<%--  }--%>

<%--  function bindMiniCartDelete(){--%>
<%--    document.querySelectorAll('.mini-delete').forEach(btn=>{--%>
<%--      btn.addEventListener('click',()=>{--%>
<%--        const id=btn.dataset.id;--%>
<%--        const card=document.querySelector(`.cart-item[dto-id="${id}"]`);--%>
<%--        if(card){ card.classList.add('fade-out'); setTimeout(()=>{card.remove();updateTotals();},300);}--%>
<%--      });--%>
<%--    });--%>
<%--  }--%>

<%--  document.getElementById('applyPromo').addEventListener('click',()=>{--%>
<%--    const code=document.getElementById('promoCode').value.trim().toUpperCase();--%>
<%--    discountRate=0;--%>
<%--    if(code==='SALE10') discountRate=0.1;--%>
<%--    if(code==='SALE20') discountRate=0.2;--%>
<%--    updateTotals();--%>
<%--  });--%>

<%--  window.onload=()=>{ bindCartEvents(); updateTotals(); };--%>
<%--</script>--%>
</body>
</html>

