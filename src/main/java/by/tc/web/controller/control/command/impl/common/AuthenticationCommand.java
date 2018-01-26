package by.tc.web.controller.control.command.impl.common;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.user.User;
import by.tc.web.service.authenticator.Authenticator;
import by.tc.web.service.validator.AccountValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String phone = req.getParameter(ControllerConstants.PHONE_PARAM);
        String password = req.getParameter(ControllerConstants.PASSWORD_PARAM);

        if (!AccountValidator.isPhoneValid(phone)) {
            displayError("Please provide a valid password", req, resp);
            return;
        }

        if (!AccountValidator.isPasswordValid(password)) {
            displayError("Please provide a valid password", req, resp);
            return;
        }

        User user = Authenticator.authenticate(Long.parseLong(phone), password);

        if (user != null) {
            String lang = (String) session.getAttribute(ControllerConstants.LOCALE_PARAM);
            session.invalidate();
            session = req.getSession();
            session.setAttribute(ControllerConstants.USER_PARAM, user);
            session.setAttribute(ControllerConstants.LOCALE_PARAM, lang);
            resp.sendRedirect("/account");
        } else {
            displayError("Incorrect login/password combination", req, resp);
        }
    }

    private void displayError(String error, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ControllerConstants.ERROR, error);
        req.getRequestDispatcher("/signin").forward(req, resp);
    }
}
