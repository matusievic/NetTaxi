package by.tc.web.controller.control.command.impl;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.domain.user.User;
import by.tc.web.service.user.UserService;
import by.tc.web.service.user.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerDisplayingCommand implements ControllerCommand {
    private static final String ID_PARAM = "id";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter(ID_PARAM);

        int id = 0;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Incorrect driver id");
            req.getRequestDispatcher("/500").forward(req, resp);
            return;
        }

        UserService service = UserServiceFactory.getInstance().getCustomerService();
        User taxiDriver = service.get(id);
        req.setAttribute("customer", taxiDriver);
        req.getRequestDispatcher("administrator/customer/display").forward(req, resp);
    }
}
