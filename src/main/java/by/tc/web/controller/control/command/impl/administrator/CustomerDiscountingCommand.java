package by.tc.web.controller.control.command.impl.administrator;

import by.tc.web.controller.control.command.CommandProvider;
import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.service.converter.Converter;
import by.tc.web.service.user.UserService;
import by.tc.web.service.user.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class CustomerDiscountingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter(ControllerConstants.ID_PARAM);
        String discountParam = req.getParameter(ControllerConstants.DISCOUNT_PARAM);

        Optional<Integer> id = Converter.parseInt(idParam);
        Optional<Float> discount = Converter.parseFloat(discountParam);

        if (id.isPresent()) {
            UserService service = UserServiceFactory.getInstance().getCustomerService();
            service.discount(id.get(), discount.get());
            CommandProvider.takeCommand("display_customer").execute(req, resp);
        } else {
            req.setAttribute(ControllerConstants.ERROR, "Incorrect input data");
            CommandProvider.takeCommand("display_customer").execute(req, resp);
        }
    }
}
