package by.tc.web.controller.control.command.impl;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.authenticator.Authenticator;
import by.tc.web.service.validator.AccountValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationCommand implements ControllerCommand {
    private static final String PHONE_PARAM = "phone";
    private static final String PASSWORD_PARAM = "password";
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String phone = req.getParameter(PHONE_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);

        if (!AccountValidator.isPhoneValid(phone)) {
            req.setAttribute("error", "Please provide a valid password");
            req.getRequestDispatcher("/signin").forward(req, resp);
            return;
        }

        if (!AccountValidator.isPasswordValid(password)) {
            req.setAttribute("error", "Please provide a valid password");
            req.getRequestDispatcher("/signin").forward(req, resp);
            return;
        }

        User user = Authenticator.authenticate(Long.parseLong(phone), password);

        if (user != null) {
            session.invalidate();
            session = req.getSession();
            session.setAttribute("user", user);
            if (user.getClass() == Customer.class) {
                resp.sendRedirect("/customer/account");
            } else if (user.getClass() == TaxiDriver.class) {
                resp.sendRedirect("/driver/account");
            } else if (user.getClass() == Administrator.class) {
                resp.sendRedirect("/administrator/account");
            } else {
                resp.sendRedirect("/500");
            }
        } else {
            req.setAttribute("error", "Incorrect login/password combination");
            req.getRequestDispatcher("/signin").forward(req, resp);
        }
    }
}
