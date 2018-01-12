package by.tc.web.service.registrar.impl;

import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.user.UserDAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.user.User;
import by.tc.web.service.encryptor.Encryptor;
import by.tc.web.service.encryptor.EncryptorFactory;
import by.tc.web.service.encryptor.exception.EncryptorException;
import by.tc.web.service.registrar.UserRegistrar;
import by.tc.web.service.registrar.exception.RegistrarException;
import org.apache.log4j.Logger;

public class CustomerRegistrar implements UserRegistrar {
    private static final Logger logger = Logger.getLogger(CustomerRegistrar.class);
    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final EncryptorFactory encryptorFactory = EncryptorFactory.getInstance();

    @Override
    public void register(User user) throws RegistrarException {
        UserDAO userDAO = daoFactory.getCustomerDAO();
        Encryptor encryptor = encryptorFactory.createEncryptor();
        try {
            char[] encryptedPassword = encryptor.encrypt(String.valueOf(user.getPassword()));
            user.setPassword(encryptedPassword);
            userDAO.create(user);
        } catch (DAOException e) {
            logger.error("Cannot register a user", e);
            throw new RegistrarException(e);
        } catch (EncryptorException e) {
            logger.error("Cannot encrypt the password", e);
            throw new RegistrarException(e);
        }
    }
}
