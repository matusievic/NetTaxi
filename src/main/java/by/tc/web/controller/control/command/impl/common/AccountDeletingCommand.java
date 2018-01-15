package by.tc.web.controller.control.command.impl.common;

import by.tc.web.controller.control.command.CommandProvider;
import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.encoder.Encoder;
import by.tc.web.service.encoder.EncoderFactory;
import by.tc.web.service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AccountDeletingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(ControllerConstants.USER_PARAM);

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }


        UserService service;
        Class userClass = user.getClass();

        if (userClass == Customer.class) {
            service = ControllerConstants.customerService;
        } else if (userClass == TaxiDriver.class) {
            service = ControllerConstants.taxiDriverService;
        } else if (userClass == Administrator.class) {
            service = ControllerConstants.administratorService;
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        String password = req.getParameter(ControllerConstants.PASSWORD_PARAM);
        if (!password.equals("")) {
            Encoder encoder = EncoderFactory.getInstance().createEncryptor();

            char[] encryptedPassword = encryptedPassword = encoder.encrypt(password);
            if (encryptedPassword == null) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            if (Arrays.equals(encryptedPassword, user.getPassword())) {
                service.delete(user);
                CommandProvider.takeCommand("close_session").execute(req, resp);
            }
        }

        req.setAttribute("error", "Please provide a valid password");
        req.getRequestDispatcher("/account/delete").forward(req, resp);
    }
}
