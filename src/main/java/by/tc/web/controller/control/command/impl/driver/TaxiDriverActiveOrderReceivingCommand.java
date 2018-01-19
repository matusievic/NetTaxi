package by.tc.web.controller.control.command.impl.driver;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.order.Order;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.TaxiDriver;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaxiDriverActiveOrderReceivingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute(ControllerConstants.USER_PARAM);

        if (user == null || user.getClass() != TaxiDriver.class) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        Order order = ControllerConstants.orderService.getActiveOrderByTaxiDriverId(user.getId());
        String json = new Gson().toJson(order);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
