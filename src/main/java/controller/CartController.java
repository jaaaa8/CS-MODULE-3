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
import service.impl.IOrderItemService;
import service.impl.IOrdersService;

import java.io.IOException;
import java.util.List;

@WebServlet (name = "CartController", urlPatterns = "/cart")
public class CartController extends HttpServlet {
    private final IOrdersService orderService = new OrdersService();
    private final IOrderItemService orderItemService = new OrderItemService();
    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action){
            case "view":
                // Implement cart view logic here
                showCartView(req, resp);
                break;
            case "detail":
                // Implement cart detail view logic here
                break;
            case "add":
                // Implement add to cart logic here
                addNewItemToCart(req, resp);
                break;
            case "remove":
                // Implement remove from cart logic here
                break;
            case "checkout":
                // Implement checkout logic here
                break;
            default:
                // Implement default cart view logic here
                break;
        }
    }

    private void addNewItemToCart(HttpServletRequest req, HttpServletResponse resp) {
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

            // 1️⃣ Account → Customer
            Customer customer = customerService.findByAccountId(account.getId());
            if (customer == null) {
                throw new RuntimeException("Customer not found for accountId=" + account.getId());
            }

            // 2️⃣ Get or create cart
            Orders cart = orderService.findCartByCustomerId(customer.getId());
            if (cart == null) {
                int cartId = orderService.createCartForCustomer(customer.getId());
                cart = orderService.findOrderById(cartId);
            }

            // 3️⃣ Get product info
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            if (quantity <= 0) quantity = 1;

            // 4️⃣ Add or update item
            boolean exists = orderItemService.existItemInOrder(cart.getId(), bookId);

            if (exists) {
                orderItemService.increaseQuantity(cart.getId(), bookId, quantity);
            } else {
                orderItemService.addItem(cart.getId(), bookId, quantity);
            }

            // 5️⃣ Redirect (PRG pattern)
            resp.sendRedirect(req.getContextPath() + "/cart");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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

            Customer customer = customerService.findByAccountId(account.getId());
            if (customer == null) {
                throw new RuntimeException("Customer not found for accountId=" + account.getId());
            }

            Orders cart = orderService.findCartByCustomerId(customer.getId());

            if (cart == null) {
                int cartId = orderService.createCartForCustomer(customer.getId());
                cart = orderService.findOrderById(cartId);
            }

            List<OrderItemDto> items = orderItemService.getItemsByOrderId(cart.getId());

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
                break;
            case "remove":
                // Implement remove from cart logic here
                break;
            case "checkout":
                // Implement checkout logic here
                break;
            default:
                // Implement default cart action here
                break;
        }
    }
}
