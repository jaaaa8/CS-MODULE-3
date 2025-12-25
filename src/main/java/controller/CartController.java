package controller;

import dto.OrderItemDto;
import dto.ProductDto;
import entity.Account;
import entity.Customer;
import entity.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CustomerService;
import service.OrderItemService;
import service.OrdersService;
import service.ProductService;
import service.impl.ICustomerService;
import service.impl.IOrderItemService;
import service.impl.IOrdersService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "CartController", urlPatterns = "/customer/cart")
public class CartController extends HttpServlet {
    private final IOrdersService orderService = new OrdersService();
    private final IOrderItemService orderItemService = new OrderItemService();
    private final ICustomerService customerService = new CustomerService();
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action){
            case "miniCart":
                loadMiniCartJson(req, resp);
                break;
            case "done":
                showSuccessView(req, resp);
                break;
            case "checkout":
                // TODO: Implement checkout action
                break;
            default:
                showCartView(req, resp);
                break;
        }
    }

    private void showSuccessView(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/view/customer/cart/success.jsp")
                    .forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            handleError(resp, "Lỗi hiển thị trang thành công");
        }
    }

    private void addNewItemToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Kiểm tra session
            HttpSession session = req.getSession(false);
            if (session == null) {
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
                return;
            }

            // Kiểm tra account
            Account account = (Account) session.getAttribute("account");
            if (account == null) {
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
                return;
            }

            // Lấy bookId từ request
            String bookIdParam = req.getParameter("bookId");
            if (bookIdParam == null || bookIdParam.trim().isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Sách không hợp lệ");
                return;
            }

            int bookId;
            try {
                bookId = Integer.parseInt(bookIdParam);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sách không hợp lệ");
                return;
            }

            // Tìm customer theo account ID
            Customer customer = customerService.findByAccountId(account.getId());
            if (customer == null) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Thông tin khách hàng không tồn tại. Vui lòng cập nhật hồ sơ.");
                return;
            }

            // Lấy giỏ hàng hiện tại
            Orders cart = orderService.findCartByCustomerId(customer.getId());

            // Nếu chưa có giỏ hàng, tạo mới
            if (cart == null) {

                cart = orderService.getOrCreateCart(customer.getId());
                if (cart == null) {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống khi tạo giỏ hàng.");
                    return;
                }
            }

            // Lấy giá sách từ database
            ProductDto product = productService.findById(bookId);
            if (product == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Sách không tồn tại");
                return;
            }

            double bookPrice = product.getPrice();

            // Kiểm tra xem sách đã có trong giỏ hàng chưa
            boolean exists = orderItemService.existItemInOrder(cart.getId(), bookId);

            if (exists) {
                // Nếu đã có, tăng số lượng
                orderItemService.increaseQuantity(cart.getId(), bookId, 1);
            } else {
                // Nếu chưa có, thêm mới với giá đúng
                orderItemService.addItem(cart.getId(), bookId, 1, bookPrice);
            }

            resp.sendRedirect(req.getContextPath() + "/customer/cart");

        } catch (Exception e) {
            e.printStackTrace();
            handleError(resp, "Lỗi khi thêm sách vào giỏ hàng");
        }
    }

    private void showCartView(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Kiểm tra session
            HttpSession session = req.getSession(false);
            if (session == null) {
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
                return;
            }

            // Kiểm tra account
            Account account = (Account) session.getAttribute("account");
            if (account == null) {
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
                return;
            }

            // Tìm customer theo account ID
            Customer customer = customerService.findByAccountId(account.getId());
            if (customer == null) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Thông tin khách hàng không tồn tại. Vui lòng cập nhật hồ sơ.");
                return;
            }

            // Lấy giỏ hàng hiện tại
            Orders cart = orderService.findCartByCustomerId(customer.getId());

            // Nếu chưa có giỏ hàng, tạo mới
            if (cart == null) {
                cart = orderService.getOrCreateCart(customer.getId());
                if (cart == null) {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống khi tạo giỏ hàng.");
                    return;
                }
            }

            // Lấy danh sách sản phẩm trong giỏ hàng
            List<OrderItemDto> items = orderItemService.getItemsByOrderId(cart.getId());

            if (items == null) {
                items = new ArrayList<>();
            }

            req.setAttribute("cart", cart);
            req.setAttribute("orderItems", items);

            req.getRequestDispatcher("/view/customer/cart/cart.jsp")
                    .forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            handleError(resp, "Lỗi khi hiển thị giỏ hàng");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        try {
            switch (action){
                case "add":
                    addNewItemToCart(req, resp);
                    break;
                case "remove":
                    removeItem(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Hành động không hợp lệ");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleError(resp, "Lỗi xử lý yêu cầu");
        }
    }

    private void removeItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String orderItemIdParam = req.getParameter("orderItemId");
            if (orderItemIdParam == null || orderItemIdParam.trim().isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ");
                return;
            }

            int orderItemId;
            try {
                orderItemId = Integer.parseInt(orderItemIdParam);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ");
                return;
            }

            boolean removed = orderItemService.removeItem(orderItemId);
            if (!removed) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không thể xóa sản phẩm. Vui lòng thử lại.");
                return;
            }

            resp.sendRedirect(req.getContextPath() + "/customer/cart");

        } catch (Exception e) {
            e.printStackTrace();
            handleError(resp, "Lỗi khi xóa sản phẩm khỏi giỏ hàng");
        }
    }

    private void loadMiniCartJson(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession(false);
            if (session == null) {
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().write("{\"items\":[],\"totalPrice\":0}");
                return;
            }

            Account account = (Account) session.getAttribute("account");
            if (account == null) {
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().write("{\"items\":[],\"totalPrice\":0}");
                return;
            }

            Customer customer = customerService.findByAccountId(account.getId());
            if (customer == null) {
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().write("{\"items\":[],\"totalPrice\":0}");
                return;
            }

            Orders cart = orderService.findCartByCustomerId(customer.getId());
            if (cart == null) {
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().write("{\"items\":[],\"totalPrice\":0}");
                return;
            }

            List<OrderItemDto> items = orderItemService.getItemsByOrderId(cart.getId());

            double totalPrice = 0;
            for (OrderItemDto item : items) {
                totalPrice += item.getPrice() * item.getQuantity();
            }

            // Tạo JSON response
            StringBuilder json = new StringBuilder();
            json.append("{\"items\":[");

            for (int i = 0; i < items.size(); i++) {
                OrderItemDto item = items.get(i);
                double subtotal = item.getPrice() * item.getQuantity();

                json.append("{")
                        .append("\"bookName\":\"").append(escapeJson(item.getBookName())).append("\",")
                        .append("\"price\":").append(item.getPrice()).append(",")
                        .append("\"quantity\":").append(item.getQuantity()).append(",")
                        .append("\"subtotal\":").append(subtotal)
                        .append("}");

                if (i < items.size() - 1) {
                    json.append(",");
                }
            }

            json.append("],\"totalPrice\":").append(totalPrice).append("}");

            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write(json.toString());

        } catch (Exception e) {
            e.printStackTrace();
            try {
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().write("{\"items\":[],\"totalPrice\":0}");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Escape JSON string to avoid injection
     */
    private String escapeJson(String text) {
        if (text == null) return "";
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    /**
     * Phương thức hỗ trợ xử lý lỗi
     */
    private void handleError(HttpServletResponse resp, String message) {
        try {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
        } catch (IOException e) {
            System.err.println("Không thể gửi lỗi: " + message);
            e.printStackTrace();
        }
    }
}