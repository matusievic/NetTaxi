package by.tc.web.controller.control.command.impl;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.builder.impl.CustomerBuilder;
import by.tc.web.service.registrar.RegistrarFactory;
import by.tc.web.service.registrar.UserRegistrar;
import by.tc.web.service.registrar.exception.RegistrarException;
import by.tc.web.service.validator.AccountValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerRegistrationCommand implements ControllerCommand {
    private static final Logger logger = Logger.getLogger(CustomerRegistrationCommand.class);
    private static final String PHONE_PARAM = "phone";
    private static final String NAME_PARAM = "name";
    private static final String SURNAME_PARAM = "surname";
    private static final String FIRST_PASSWORD_PARAM = "first_password";
    private static final String SECOND_PASSWORD_PARAM = "second_password";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String phone = req.getParameter(PHONE_PARAM);
        if (!AccountValidator.isPhoneValid(phone)) {
            req.setAttribute("error", "Please provide a valid phone number");
            req.getRequestDispatcher("/signup");
        }

        String name = req.getParameter(NAME_PARAM);
        if (!AccountValidator.isNameValid(name)) {
            req.setAttribute("error", "Please provide a valid name");
            req.getRequestDispatcher("/signup");
        }

        String surname = req.getParameter(SURNAME_PARAM);
        if (!AccountValidator.isSurnameValid(surname)) {
            req.setAttribute("error", "Please provide a valid surname");
            req.getRequestDispatcher("/signup");
        }

        String firstPassword = req.getParameter(FIRST_PASSWORD_PARAM);
        String secondPassword = req.getParameter(SECOND_PASSWORD_PARAM);
        if (!AccountValidator.isPasswordsValid(firstPassword, secondPassword)) {
            req.setAttribute("error", "Please provide provide valid passwords");
            req.getRequestDispatcher("/signup");
        }

        User customer = new CustomerBuilder(0).phone(Long.parseLong(phone)).name(name).surname(surname)
                        .password(firstPassword.toCharArray()).build();

        try {
            UserRegistrar registrar = RegistrarFactory.getInstance().createCustomerRegistrar();
            registrar.register(customer);
            req.getSession().setAttribute("user", customer);
            resp.sendRedirect("index");
        } catch (RegistrarException e) {
            logger.error("Cannot register customer: " + e.getMessage(), e);
            req.setAttribute("error", "An error has occurred. Please try again later.");
            req.getRequestDispatcher("/signup");
        }
    }
}
