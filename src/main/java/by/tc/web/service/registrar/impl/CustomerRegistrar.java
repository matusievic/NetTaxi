package by.tc.web.service.registrar.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.user.User;
import by.tc.web.service.encoder.Encoder;
import by.tc.web.service.encoder.EncoderFactory;
import by.tc.web.service.registrar.UserRegistrar;
import by.tc.web.service.registrar.exception.RegistrarException;
import org.apache.log4j.Logger;

public class CustomerRegistrar implements UserRegistrar {
    private static final Logger logger = Logger.getLogger(CustomerRegistrar.class);
    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final EncoderFactory encoderFactory = EncoderFactory.getInstance();

    @Override
    public void register(User user) throws RegistrarException {
        DAO userDAO = daoFactory.getCustomerDAO();
        Encoder encoder = encoderFactory.createEncryptor();
        try {
            char[] encryptedPassword = encoder.encrypt(String.valueOf(user.getPassword()));
            if (encryptedPassword == null) {
                logger.error("Cannot encrypt the password");
                throw new RegistrarException("Cannot register user");
            }

            user.setPassword(encryptedPassword);
            userDAO.create(user);
        } catch (DAOException e) {
            logger.error("Cannot register a user", e);
            throw new RegistrarException(e);
        }
    }
}
