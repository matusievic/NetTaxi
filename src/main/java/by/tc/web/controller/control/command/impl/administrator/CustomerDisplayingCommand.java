package by.tc.web.controller.control.command.impl.administrator;

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

public class CustomerDisplayingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter(ControllerConstants.ID_PARAM);
        Optional<Integer> id = Converter.parseInt(idParam);

        if (id.isPresent()) {
            UserService service = UserServiceFactory.getInstance().getCustomerService();
            Object taxiDriver = service.get(id.get());
            req.setAttribute("customer", taxiDriver);
            req.getRequestDispatcher("administrator/customer/display").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
