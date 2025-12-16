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


@WebServlet(name = "ProductController", urlPatterns = "/products")
public class    ProductController extends HttpServlet {

    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "detail":
                showDetail(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            default:
                showProductsToHome(req, resp);
                break;
        }
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        ProductDto product = productService.findById(id);

        req.setAttribute("product", product);

        req.getRequestDispatcher(
                "/view/customer/product/detail.jsp"
        ).forward(req, resp);
    }


    private void showProductsToHome(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<ProductDto> productList = productService.findAll();
        System.out.println("Product size = " + productList.size());

        req.setAttribute("productList", productList);
        req.getRequestDispatcher("/view/customer/home/home.jsp")
                .forward(req, resp);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String keyword = req.getParameter("keyword");

        List<ProductDto> productList =
                productService.search(keyword);

        req.setAttribute("productList", productList);
        req.setAttribute("keyword", keyword);

        req.getRequestDispatcher("/view/customer/home/home.jsp")
                .forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}

