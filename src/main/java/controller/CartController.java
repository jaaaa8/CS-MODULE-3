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
            // 1. Kiểm tra Session (Giữ nguyên)
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("account") == null) {
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
                return;
            }
            Account account = (Account) session.getAttribute("account");

            // 2. Lấy tham số (Thêm lấy quantity từ input)
            String bookIdParam = req.getParameter("bookId");
            if (bookIdParam == null || bookIdParam.trim().isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Sách không hợp lệ");
                return;
            }
            int bookId = Integer.parseInt(bookIdParam);

            // Mặc định là 1, nếu có tham số quantity thì lấy theo tham số
            int quantity = 1;
            try {
                String qtyParam = req.getParameter("quantity");
                if (qtyParam != null && !qtyParam.isEmpty()) {
                    quantity = Integer.parseInt(qtyParam);
                }
            } catch (NumberFormatException e) { /* Bỏ qua lỗi format */ }

            // 3. Lấy thông tin khách hàng và giỏ hàng (Giữ nguyên)
            Customer customer = customerService.findByAccountId(account.getId());
            if (customer == null) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Lỗi thông tin khách hàng.");
                return;
            }

            Orders cart = orderService.findCartByCustomerId(customer.getId());
            if (cart == null) {
                cart = orderService.getOrCreateCart(customer.getId());
            }

            // 4. KIỂM TRA TỒN KHO & TRỪ KHO (Dùng ProductService)
            ProductDto product = productService.findById(bookId); // Dùng ProductDto
            if (product == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Sách không tồn tại");
                return;
            }

            if (product.getStock() < quantity) {
                // Nếu kho không đủ -> Quay lại trang chi tiết báo lỗi
                resp.sendRedirect(req.getContextPath() + "/product?action=detail&id=" + bookId + "&error=stock");
                return;
            }

            // --- TRỪ KHO ---
            productService.updateStock(bookId, -quantity);

            // 5. Thêm vào OrderItem
            boolean exists = orderItemService.existItemInOrder(cart.getId(), bookId);
            if (exists) {
                orderItemService.increaseQuantity(cart.getId(), bookId, quantity);
            } else {
                orderItemService.addItem(cart.getId(), bookId, quantity, product.getPrice());
            }

            resp.sendRedirect(req.getContextPath() + "/customer/cart");

        } catch (Exception e) {
            e.printStackTrace();
            handleError(resp, "Lỗi khi thêm sách vào giỏ hàng");
        }
    }

    private void removeItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String orderItemIdParam = req.getParameter("orderItemId");
            if (orderItemIdParam == null || orderItemIdParam.trim().isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ");
                return;
            }
            int orderItemId = Integer.parseInt(orderItemIdParam);

            // 1. Tìm item trong giỏ để lấy số lượng cần hoàn
            // (Vì service chưa có hàm findById, ta lấy list ra rồi lọc)
            HttpSession session = req.getSession(false);
            Account account = (Account) session.getAttribute("account");
            Customer customer = customerService.findByAccountId(account.getId());
            Orders cart = orderService.findCartByCustomerId(customer.getId());

            OrderItemDto itemToRemove = null;
            if (cart != null) {
                List<OrderItemDto> items = orderItemService.getItemsByOrderId(cart.getId());
                for (OrderItemDto item : items) {
                    if (item.getOrderItemId() == orderItemId) {
                        itemToRemove = item;
                        break;
                    }
                }
            }

            // 2. HOÀN KHO & XÓA ITEM
            if (itemToRemove != null) {
                // Hoàn lại kho (số dương)
                productService.updateStock(itemToRemove.getBookId(), itemToRemove.getQuantity());

                // Xóa khỏi giỏ
                orderItemService.removeItem(orderItemId);
            } else {
                // Trường hợp item rác (không tìm thấy trong list nhưng vẫn có ID request)
                orderItemService.removeItem(orderItemId);
            }

            resp.sendRedirect(req.getContextPath() + "/customer/cart");

        } catch (Exception e) {
            e.printStackTrace();
            handleError(resp, "Lỗi khi xóa sản phẩm khỏi giỏ hàng");
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