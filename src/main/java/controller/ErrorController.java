package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ErrorController", urlPatterns = "/error")
public class ErrorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("code");

        if ("403".equals(code)) {
            req.setAttribute("errorCode", 403);
            req.setAttribute("errorTitle", "403 - Forbidden");
            req.setAttribute("errorMessage", "Bạn không có quyền truy cập vào trang này.");
            req.getRequestDispatcher("/view/error/403.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}

