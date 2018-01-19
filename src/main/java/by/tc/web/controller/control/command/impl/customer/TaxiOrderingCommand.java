package by.tc.web.controller.control.command.impl.customer;

import by.tc.web.controller.control.command.CommandProvider;
import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.order.Order;
import by.tc.web.domain.order.OrderStatus;
import by.tc.web.domain.order.builder.OrderBuilder;
import by.tc.web.domain.point.Point;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaxiOrderingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute("user");
        int taxiDriverId = Converter.parseInt(req.getParameter(ControllerConstants.ID_PARAM)).orElse(-1);

        if (taxiDriverId < 0 || user == null || user.getClass() != Customer.class) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        float beginX = Converter.parseFloat(req.getParameter(ControllerConstants.BEGIN_X_PARAM)).orElse(0F),
              beginY = Converter.parseFloat(req.getParameter(ControllerConstants.BEGIN_Y_PARAM)).orElse(0F),
              price = Converter.parseFloat(req.getParameter(ControllerConstants.PRICE_PARAM)).orElse(0F),
              endX = Converter.parseFloat(req.getParameter(ControllerConstants.END_X_PARAM)).orElse(0F),
              endY = Converter.parseFloat(req.getParameter(ControllerConstants.END_Y_PARAM)).orElse(0F);

        Order order = new OrderBuilder().price(price).begin(new Point(beginX, beginY)).end(new Point(endX, endY))
                          .customerId(user.getId()).taxiDriverId(taxiDriverId).status(OrderStatus.NEW).build();

        ControllerConstants.orderService.add(order);
    }
}
