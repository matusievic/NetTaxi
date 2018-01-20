package by.tc.web.controller.filter;

import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Customer;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomerFilter implements Filter {
    private static final Logger logger = Logger.getLogger(CustomerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("CustomerTag filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        if (session == null) {
            req.setAttribute(FilterConstants.ERROR, "You must sign in to view this page");
            resp.sendRedirect(FilterConstants.SIGN_IN);
        }

        User user = (User) session.getAttribute(FilterConstants.USER_PARAM);
        if (user == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else if (user.getClass() == Customer.class) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            req.setAttribute(FilterConstants.ERROR, "You must sign in to view this page");
            resp.sendRedirect(FilterConstants.SIGN_IN);
        }
    }

    @Override
    public void destroy() {
        logger.info("CustomerTag filter destroyed");
    }
}
