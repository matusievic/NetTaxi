package by.tc.web.controller.control.command.impl;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.service.encryptor.Encryptor;
import by.tc.web.service.encryptor.EncryptorFactory;
import by.tc.web.service.encryptor.exception.EncryptorException;
import by.tc.web.service.user.UserService;
import by.tc.web.service.user.UserServiceFactory;
import by.tc.web.service.validator.AccountValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AdministratorUpdatingCommand implements ControllerCommand {
    private static final String NAME_PARAM = "name";
    private static final String SURNAME_PARAM = "surname";
    private static final String PHONE_PARAM = "phone";
    private static final String OLD_PASSWORD_PARAM = "oldPassword";
    private static final String FIRST_PASSWORD_PARAM = "firstPassword";
    private static final String SECOND_PASSWORD_PARAM = "secondPassword";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Administrator administrator = (Administrator) req.getSession().getAttribute("user");

        if (administrator == null) {
            req.setAttribute("error", "Internal server error");
            req.getRequestDispatcher("/500").forward(req, resp);
            return;
        }

        String name = req.getParameter(NAME_PARAM);
        if (!AccountValidator.isNameValid(name)) {
            req.setAttribute("error", "Incorrect name");
            req.getRequestDispatcher("/administrator/account/edit").forward(req, resp);
            return;
        }
        administrator.setName(name);

        String surname = req.getParameter(SURNAME_PARAM);
        if (!AccountValidator.isSurnameValid(surname)) {
            req.setAttribute("error", "Incorrect surname");
            req.getRequestDispatcher("/administrator/account/edit").forward(req, resp);
            return;
        }
        administrator.setSurname(surname);

        String phone = req.getParameter(PHONE_PARAM);
        if (!AccountValidator.isPhoneValid(phone)) {
            req.setAttribute("error", "Incorrect phone");
            req.getRequestDispatcher("/administrator/account/edit").forward(req, resp);
            return;
        }
        administrator.setPhone(Long.parseLong(phone));

        String oldPassword = req.getParameter(OLD_PASSWORD_PARAM);
        if (!oldPassword.equals("")) {
            Encryptor encryptor = EncryptorFactory.getInstance().createEncryptor();

            char[] oldEncryptedPassword = null;

            try {
                oldEncryptedPassword = encryptor.encrypt(oldPassword);
            } catch (EncryptorException e) {
                req.setAttribute("error", "Internal server error");
                resp.sendRedirect("/500");
                return;
            }

            if (Arrays.equals(oldEncryptedPassword, administrator.getPassword())) {
                String firstPassword = req.getParameter(FIRST_PASSWORD_PARAM);
                String secondPassword = req.getParameter(SECOND_PASSWORD_PARAM);

                if (!AccountValidator.isPasswordsValid(firstPassword, secondPassword)) {
                    req.setAttribute("error", "Incorrect passwords");
                    req.getRequestDispatcher("/administrator/account/edit").forward(req, resp);
                    return;
                }

                char[] encryptedPassword = null;

                try {
                    encryptedPassword = encryptor.encrypt(firstPassword);
                } catch (EncryptorException e) {
                    req.setAttribute("error", "Internal server error");
                    resp.sendRedirect("/500");
                    return;
                }

                administrator.setPassword(encryptedPassword);
            } else {
                req.setAttribute("error", "Enter correct current password");
                req.getRequestDispatcher("/administrator/account/edit").forward(req, resp);
                return;
            }
        }

        UserService service = UserServiceFactory.getInstance().getAdministratorService();
        service.update(administrator);
        resp.sendRedirect("/administrator/account");
    }
}
