package by.tc.web.controller.impl;

import by.tc.web.controller.ControllerCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalizationCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession(true).setAttribute("locale", req.getParameter("locale"));
        req.getRequestDispatcher(req.getParameter("from")).forward(req, resp);
    }
}
