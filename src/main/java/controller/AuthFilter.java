package controller;

import entity.Account;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String context = req.getContextPath();

        HttpSession session = req.getSession(false);
        Account account = (session != null)
                ? (Account) session.getAttribute("account")
                : null;

        // ================== PUBLIC ==================
        if (uri.equals(context + "/home")
                || uri.startsWith(context + "/bookDetail")
                || uri.startsWith(context + "/product")
                || uri.startsWith(context + "/css")
                || uri.startsWith(context + "/js")
                || uri.startsWith(context + "/images")
                || uri.startsWith(context + "/auth")) {

            chain.doFilter(request, response);
            return;
        }

        // ================== CART / PAYMENT (LOGIN REQUIRED) ==================
        if (uri.startsWith(context + "/cart")
                || uri.startsWith(context + "/checkout")
                || uri.startsWith(context + "/payment")) {

            if (account == null) {
                resp.sendRedirect(context + "/auth?action=login");
                return;
            }
        }

        // ================== ADMIN ONLY ==================
        if (uri.startsWith(context + "/admin")) {
            if (account == null) {
                resp.sendRedirect(context + "/auth?action=login");
                return;
            }
            if (!"ADMIN".equals(account.getRole())) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Không có quyền admin");
                return;
            }
        }

        // Gắn user hiện tại cho JSP
        if (account != null) {
            req.setAttribute("currentUser", account);
        }

        chain.doFilter(request, response);
    }
}
