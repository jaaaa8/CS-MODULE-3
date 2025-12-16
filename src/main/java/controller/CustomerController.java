package controller;

import entity.Book;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CustomerService;
import service.IService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/customer")
public class CustomerController extends HttpServlet {
    IService<Customer> customerService=new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if(action==null) action="";
        switch(action){
            case "add":
                req.getRequestDispatcher("/view/admin/customer_management/add.jsp").forward(req,resp);
                break;
            case "showUpdate":
                showUpdate(req,resp);
                break;
            default:
                List<Customer> customerList=customerService.findAll();
                req.setAttribute("customerList",customerList);
                req.getRequestDispatcher("/view/admin/customer_management/home.jsp").forward(req,resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if(action==null) action="";
        switch(action){
            case "add":
                save(req,resp);
                break;
            case "delete":
                deleteById(req,resp);
                break;
            case "update":
                update(req,resp);
                break;
            default:
                List<Customer> customerList=customerService.findAll();
                req.setAttribute("customerList",customerList);
                req.getRequestDispatcher("/view/admin/customer_management/home.jsp").forward(req,resp);
                break;
        }
    }
    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int deleteId = Integer.parseInt(req.getParameter("deleteId"));
        boolean isSuccess = customerService.delete(deleteId);
        String mess = isSuccess ? "Xoá thành công" : "Xoá thất bại";
        resp.sendRedirect("/customer?mess=" + mess);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
        int accountId = Integer.parseInt(req.getParameter("accountId"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        Customer customer = new Customer(0,accountId,name,email,phone,address);
        boolean isSuccess = customerService.add(customer);

        String mess = isSuccess ? "Thêm mới thành công" : "Thêm mới thất bại";
        resp.sendRedirect("/customer?mess=" + mess);
        }catch(Exception e){
            resp.sendRedirect("/customer?mess=Lỗi định dạng dữ liệu, thêm sách thất bại.");
        }
    }
    private void showUpdate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.findById(id);

        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/view/admin/customer_management/update.jsp").forward(req, resp);
    }
    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        int accountId = Integer.parseInt(req.getParameter("accountId"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        Customer customer = new Customer(id,accountId,name,email,phone,address);

        customerService.update(customer);
        resp.sendRedirect("/customer");
    }
}
