package controller;

import dto.CustomerDto;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AccountService;
import service.CustomerService;
import service.impl.ICustomerService;
import service.impl.IAccountService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerManagementController", value = "/customer")
public class CustomerManagementController extends HttpServlet {
    ICustomerService customerService = new CustomerService();
    IAccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                showFormAdd(req, resp);
                break;
            case "showUpdate":
                showUpdate(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            case "addByCustomer":
                showFormAddByCustomer(req,resp);
                break;
            default:
                showList(req, resp);
                break;
        }

    }

    private void showFormAddByCustomer(HttpServletRequest req, HttpServletResponse resp) {
        try{
            req.getRequestDispatcher("/view/customer/infor/customerdetail.jsp").forward(req,resp);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                save(req, resp);
                break;
            case "delete":
                deleteById(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            default:
                showList(req, resp);
                break;
        }
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("accountList", accountService.getAllAccounts());
            req.getRequestDispatcher("/view/admin/customer_management/add.jsp").forward(req, resp);

        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) {
        List<CustomerDto> customerList = customerService.findAll();
        req.setAttribute("customerList", customerList);
        req.setAttribute("accountList", accountService.getAllAccounts());
        try {
            req.getRequestDispatcher("/view/admin/customer_management/home.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int deleteId = Integer.parseInt(req.getParameter("deleteId"));
        boolean isSuccess = customerService.delete(deleteId);
        String mess = isSuccess ? "Delete Successfully" : "Delete Errol";
        resp.sendRedirect("/customer?mess=" + mess);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int accountId = Integer.parseInt(req.getParameter("accountId"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            Customer customer = new Customer(0, accountId, name, email, phone, address);
            boolean isSuccess = customerService.add(customer);

            String mess = isSuccess ? "Add Successfully" : "Add Error";
            resp.sendRedirect("/customer?mess=" + mess);
        } catch (Exception e) {
            resp.sendRedirect("/customer");
        }
    }

    private void showUpdate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.findById(id);

        req.setAttribute("customer", customer);
        req.setAttribute("accountList", accountService.getAllAccounts());
        req.getRequestDispatcher("/view/admin/customer_management/update.jsp").forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int accountId = Integer.parseInt(req.getParameter("accountId"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");

            Customer customer = new Customer(id, accountId, name, email, phone, address);

            boolean isSuccess = customerService.update(customer);
            String mess = isSuccess ? "Update Successfully" : "Update Error";
            resp.sendRedirect("/customer?mess=" + mess);
        } catch (Exception e) {
            resp.sendRedirect("/customer");
        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        List<CustomerDto> customerList = customerService.search(name);

        req.setAttribute("customerList", customerList);


        try {
            req.getRequestDispatcher("/view/admin/customer_management/home.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
