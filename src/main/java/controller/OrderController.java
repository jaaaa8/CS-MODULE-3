package controller;

import dto.OrderItemDto;
import dto.OrdersDto;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.OrderItemService;
import service.OrdersService;
import service.impl.IOrderItemService;
import service.impl.IOrdersService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "OrderController", urlPatterns="/admin/order")
public class OrderController extends HttpServlet {

    private final IOrdersService orderService = new OrdersService();
    private final IOrderItemService orderItemService = new OrderItemService();
    private static final String JSP_PATH = "/view/admin/order/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "delete":
                deleteById(req, resp);
                break;
            case "detail":
                showDetail(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            default:
                showOrderList(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "delete":
                deleteById(req, resp);
                break;
            case "detail":
                break;
            case "admin_confirm":
                confirmAdmin(req, resp);
                break;
            case "admin_shipped":
                shippedAdmin(req, resp);
                break;
            case "client_complete":
                completeOrder(req, resp);
                break;
            default:
                showOrderList(req, resp);
                break;
        }
    }

    private void showOrderList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<OrdersDto> ordersList = orderService.findAllOrders();
        req.setAttribute("orderList", ordersList);
        req.getRequestDispatcher(JSP_PATH + "home.jsp").forward(req, resp);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mess;
        try {
            int deleteId = Integer.parseInt(req.getParameter("deleteId"));
            boolean isSuccess = orderService.deleteOrder(deleteId);
            mess = isSuccess
                    ? "Xoa don hang " + deleteId + " thanh cong"
                    : "Xoa don hang that bai ";
        } catch (NumberFormatException e) {
            mess = "Loi id 0 hop le.";
        }
        resp.sendRedirect(req.getContextPath() + "/admin/order?mess=" + mess);
    }

    private void confirmAdmin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

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
            Integer adminId = (Integer) req.getSession().getAttribute("adminId");

            if (adminId == null) {
                mess = "Vui lòng đăng nhập admin.";
            } else {
                boolean isSuccess = orderService.confirmOrder(orderId, adminId);
                mess = isSuccess
                        ? "Đã xác nhận đơn hàng ID " + orderId
                        : "Xác nhận thất bại.";
            }
        } catch (NumberFormatException e) {
            mess = "ID đơn hàng không hợp lệ.";
        }
        String encodedMess = URLEncoder.encode(mess, StandardCharsets.UTF_8);
        resp.sendRedirect(req.getContextPath() + "/admin/order?mess=" + encodedMess);
    }

    // ADMIN: CONFIRMED -> SHIPPED
    private void shippedAdmin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String mess;
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            boolean isSuccess = orderService.updateOrderStatus(orderId, "SHIPPED");
            mess = isSuccess
                    ? "Đơn hàng ID " + orderId + " đã chuyển sang vận chuyển."
                    : "Chuyển trạng thái thất bại.";
        } catch (NumberFormatException e) {
            mess = "ID đơn hàng không hợp lệ.";
        }
        String encodedMess = URLEncoder.encode(mess, StandardCharsets.UTF_8);
        resp.sendRedirect(req.getContextPath() + "/admin/order?mess=" + encodedMess);
    }

    // CLIENT: SHIPPED -> COMPLETED
    private void completeOrder(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String mess;
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            boolean isSuccess = orderService.updateOrderStatus(orderId, "COMPLETED");
            mess = isSuccess
                    ? "Đơn hàng ID " + orderId + " đã hoàn thành."
                    : "Hoàn thành đơn hàng thất bại.";
        } catch (NumberFormatException e) {
            mess = "ID đơn hàng không hợp lệ.";
        }
        resp.sendRedirect(req.getContextPath() + "/admin/order?mess=" + mess);
    }
    private void showDetail(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));

            // Lấy danh sách item của đơn hàng
            List<OrderItemDto> itemList = orderItemService.getItemsByOrderId(orderId);

            // Gửi dữ liệu sang JSP
            req.setAttribute("orderId", orderId);
            req.setAttribute("itemList", itemList);

            req.getRequestDispatcher(JSP_PATH + "detail.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            req.setAttribute("mess", "ID đơn hàng không hợp lệ");
            req.getRequestDispatcher(JSP_PATH + "home.jsp").forward(req, resp);
        }
    }
    private void search(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<OrdersDto> ordersList;

        if (keyword != null && !keyword.trim().isEmpty()) {
            ordersList = orderService.search(keyword);
        } else {
            ordersList = orderService.findAllOrders();
        }
        req.setAttribute("orderList", ordersList);
        req.setAttribute("keyword", keyword); // Giữ lại từ khóa ở ô input
        req.getRequestDispatcher(JSP_PATH + "home.jsp").forward(req, resp);
    }

}
