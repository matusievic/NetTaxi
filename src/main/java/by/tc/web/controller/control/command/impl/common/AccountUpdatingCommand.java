package by.tc.web.controller.control.command.impl.common;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.domain.car.builder.CarBuilder;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.encoder.Encoder;
import by.tc.web.service.encoder.EncoderFactory;
import by.tc.web.service.user.UserService;
import by.tc.web.service.validator.AccountValidator;
import by.tc.web.service.validator.CarValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AccountUpdatingCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        Class userClass = user.getClass();
        UserService service = null;

        if (userClass == Customer.class) {
            service = ControllerConstants.customerService;
        } else if (userClass == TaxiDriver.class) {
            service = ControllerConstants.taxiDriverService;

            String carNumber = req.getParameter(ControllerConstants.CAR_NUMBER_PARAM);
            if (!CarValidator.isNumberValid(carNumber)) {
                displayError("Please provide a correct car number", req, resp);
                return;
            }

            String carModel = req.getParameter(ControllerConstants.CAR_MODEL_PARAM);
            if (!CarValidator.isModelValid(carModel)) {
                displayError("Please provide a correct car model", req, resp);
                return;
            }

            ((TaxiDriver) user).setCar(new CarBuilder().number(carNumber.toCharArray()).model(carModel).build());

            String tariff = req.getParameter(ControllerConstants.TARIFF_PARAM);
            if (!AccountValidator.isTariffValid(tariff)) {
                displayError("Please provide a correct tariff", req, resp);
                return;
            }

            ((TaxiDriver) user).setTariff(Float.parseFloat(tariff));

        } else if (userClass == Administrator.class) {
            service = ControllerConstants.administratorService;
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        String name = req.getParameter(ControllerConstants.NAME_PARAM);
        if (!AccountValidator.isNameValid(name)) {
            displayError("Please provide a correct name", req, resp);
            return;
        }
        user.setName(name);

        String surname = req.getParameter(ControllerConstants.SURNAME_PARAM);
        if (!AccountValidator.isSurnameValid(surname)) {
            displayError("Please provide a correct surname", req, resp);
            return;
        }
        user.setSurname(surname);

        String phone = req.getParameter(ControllerConstants.PHONE_PARAM);
        if (!AccountValidator.isPhoneValid(phone)) {
            displayError("Please provide a correct phone", req, resp);
            return;
        }
        user.setPhone(Long.parseLong(phone));

        String oldPassword = req.getParameter(ControllerConstants.OLD_PASSWORD_PARAM);
        if (!oldPassword.equals("")) {
            Encoder encoder = EncoderFactory.getInstance().getEncoder();

            char[] oldEncryptedPassword = encoder.encode(oldPassword);
            if (oldEncryptedPassword == null) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            if (Arrays.equals(oldEncryptedPassword, user.getPassword())) {
                String firstPassword = req.getParameter(ControllerConstants.FIRST_PASSWORD_PARAM);
                String secondPassword = req.getParameter(ControllerConstants.SECOND_PASSWORD_PARAM);

                if (!AccountValidator.isPasswordsValid(firstPassword, secondPassword)) {
                    displayError("Please provide correct passwords", req, resp);
                    return;
                }

                char[] encryptedPassword = encoder.encode(firstPassword);
                if (encryptedPassword == null) {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    return;
                }

                user.setPassword(encryptedPassword);
            } else {
                displayError("Please enter a correct current password", req, resp);
                return;
            }
        }

        service.update(user);
        resp.sendRedirect("/account");
    }

    private void displayError(String error, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ControllerConstants.ERROR, error);
        req.getRequestDispatcher("/account/edit").forward(req, resp);
    }
}
