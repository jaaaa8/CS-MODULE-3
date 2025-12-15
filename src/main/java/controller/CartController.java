//package controller;
//
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import service.CartService;
//import service.ICartService;
//
//import java.io.IOException;
//
//@WebServlet("/cart")
//public class CartController extends HttpServlet {
//
//    private final ICartService cartService = new CartService();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        int customerId = 1; // TEST – sau này lấy từ session
//
//        req.setAttribute("cartItems", cartService.getCart(customerId));
//        req.getRequestDispatcher("/view/customer/cart/cart.jsp")
//                .forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException {
//
//        int customerId = 1; // TEST
//        int bookId = Integer.parseInt(req.getParameter("bookId"));
//        int quantity = Integer.parseInt(req.getParameter("quantity"));
//
//        cartService.addToCart(customerId, bookId, quantity);
//
//        resp.sendRedirect(req.getContextPath() + "/cart");
//    }
//}
//
//
//
