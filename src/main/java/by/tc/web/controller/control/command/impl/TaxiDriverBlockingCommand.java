package by.tc.web.controller.control.command.impl;

import by.tc.web.controller.control.command.CommandProvider;
import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.service.user.UserService;
import by.tc.web.service.user.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaxiDriverBlockingCommand implements ControllerCommand {
    private static final String ID_PARAM = "id";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter(ID_PARAM);

        int id = 0;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Incorrect id");
            req.getRequestDispatcher("/404").forward(req, resp);
            return;
        }

        UserService service = UserServiceFactory.getInstance().getTaxiDriverService();
        service.block(id);

        CommandProvider.takeCommand("display_taxidriver").execute(req, resp);
    }
}
