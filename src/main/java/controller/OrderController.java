package controller;

import entity.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OrderService;
import service.impl.IOrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderController", urlPatterns="/order")
public class OrderController extends HttpServlet {
    private final IOrderService orderService = new OrderService();
    private static final String JSP_PATH = "/view/admin/order/";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "delete":
                deleteById(req, resp);
                break;
            case "admin_confirm":
                confirmAdmin(req, resp); // PENDING -> CONFIRMED
                break;
            case "admin_shipped":
                shippedAdmin(req, resp); // CONFIRMED -> SHIPPED
                break;
            case "client_complete":
                completeOrder(req, resp); // SHIPPED -> COMPLETED
                break;
            default:
                showOrderList(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "delete":
                deleteById(req, resp);
                break;
            default:
                showOrderList(req, resp);
                break;
        }
    }

    private void showOrderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Orders> ordersList = orderService.findAll();
        req.setAttribute("orderList", ordersList); // Đã sửa tên attribute
        req.getRequestDispatcher(JSP_PATH + "home.jsp").forward(req, resp);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int deleteId = 0;
        String mess;
        try {
            deleteId = Integer.parseInt(req.getParameter("deleteId"));
            boolean isSuccess = orderService.delete(deleteId);
            mess = isSuccess ? "Xoá đơn hàng ID " + deleteId + " thành công" : "Xoá đơn hàng thất bại";
        } catch (NumberFormatException e) {
            mess = "Lỗi: ID xoá không hợp lệ.";
        }
        resp.sendRedirect("/order?mess=" + mess);
    }

    private void confirmAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mess;
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            Integer adminId = (Integer) req.getSession().getAttribute("adminId");

            if (adminId == null || adminId == 0) {
                mess = "Lỗi: Vui lòng đăng nhập lại để xác nhận đơn hàng.";
            } else {
                boolean isSuccess = orderService.updateStatus(orderId, "CONFIRMED", adminId);
                mess = isSuccess ? "Đã xác nhận đơn hàng ID " + orderId : "Xác nhận thất bại.";
            }
        } catch (NumberFormatException e) {
            mess = "Lỗi: ID đơn hàng không hợp lệ.";
        }
        resp.sendRedirect(req.getContextPath() + "/order?mess=" + mess);
    }

    private void shippedAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mess;
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            Integer adminId = (Integer) req.getSession().getAttribute("adminId");
            int accountId = (adminId != null) ? adminId : 0;
            boolean isSuccess = orderService.updateStatus(orderId, "SHIPPED", accountId);
            mess = isSuccess ? "Đơn hàng ID " + orderId + " đã chuyển sang vận chuyển." : "Chuyển vận chuyển thất bại.";
        } catch (NumberFormatException e) {
            mess = "Lỗi: ID đơn hàng không hợp lệ.";
        }
        resp.sendRedirect(req.getContextPath() + "/order?mess=" + mess);
    }
    // SHIPPED -> COMPLETED
    // Trong OrderController.java

    private void completeOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mess;
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            Integer adminId = (Integer) req.getSession().getAttribute("adminId");
            int accountId = (adminId != null) ? adminId : 0;

            boolean isSuccess = orderService.updateStatus(orderId, "COMPLETED", accountId);

            mess = isSuccess ? "Đơn hàng ID " + orderId + " đã HOÀN THÀNH." : "Hoàn thành đơn hàng thất bại.";
        } catch (NumberFormatException e) {
            mess = "Lỗi: ID đơn hàng không hợp lệ.";
        }
        resp.sendRedirect(req.getContextPath() + "/order?mess=" + mess);
    }
}