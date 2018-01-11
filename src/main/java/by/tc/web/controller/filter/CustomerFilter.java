package by.tc.web.controller.filter;

import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Customer;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomerFilter implements Filter {
    private static final Logger logger = Logger.getLogger(CustomerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Customer filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        if (session == null) {
            req.setAttribute("error", "You must sign in to view this page");
            resp.sendRedirect("/login");
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/403");
        } else if (user.getClass() == Customer.class) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            req.setAttribute("error", "You must sign in to view this page");
            resp.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {
        logger.info("Customer filter destroyed");
    }
}