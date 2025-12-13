package controller;

import dto.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.IProductService;
import service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = "/customers")
public class ProductController extends HttpServlet {
    private IProductService productService = new ProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "search":
                search(req, resp);
                break;
            default:
                showProductsToHome(req, resp);
                break;
        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String searchTitle = req.getParameter("searchTitle");
        String searchAuthorName = req.getParameter("searchAuthorName");

        List<ProductDto> productList =
                productService.search(searchTitle, null, searchAuthorName, null);

        req.setAttribute("productList", productList);
        req.setAttribute("searchTitle", searchTitle);
        req.setAttribute("searchAuthorName", searchAuthorName);

        req.getRequestDispatcher("/view/home.jsp").forward(req, resp);
    }

    private void showProductsToHome(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("productList", productService.findAll());
        req.getRequestDispatcher("/view/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
