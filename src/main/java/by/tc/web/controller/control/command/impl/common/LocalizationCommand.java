package by.tc.web.controller.control.command.impl.common;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.controller.control.command.impl.AbstractCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalizationCommand extends AbstractCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(ControllerConstants.LOCALE_PARAM, req.getParameter(ControllerConstants.LOCALE_PARAM));
        req.getRequestDispatcher(req.getParameter(ControllerConstants.PREVIOUS_PAGE_PARAM)).forward(req, resp);
    }
}
