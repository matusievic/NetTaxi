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
import org.apache.log4j.Logger;

/**
 * This class aimed to authenticate users
 */
public final class Authenticator {
    private static final Logger logger = Logger.getLogger(Authenticator.class);
    private static final DAO<Customer> customerDAO = DAOFactory.getInstance().getCustomerDAO();
    private static final DAO<TaxiDriver> driverDAO = DAOFactory.getInstance().getTaxiDriverDAO();
    private static final DAO<Administrator> administratorDAO = DAOFactory.getInstance().getAdministratorDAO();
    private static final Encoder encoder = EncoderFactory.getInstance().getEncoder();

    /**
     * This method gets a user by specified phone and password
     * @param phone a user phone
     * @param password a user password
     * @return a corresponding user or null if incorrect data was provided
     */
    public static User authenticate(long phone, String password) {
        char[] encodedPassword = encoder.encode(password);
        if (encodedPassword == null) {
            logger.error("Cannot get encoded password -> encoder returned a null value");
        }

        try {
            User customer = customerDAO.readByPhoneAndPassword(phone, encodedPassword);
            if (customer != null) {
                return customer;
            }

            User driver = driverDAO.readByPhoneAndPassword(phone, encodedPassword);
            if (driver != null) {
                return driver;
            }

            User administrator = administratorDAO.readByPhoneAndPassword(phone, encodedPassword);
            if (administrator != null) {
                return administrator;
            }
        } catch (DAOException e) {
            logger.error("Cannot authenticate -> DAO layer thrown an exception", e);
        }

        return null;
    }
}
