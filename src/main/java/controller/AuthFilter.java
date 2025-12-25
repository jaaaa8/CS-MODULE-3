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
                || uri.startsWith(context + "/auth")
                || uri.startsWith(context + "/customer/products")) {

            chain.doFilter(request, response);
            return;
        }

        if (uri.startsWith(context + "/admin")) {

            if (account == null) {
                resp.sendRedirect(context + "/auth?action=login");
                return;
            }

            if (!"ADMIN".equals(account.getRole())) {
                resp.sendRedirect(context + "/error?code=403");
                return;
            }

            chain.doFilter(request, response);
            return;
        }

        if (uri.startsWith(context + "/customer")) {

            if (account == null) {
                resp.sendRedirect(context + "/auth?action=login");
                return;
            }

            if (!"CUSTOMER".equals(account.getRole())) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN); // 403
                return;
            }

            chain.doFilter(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

}
