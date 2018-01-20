package by.tc.web.controller.control.command.impl.customer;

import by.tc.web.controller.control.command.CommandProvider;
import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.service.converter.Converter;
import by.tc.web.service.validator.AccountValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderRatingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute(ControllerConstants.USER_PARAM);
        int id = Converter.parseInt(req.getParameter(ControllerConstants.ID_PARAM)).orElse(-1);
        String ratingParam = req.getParameter(ControllerConstants.RATING_PARAM);

        if (!AccountValidator.isRatingValid(ratingParam) || id < 0 || user == null || user.getClass() != Customer.class) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        int rating = Integer.parseInt(ratingParam);

        ControllerConstants.orderService.rate(id, (byte) rating);

        CommandProvider.takeCommand("display_order").execute(req, resp);
    }
}
