package by.tc.web.controller.control.command.impl.common;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.pagination.Pagination;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrdersDisplayingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(ControllerConstants.USER_PARAM);

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String currentPageParam = req.getParameter(ControllerConstants.CURRENT_PAGE_PARAM);
        int currentPage = Converter.parseInt(currentPageParam).orElse(1);

        Class userClass = user.getClass();
        Pagination pagination;

        if (userClass == Customer.class) {
            pagination = displayCustomerOrders(currentPage, ControllerConstants.ITEMS_PER_PAGE, user.getId(), req);
        } else if (userClass == TaxiDriver.class) {
            pagination = displayTaxiDriverOrders(currentPage, ControllerConstants.ITEMS_PER_PAGE, user.getId(), req);
        } else if (userClass == Administrator.class) {
            pagination = displayAllOrders(currentPage, ControllerConstants.ITEMS_PER_PAGE);
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        req.setAttribute("orders", pagination.getData());
        req.setAttribute("currentPage", pagination.getCurrentPage());
        req.setAttribute("lastPage", pagination.getLastPage());

        req.getRequestDispatcher("/orders").forward(req, resp);
    }

    private Pagination displayAllOrders(int currentPage, int itemsPerPage) {
         return ControllerConstants.orderService.getAllOrdersInRang(currentPage, itemsPerPage);
    }

    private Pagination displayTaxiDriverOrders(int currentPage, int itemsPerPage, int taxiDriverId, HttpServletRequest req) {
        req.setAttribute("activeOrder", ControllerConstants.orderService.getActiveOrderByTaxiDriverId(taxiDriverId));
        return ControllerConstants.orderService.getOrdersByTaxiDriverIdInRange(currentPage, itemsPerPage, taxiDriverId);
    }

    private Pagination displayCustomerOrders(int currentPage, int itemsPerPage, int customerId, HttpServletRequest req) {
        req.setAttribute("activeOrder", ControllerConstants.orderService.getActiveOrderByTaxiDriverId(customerId));
        return ControllerConstants.orderService.getOrdersByCustomerIdInRange(currentPage, itemsPerPage, customerId);
    }
}
