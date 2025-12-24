package controller;

import dto.OrderItemDto;
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
import service.impl.ICustomerService;
import service.impl.IOrderItemService;
import service.impl.IOrdersService;

import java.io.IOException;
import java.util.List;

@WebServlet (name = "CartController", urlPatterns = "/cart")
public class CartController extends HttpServlet {
    private final IOrdersService orderService = new OrdersService();
    private final IOrderItemService orderItemService = new OrderItemService();
    private final ICustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action){
            case "add":
                // thường add dùng POST → không xử lý ở GET
                break;
            case "done":
                showSuccessView(req, resp);
                break;
            case "checkout":
                break;
            default:
                // ✅ MẶC ĐỊNH LÀ XEM CART
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
            throw new RuntimeException(e);
        }
    }


    private void addNewItemToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

        int bookId = Integer.parseInt(req.getParameter("bookId"));

        Customer customer = customerService.findById(account.getId());

        Orders cart = orderService.findCartByCustomerId(customer.getId());
        if (cart == null) {
            int cartId = orderService.createCartForCustomer(customer.getId());
            cart = orderService.findOrderById(cartId);
        }

        boolean exists = orderItemService.existItemInOrder(cart.getId(), bookId);

        if (exists) {
            orderItemService.increaseQuantity(cart.getId(), bookId, 1);
        } else {
            orderItemService.addItem(cart.getId(), bookId, 1, 0);
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    private void showCartView(HttpServletRequest req, HttpServletResponse resp) {
        try {
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

            Customer customer = customerService.findById(account.getId());
            if (customer == null) {
                throw new RuntimeException("Customer not found for accountId=" + account.getId());
            }

            Orders cart = orderService.findCartByCustomerId(customer.getId());

            if (cart == null) {
                int cartId = orderService.createCartForCustomer(customer.getId());
                cart = orderService.findOrderById(cartId);
            }

            List<OrderItemDto> items = orderItemService.getItemsByOrderId(cart.getId());
            if(items.isEmpty()){
                req.setAttribute("mess","Giỏ hàng của bạn đang trống.");
            }

            req.setAttribute("cart", cart);
            req.setAttribute("orderItems", items);

            req.getRequestDispatcher("/view/customer/cart/cart.jsp")
                    .forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action){
            case "add":
                // Implement add to cart logic here
                addNewItemToCart(req, resp);
                break;
            case "remove":
                removeItem(req, resp);
                // Implement remove from cart logic here
                break;
            default:
                // Implement default cart action here
                break;
        }
    }

    private void removeItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int orderItemId = Integer.parseInt(req.getParameter("orderItemId"));
        orderItemService.removeItem(orderItemId);

        resp.sendRedirect(req.getContextPath() + "/cart");
    }



}
