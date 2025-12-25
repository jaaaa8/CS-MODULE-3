package controller;

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
import service.OrdersService;
import service.impl.IOrdersService;

import java.io.IOException;

@WebServlet (name = "PaymentController", urlPatterns = "/payments")
public class PaymentController extends HttpServlet {
    private final IOrdersService orderService = new OrdersService();
    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showPaymentPage(req, resp);
    }

    private void showPaymentPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/view/customer/payment/payment.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "checkout":
                checkout(req, resp);
                break;
            default:
                showPaymentPage(req, resp);
                break;
        }
    }

    private void checkout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        Account account = (Account) session.getAttribute("account");

        Customer customer = customerService.findByAccountId(account.getId());

        boolean success = orderService.checkout(customer.getId());

        if (success) {
            resp.sendRedirect(req.getContextPath() + "/cart?action=done");
        } else {
            resp.sendRedirect(req.getContextPath() + "/cart?action=error");
        }
    }
}
