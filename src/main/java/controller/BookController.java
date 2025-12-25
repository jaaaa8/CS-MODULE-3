package controller;

import dto.CustomerDto;
import dto.ProductDto;
import entity.Book;
import entity.Publisher;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.CategoryRepository;
import repository.impl.ICategoryRepository;
import service.AuthorService;
import service.CategoryService;
import service.PublisherService;
import service.impl.IAuthorService;
import service.impl.ICategoryService;
import service.impl.IProductService;
import service.ProductService;
import service.impl.IPublisherService;

import java.io.IOException;
import java.util.List;

@WebServlet(name="BookController" ,urlPatterns="/admin/book")
@MultipartConfig
public class BookController extends HttpServlet {
    IProductService productService = new ProductService();
    IAuthorService authorService = new AuthorService();
    ICategoryService categoryService = new CategoryService();
    IPublisherService publisherService = new PublisherService();
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
            default:
                showList(req, resp);
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
            req.setAttribute("categoryList", categoryService.findAll());
            req.setAttribute("authorList", authorService.findAll());
            req.setAttribute("publisherList", publisherService.findAll());
            req.getRequestDispatcher("/view/admin/book/add.jsp").forward(req, resp);

        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) {
        List<ProductDto> productList = productService.findAll();
        req.setAttribute("productList", productList);
        req.setAttribute("categoryList", categoryService.findAll());
        req.setAttribute("authorList", authorService.findAll());
        req.setAttribute("publisherList", publisherService.findAll());
        try {
            req.getRequestDispatcher("/view/admin/book/home.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int deleteId = Integer.parseInt(req.getParameter("deleteId"));
        boolean isSuccess = productService.delete(deleteId);
        String mess = isSuccess ? "Delete Successfully" : "Delete Error";
        resp.sendRedirect("/admin/book?mess=" + mess);
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
            boolean isSuccess = productService.add(book);

            String mess = isSuccess ? "Add Successfully" : "Add Error";
            resp.sendRedirect("/admin/book?mess=" + mess);
        }catch(Exception e) {
            resp.sendRedirect("/admin/book");
        }
    }
    private void showUpdate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        ProductDto book = productService.findById(id);

        req.setAttribute("book", book);
        req.setAttribute("categoryList", categoryService.findAll());
        req.setAttribute("authorList", authorService.findAll());
        req.setAttribute("publisherList", publisherService.findAll());
        req.getRequestDispatcher("/view/admin/book/update.jsp").forward(req, resp);
    }
    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      try{
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

        boolean isSuccess = productService.update(book);

        String mess = isSuccess ? "Update Successfully" : "Update Error";
        resp.sendRedirect("/admin/book?mess=" + mess);
      }catch(Exception e) {
        resp.sendRedirect("/admin/book");
      }
    }
    private void search(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String name = req.getParameter("name");
            Integer categoryId = req.getParameter("categoryId") != null && !req.getParameter("categoryId").isEmpty()
                    ? Integer.parseInt(req.getParameter("categoryId")) : null;
            Integer authorId = req.getParameter("authorId") != null && !req.getParameter("authorId").isEmpty()
                    ? Integer.parseInt(req.getParameter("authorId")) : null;
            Integer publisherId = req.getParameter("publisherId") != null && !req.getParameter("publisherId").isEmpty()
                    ? Integer.parseInt(req.getParameter("publisherId")) : null;

            List<ProductDto> productList = productService.filter(name, categoryId, authorId, publisherId);

            req.setAttribute("productList", productList);
            req.setAttribute("categoryList", categoryService.findAll());
            req.setAttribute("authorList", authorService.findAll());
            req.setAttribute("publisherList", publisherService.findAll());

            req.getRequestDispatcher("/view/admin/book/home.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
