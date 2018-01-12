package by.tc.web.controller.control.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerCommand {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
