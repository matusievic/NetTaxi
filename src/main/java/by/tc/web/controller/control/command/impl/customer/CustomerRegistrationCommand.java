package by.tc.web.controller.control.command.impl.customer;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.builder.impl.CustomerBuilder;
import by.tc.web.service.registrar.RegistrarFactory;
import by.tc.web.service.registrar.UserRegistrar;
import by.tc.web.service.exception.ServiceException;
import by.tc.web.service.validator.AccountValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerRegistrationCommand implements ControllerCommand {
    private static final Logger logger = Logger.getLogger(CustomerRegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter(ControllerConstants.PHONE_PARAM);
        if (!AccountValidator.isPhoneValid(phone)) {
            displayError("Please provide a valid phone number", req, resp);
            return;
        }

        String name = req.getParameter(ControllerConstants.NAME_PARAM);
        if (!AccountValidator.isNameValid(name)) {
            displayError("Please provide a valid name", req, resp);
            return;
        }

        String surname = req.getParameter(ControllerConstants.SURNAME_PARAM);
        if (!AccountValidator.isSurnameValid(surname)) {
            displayError("Please provide a valid surname", req, resp);
            return;
        }

        String firstPassword = req.getParameter(ControllerConstants.FIRST_PASSWORD_PARAM);
        String secondPassword = req.getParameter(ControllerConstants.SECOND_PASSWORD_PARAM);
        if (!AccountValidator.isPasswordsValid(firstPassword, secondPassword)) {
            displayError("Please provide provide valid passwords", req, resp);
            return;
        }

        User customer = new CustomerBuilder(0).phone(Long.parseLong(phone)).name(name).surname(surname)
                        .password(firstPassword.toCharArray()).build();

        try {
            UserRegistrar registrar = RegistrarFactory.getInstance().createCustomerRegistrar();
            registrar.register(customer);
            req.getSession().setAttribute("user", customer);
            resp.sendRedirect("index");
        } catch (ServiceException e) {
            logger.error("Cannot register customer: " + e.getMessage(), e);
            displayError("An error has occurred. Please try again later.", req, resp);
            return;
        }
    }

    private void displayError(String error, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ControllerConstants.ERROR, error);
        req.getRequestDispatcher("/signup").forward(req, resp);
    }
}
