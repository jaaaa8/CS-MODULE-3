package controller;

import entity.Book;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;
import service.BookService;
import service.IService;

import java.io.IOException;
import java.util.List;

@WebServlet(name="BookController" ,urlPatterns="/book")
@MultipartConfig
public class BookController extends HttpServlet {
    IService<Book> bookService = new BookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "add":
                req.getRequestDispatcher("/view/admin/book/add.jsp").forward(req, resp);
                break;
            case "showUpdate":
                showUpdate(req, resp);
                break;

            default:
                List<Book> bookList = bookService.findAll();
                req.setAttribute("bookList", bookList);
                req.getRequestDispatcher("/view/admin/book/home.jsp").forward(req, resp);
                break;
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
            default:
                List<Book> bookList = bookService.findAll();
                req.setAttribute("bookList", bookList);
                req.getRequestDispatcher("/view/admin/book/home.jsp").forward(req, resp);
                break;
        }
    }
    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int deleteId = Integer.parseInt(req.getParameter("deleteId"));
        boolean isSuccess = bookService.delete(deleteId);
        String mess = isSuccess ? "Xoá thành công" : "Xoá thất bại";
        resp.sendRedirect("/book?mess=" + mess);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            int authorId = Integer.parseInt(req.getParameter("authorId"));
            int publisherId = Integer.parseInt(req.getParameter("publisherId"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            double price = Double.parseDouble(req.getParameter("price"));
            int stock = Integer.parseInt(req.getParameter("stock"));
            String imgURL = req.getParameter("imgURL");

            Book book = new Book(0, categoryId, authorId, publisherId, title, description, price, stock, imgURL);
            boolean isSuccess = bookService.add(book);

            String mess = isSuccess ? "Thêm mới thành công" : "Thêm mới thất bại";
            resp.sendRedirect("/book?mess=" + mess);
        }catch(Exception e) {
            resp.sendRedirect("/book?mess=Lỗi định dạng dữ liệu, thêm sách thất bại.");
        }
    }
    private void showUpdate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookService.findById(id);

        req.setAttribute("book", book);
        req.getRequestDispatcher("/view/admin/book/update.jsp").forward(req, resp);
    }
    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        int publisherId = Integer.parseInt(req.getParameter("publisherId"));

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int stock = Integer.parseInt(req.getParameter("stock"));
        String imgURL = req.getParameter("imgURL");

        Book book = new Book(id, categoryId, authorId, publisherId,
                title, description, price, stock, imgURL);

        bookService.update(book);
        resp.sendRedirect("/book");
    }
}
