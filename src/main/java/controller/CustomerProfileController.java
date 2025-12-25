package controller;

import entity.Account;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CustomerService;
import service.impl.ICustomerService;

import java.io.IOException;

@WebServlet (name = "CustomerProfile", urlPatterns = "/customer/profile")
public class CustomerProfileController extends HttpServlet {
    private final ICustomerService customerService = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getRequestDispatcher("/view/customer/infor/customerdetail.jsp").forward(req,resp);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
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
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            Customer newCustomer = customerService.createNewCustomerByAccountId(account.getId(),name,email,phone,address);
            if (newCustomer != null) {
                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                resp.sendRedirect(req.getContextPath() + "/customer?action=addByCustomer&error=1");
            }

        }catch(Exception e){
            try {
                resp.sendRedirect("/home?mess=Lỗi định dạng dữ liệu, thêm sách thất bại.");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
