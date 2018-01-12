package by.tc.web.controller.control.command.impl;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.domain.car.Car;
import by.tc.web.domain.car.builder.CarBuilder;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.builder.impl.TaxiDriverBuilder;
import by.tc.web.service.registrar.RegistrarFactory;
import by.tc.web.service.registrar.UserRegistrar;
import by.tc.web.service.registrar.exception.RegistrarException;
import by.tc.web.service.validator.AccountValidator;
import by.tc.web.service.validator.CarValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaxiDriverRegistrationCommand implements ControllerCommand {
    private static final Logger logger = Logger.getLogger(CustomerRegistrationCommand.class);
    private static final String PHONE_PARAM = "phone";
    private static final String NAME_PARAM = "name";
    private static final String SURNAME_PARAM = "surname";
    private static final String FIRST_PASSWORD_PARAM = "first_password";
    private static final String SECOND_PASSWORD_PARAM = "second_password";
    private static final String CAR_NUMBER_PARAM = "car_number";
    private static final String CAR_MODEL_PARAM = "car_model";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter(PHONE_PARAM);
        if (!AccountValidator.isPhoneValid(phone)) {
            req.setAttribute("error", "Please provide a valid phone number");
            req.getRequestDispatcher("/signup.jsp");
        }

        String name = req.getParameter(NAME_PARAM);
        if (!AccountValidator.isNameValid(name)) {
            req.setAttribute("error", "Please provide a valid name");
            req.getRequestDispatcher("/signup.jsp");
        }

        String surname = req.getParameter(SURNAME_PARAM);
        if (!AccountValidator.isSurnameValid(surname)) {
            req.setAttribute("error", "Please provide a valid surname");
            req.getRequestDispatcher("/signup.jsp");
        }

        String firstPassword = req.getParameter(FIRST_PASSWORD_PARAM);
        String secondPassword = req.getParameter(SECOND_PASSWORD_PARAM);
        if (!AccountValidator.isPasswordsValid(firstPassword, secondPassword)) {
            req.setAttribute("error", "Please provide provide valid passwords");
            req.getRequestDispatcher("/signup.jsp");
        }

        String carNumber = req.getParameter(CAR_NUMBER_PARAM);
        if (!CarValidator.isNumberValid(carNumber)) {
            req.setAttribute("error", "Please provide a valid car number");
            req.getRequestDispatcher("/signup.jsp");
        }

        String carModel = req.getParameter(CAR_MODEL_PARAM);
        if (!CarValidator.isModelValid(carModel)) {
            req.setAttribute("error", "Please provide a valid car model");
            req.getRequestDispatcher("/signup.jsp");
        }

        Car car = new CarBuilder().number(carNumber.toCharArray()).model(carModel).build();
        User taxiDriver = new TaxiDriverBuilder(0).phone(Long.parseLong(phone)).name(name).surname(surname)
                          .password(firstPassword.toCharArray()).car(car).build();

        try {
            UserRegistrar registrar = RegistrarFactory.getInstance().createCustomerRegistrar();
            registrar.register(taxiDriver);
            req.getSession().setAttribute("user", taxiDriver);
            resp.sendRedirect("index");
        } catch (RegistrarException e) {
            logger.error("Cannot register taxi driver: " + e.getMessage(), e);
            req.setAttribute("error", "An error has occurred. Please try again later.");
            req.getRequestDispatcher("/signup.jsp");
        }
    }
}
