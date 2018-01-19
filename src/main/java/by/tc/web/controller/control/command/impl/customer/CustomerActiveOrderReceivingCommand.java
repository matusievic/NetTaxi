package by.tc.web.controller.control.command.impl.customer;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.order.Order;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.domain.user.impl.TaxiDriver;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerActiveOrderReceivingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute(ControllerConstants.USER_PARAM);

        if (user == null || user.getClass() != Customer.class) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        Order order = ControllerConstants.orderService.getActiveOrderByCustomerId(user.getId());
        String orderJson = new Gson().toJson(order);

        TaxiDriver taxiDriver = (TaxiDriver) ControllerConstants.taxiDriverService.get(order.getTaxiDriverId());
        taxiDriver.setPassword(new char[0]);
        String taxiDriverJson = new Gson().toJson(taxiDriver);

        String json = '[' + orderJson + ',' + taxiDriverJson + ']';

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
