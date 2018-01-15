package by.tc.web.controller.control.command.impl.administrator;

import by.tc.web.controller.control.command.CommandProvider;
import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.order.Order;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderDeletingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(ControllerConstants.USER_PARAM);
        int id = Converter.parseInt(req.getParameter(ControllerConstants.ID_PARAM)).orElse(-1);

        if (id < 0 || user == null || user.getClass() != Administrator.class) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        Order order = ControllerConstants.orderService.get(id);
        ControllerConstants.orderService.delete(order);
        CommandProvider.takeCommand("display_orders").execute(req, resp);
    }
}
