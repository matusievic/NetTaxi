package by.tc.web.controller.control.command.impl;

import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.service.paginator.Paginator;
import by.tc.web.service.paginator.PaginatorFactory;
import by.tc.web.service.user.UserService;
import by.tc.web.service.user.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractCommand {
    protected static final UserService customerService = UserServiceFactory.getInstance().getCustomerService();
    protected static final UserService taxiDriverService = UserServiceFactory.getInstance().getTaxiDriverService();
    protected static final UserService administratorService = UserServiceFactory.getInstance().getAdministratorService();

    protected static final Paginator customerPaginator = PaginatorFactory.getInstance().getCustomerPaginator();
    protected static final Paginator taxiDriverPaginator = PaginatorFactory.getInstance().getTaxiDriverPaginator();


    protected void show404Message(String error, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ControllerConstants.ERROR, error);
        req.getRequestDispatcher("/404").forward(req, resp);
    }

    protected void show500Message(String error, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute(ControllerConstants.ERROR, error);
        resp.sendRedirect("/500");
    }
}
