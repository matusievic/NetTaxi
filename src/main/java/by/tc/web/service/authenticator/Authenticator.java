package by.tc.web.service.authenticator;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.encoder.Encoder;
import by.tc.web.service.encoder.EncoderFactory;

public final class Authenticator {
    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final EncoderFactory ENCODER_FACTORY = EncoderFactory.getInstance();

    public static User authenticate(long phone, String password) {
        Encoder encoder = ENCODER_FACTORY.createEncryptor();

        char[] encryptedPassword = encoder.encrypt(password);
        if (encryptedPassword == null) {
            //TODO
        }


        DAO<Customer> customerDAO = daoFactory.getCustomerDAO();
        DAO<TaxiDriver> driverDAO = daoFactory.getTaxiDriverDAO();
        DAO<Administrator> administratorDAO = daoFactory.getAdministratorDAO();

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
