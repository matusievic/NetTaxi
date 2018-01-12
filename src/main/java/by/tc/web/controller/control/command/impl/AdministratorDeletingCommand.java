package by.tc.web.controller.control.command.impl;

import by.tc.web.controller.control.command.CommandProvider;
import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.service.encryptor.Encryptor;
import by.tc.web.service.encryptor.EncryptorFactory;
import by.tc.web.service.encryptor.exception.EncryptorException;
import by.tc.web.service.user.UserService;
import by.tc.web.service.user.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AdministratorDeletingCommand implements ControllerCommand {
    private static final String PASSWORD_PARAM = "password";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Administrator administrator = (Administrator) req.getSession().getAttribute("user");

        String password = req.getParameter(PASSWORD_PARAM);
        if (!password.equals("")) {
            Encryptor encryptor = EncryptorFactory.getInstance().createEncryptor();

            char[] encryptedPassword = null;
            try {
                encryptedPassword = encryptor.encrypt(password);
            } catch (EncryptorException e) {
                req.setAttribute("error", "Internal server error");
                resp.sendRedirect("/500");
                return;
            }

            if (Arrays.equals(encryptedPassword, administrator.getPassword())) {
                UserService service = UserServiceFactory.getInstance().getAdministratorService();
                service.delete(administrator);
                CommandProvider.takeCommand("close_session").execute(req, resp);
            }
        }

        req.setAttribute("error", "Please provide a valid password");
        req.getRequestDispatcher("/administrator/account/delete").forward(req, resp);
    }
}
