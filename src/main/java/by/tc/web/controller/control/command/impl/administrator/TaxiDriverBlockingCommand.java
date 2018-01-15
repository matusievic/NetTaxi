package by.tc.web.controller.control.command.impl.administrator;

import by.tc.web.controller.control.command.CommandProvider;
import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.controller.control.command.impl.AbstractCommand;
import by.tc.web.service.converter.Converter;
import by.tc.web.service.user.UserService;
import by.tc.web.service.user.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TaxiDriverBlockingCommand extends AbstractCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter(ControllerConstants.ID_PARAM);
        Optional<Integer> id = Converter.parseInt(idParam);

        if (id.isPresent()) {
            UserService service = UserServiceFactory.getInstance().getTaxiDriverService();
            service.block(id.get());
            CommandProvider.takeCommand("display_taxidriver").execute(req, resp);
        } else {
            req.setAttribute("error", "Incorrect id");
            req.getRequestDispatcher("/404").forward(req, resp);
        }
    }
}