package by.tc.web.controller.control.command.impl.driver;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.order.OrderStatus;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderAcceptingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute(ControllerConstants.USER_PARAM);
        int id = Converter.parseInt(req.getParameter(ControllerConstants.ID_PARAM)).orElse(-1);

        if (id < 0 || user == null || user.getClass() != TaxiDriver.class) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        ControllerConstants.orderService.changeStatus(id, OrderStatus.ACCEPTED);
    }
}
