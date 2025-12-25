package controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import entity.Account;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CustomerService;
import service.AccountService;

import java.io.IOException;

@WebServlet("/profile")
public class CustomerProfileController extends HttpServlet {

    private final CustomerService customerService = new CustomerService();
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            resp.sendRedirect("/login");
            return;
        }

        // 🔥 SỬA Ở ĐÂY
        Customer customer = customerService.findById(account.getId());

        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/view/customer/customer.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");
        if ("changePassword".equals(action)) {

            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("account");

            String newPassword = req.getParameter("newPassword");

            accountService.updatePassword(account.getId(), newPassword);

            account.setPassword(newPassword);
            session.setAttribute("account", account);

            resp.sendRedirect("/profile");
        }
    }
}

