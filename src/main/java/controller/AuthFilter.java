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

        if (account != null) {
            req.setAttribute("currentUser", account);
        }

        if (uri.equals(context + "/home")
                || uri.startsWith(context + "/product")
                || uri.startsWith(context + "/css")
                || uri.startsWith(context + "/js")
                || uri.startsWith(context + "/images")
                || uri.startsWith(context + "/auth")) {

            chain.doFilter(request, response);
            return;
        }

        if (uri.startsWith(context + "/checkout")
                || uri.startsWith(context + "/payment")
                || uri.startsWith(context + "/cart")) {

            if (account == null) {
                resp.sendRedirect(context + "/auth?action=login");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
