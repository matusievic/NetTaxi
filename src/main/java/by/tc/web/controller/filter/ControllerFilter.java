package by.tc.web.controller.filter;

import by.tc.web.controller.control.command.activity.ControllerActivity;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.domain.user.impl.TaxiDriver;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerFilter implements Filter {
    private static final Logger logger = Logger.getLogger(ControllerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Controller filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String command = req.getParameter(FilterConstants.COMMAND_PARAM);
        if (command == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        ControllerActivity activity = ControllerActivity.valueOf(command.toUpperCase());

        User user = (User) req.getSession().getAttribute(FilterConstants.USER_PARAM);
        if (user == null) {
            if (FilterConstants.unregisteredUserActivities.contains(activity)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        if (FilterConstants.accountActivities.contains(activity)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Class userClass = user.getClass();

        if (userClass == Customer.class) {
            if (FilterConstants.customerActivities.contains(activity)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        if (userClass == TaxiDriver.class) {
            if (FilterConstants.taxiDriverActivities.contains(activity)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        if (userClass == Administrator.class) {
            if (FilterConstants.administratorActivities.contains(activity)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        resp.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    public void destroy() {
        logger.info("Controller filter destroyed");
    }
}
