package by.tc.web.controller.control.command.impl.customer;

import by.tc.web.controller.control.command.ControllerCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderTaxiPageDisplayingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/order").forward(req, resp);
    }
}
