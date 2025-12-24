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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Account account = (Account) session.getAttribute("account");

        if ("ADMIN".equals(account.getRole())) {
            req.getRequestDispatcher("/view/admin/book/home.jsp").forward(req, resp);
        } else {
            Customer customer = new CustomerService().findById(account.getId());
            if(customer == null){
                // Nếu chưa có thông tin khách hàng, chuyển hướng đến trang tạo thông tin khách hàng
                resp.sendRedirect(req.getContextPath() + "/customer?action=addByCustomer");
                return;
            }
            req.getRequestDispatcher("/view/customer/home/home.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
