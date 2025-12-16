package controller;

import entity.Account;
import entity.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.IService;
import service.OrderService;
import service.OrdersService;
import service.impl.IOrderService;
import service.impl.IOrdersService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderController", urlPatterns="/order")
public class OrderController extends HttpServlet {
    private final IOrderService orderService = new OrderService();
    private final IOrdersService ordersService = new OrdersService();
    private static final String JSP_PATH = "/view/admin/order/";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                addOrder(req, resp);
                break;
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
            case "add":
                req.getRequestDispatcher(JSP_PATH + "add.jsp").forward(req, resp);
                break;
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
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/auth?action=login");
            return;
        }

        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/auth?action=login");
            return;
        }
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            boolean isSuccess = ordersService.confirmOrder(orderId,account.getId());
            mess = isSuccess ? "Đã xác nhận đơn hàng ID " + orderId : "Xác nhận thất bại.";
        } catch (NumberFormatException e) {
            mess = "Lỗi: ID đơn hàng không hợp lệ.";
        }
        req.setAttribute("mess", mess);
        resp.sendRedirect("/order");
    }

    // CONFIRMED -> SHIPPED
    private void shippedAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mess;
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            boolean isSuccess = orderService.updateStatus(orderId, "SHIPPED");
            mess = isSuccess ? "Đơn hàng ID " + orderId + " đã chuyển sang vận chuyển." : "Chuyển vận chuyển thất bại.";
        } catch (NumberFormatException e) {
            mess = "Lỗi: ID đơn hàng không hợp lệ.";
        }
        resp.sendRedirect("/order?mess=" + mess);
    }

    // SHIPPED -> COMPLETED
    private void completeOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mess;
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            boolean isSuccess = orderService.updateStatus(orderId, "COMPLETED");
            mess = isSuccess ? "Đơn hàng ID " + orderId + " đã HOÀN THÀNH." : "Hoàn thành đơn hàng thất bại.";
        } catch (NumberFormatException e) {
            mess = "Lỗi: ID đơn hàng không hợp lệ.";
        }
        resp.sendRedirect("/order?mess=" + mess);
    }
    private void addOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mess;
        try {
            // 1. Lấy dữ liệu cơ bản (Giả định bạn có một form đơn giản)
            int customerId = Integer.parseInt(req.getParameter("customerId"));
            double total = Double.parseDouble(req.getParameter("total"));
            String status = req.getParameter("status"); // VD: PENDING

            // 2. Tạo đối tượng Orders
            Orders newOrder = new Orders(0, customerId, status, total, new Date(), 0);

            // 3. Gọi Service để thêm vào DB
            boolean isSuccess = orderService.add(newOrder);

            // Lưu ý: Hàm này chỉ thêm Orders. Bạn sẽ cần thêm OrderItem riêng biệt sau đó!

            mess = isSuccess ? "Thêm đơn hàng gốc thành công!" : "Thêm đơn hàng thất bại.";
        } catch (Exception e) {
            mess = "Lỗi nhập liệu: " + e.getMessage();
            e.printStackTrace();
        }
        resp.sendRedirect("/order?mess=" + mess);
    }
}