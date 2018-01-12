package by.tc.web.controller.control.command.impl;

import by.tc.web.controller.control.command.ControllerCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalizationCommand implements ControllerCommand {
    private static final String PREVIOUS_PAGE_PARAM = "from";
    private static final String LOCALE_PARAM = "locale";
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(LOCALE_PARAM, req.getParameter(LOCALE_PARAM));
        req.getRequestDispatcher(req.getParameter(PREVIOUS_PAGE_PARAM)).forward(req, resp);
    }
}
