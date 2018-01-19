package by.tc.web.controller.control.command.impl.customer;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.user.User;
import by.tc.web.service.converter.Converter;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaxiDriverFindingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float x = Converter.parseFloat(req.getParameter(ControllerConstants.X_PARAM)).orElse(-1F);
        float y = Converter.parseFloat(req.getParameter(ControllerConstants.Y_PARAM)).orElse(-1F);

        User[] drivers = ControllerConstants.taxiDriverService.getByLocation(x, y);
        String result = new Gson().toJson(drivers);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(result);
    }
}
