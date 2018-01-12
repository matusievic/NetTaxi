package by.tc.web.service.authenticator;

import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.user.UserDAO;
import by.tc.web.domain.user.User;
import by.tc.web.service.encryptor.Encryptor;
import by.tc.web.service.encryptor.EncryptorFactory;
import by.tc.web.service.encryptor.exception.EncryptorException;

public final class Authenticator {
    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final EncryptorFactory encryptorFactory = EncryptorFactory.getInstance();

    public static User authenticate(long phone, String password) {
        Encryptor encryptor = encryptorFactory.createEncryptor();

        char[] encryptedPassword = null;
        try {
            encryptedPassword = encryptor.encrypt(password);
        } catch (EncryptorException e) {
            //TODO
        }

        UserDAO customerDAO = daoFactory.getCustomerDAO();
        UserDAO driverDAO = daoFactory.getTaxiDriverDAO();
        UserDAO administratorDAO = daoFactory.getAdministratorDAO();

        try {
            User customer = customerDAO.readByPhoneAndPassword(phone, encryptedPassword);
            if (customer != null) {
                return customer;
            }

            User driver = driverDAO.readByPhoneAndPassword(phone, encryptedPassword);
            if (driver != null) {
                return driver;
            }

            User administrator = administratorDAO.readByPhoneAndPassword(phone, encryptedPassword);
            if (administrator != null) {
                return administrator;
            }
        } catch (DAOException e) {
            return null;
        }

        return null;
    }
}
