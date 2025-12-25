package controller;

import entity.Account;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.AccountRepository;
import service.CustomerService;

import java.io.IOException;

@WebServlet(name = "HomeController", value = "/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        Account account = (session != null)
                ? (Account) session.getAttribute("account")
                : null;
        if (account == null) {
            // Cho phép vào trang home customer
            req.getRequestDispatcher("/view/customer/home/home.jsp").forward(req, resp);
            return;
        }

        if ("ADMIN".equals(account.getRole())) {
            req.getRequestDispatcher("/view/admin/book/home.jsp").forward(req, resp);
            return;
        }

        Customer customer = new CustomerService().findByAccountId(account.getId());

        if (customer == null) {
            req.getRequestDispatcher("/customer/profile").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("/view/customer/home/home.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
