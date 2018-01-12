package by.tc.web.controller.control.command.impl;

import by.tc.web.controller.control.command.CommandProvider;
import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.service.user.UserService;
import by.tc.web.service.user.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerDiscountingCommand implements ControllerCommand {
    private static final String ID_PARAM = "id";
    private static final String DISCOUNT_PARAM = "discount";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter(ID_PARAM);
        String discountParam = req.getParameter(DISCOUNT_PARAM);

        int id = 0;
        float discount = 0;
        try {
            id = Integer.parseInt(idParam);
            discount = Float.parseFloat(discountParam);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Incorrect input data");
            CommandProvider.takeCommand("display_customer").execute(req, resp);
            return;
        }

        UserService service = UserServiceFactory.getInstance().getCustomerService();
        service.discount(id, discount);
        CommandProvider.takeCommand("display_customer").execute(req, resp);
    }
}
