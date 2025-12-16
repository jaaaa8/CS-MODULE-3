package controller;

import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.AuthService;
import service.impl.IAuthService;

import java.io.IOException;

@WebServlet (name = "AuthController", urlPatterns = "/auth")
public class AuthController extends HttpServlet{
    private final IAuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "register":
                showFormRegister(req, resp);
                break;
            case "login":
            default:
                showFormLogin(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "login":
                login(req, resp);
                break;
            case "register":
                register(req, resp);
                break;
            case "logout":
                HttpSession session = req.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
                break;
        }
    }

    private void showFormRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login/register.jsp").forward(req, resp);
    }

    private void showFormLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login/login.jsp").forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Account account = authService.login(username, password);
        if(account != null){
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("message", "Login failed! Please try again.");
            req.getRequestDispatcher("/view/login/login.jsp").forward(req, resp);
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if(!authService.isUsernameAvailable(username)){
            req.setAttribute("message", "Username is already taken! Please choose another one.");
            req.getRequestDispatcher("/view/login/register.jsp").forward(req, resp);
            return;
        }
        String password = req.getParameter("password");
        Account newAccount = authService.registerNewUser(username, password);
        if(newAccount != null){
            HttpSession session = req.getSession();
            session.setAttribute("account", newAccount);
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("message", "Registration failed! Please try again.");
            req.getRequestDispatcher("/view/login/register.jsp").forward(req, resp);
        }
    }
}
