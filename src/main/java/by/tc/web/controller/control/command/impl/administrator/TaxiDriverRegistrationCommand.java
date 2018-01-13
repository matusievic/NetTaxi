package by.tc.web.controller.control.command.impl.administrator;

import by.tc.web.controller.control.command.CommandProvider;
import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.controller.control.command.impl.AbstractCommand;
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

public class TaxiDriverRegistrationCommand extends AbstractCommand implements ControllerCommand {
    private static final Logger logger = Logger.getLogger(TaxiDriverRegistrationCommand.class);

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

        String carNumber = req.getParameter(ControllerConstants.CAR_NUMBER_PARAM);
        if (!CarValidator.isNumberValid(carNumber)) {
            displayError("Please provide a valid car number", req, resp);
            return;
        }

        String carModel = req.getParameter(ControllerConstants.CAR_MODEL_PARAM);
        if (!CarValidator.isModelValid(carModel)) {
            displayError("Please provide a valid car model", req, resp);
            return;
        }

        Car car = new CarBuilder().number(carNumber.toCharArray()).model(carModel).build();
        User taxiDriver = new TaxiDriverBuilder(0).phone(Long.parseLong(phone)).name(name).surname(surname)
                          .password(firstPassword.toCharArray()).car(car).build();

        try {
            UserRegistrar registrar = RegistrarFactory.getInstance().createTaxiDriverRegistrar();
            registrar.register(taxiDriver);
            CommandProvider.takeCommand("display_taxidrivers").execute(req, resp);
        } catch (RegistrarException e) {
            logger.error("Cannot register taxi driver: " + e.getMessage(), e);
            displayError("An error has occurred. Please try again later.", req, resp);
        }
    }

    private void displayError(String error, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ControllerConstants.ERROR, error);
        req.getRequestDispatcher("/administrator/driver/create").forward(req, resp);
    }
}
