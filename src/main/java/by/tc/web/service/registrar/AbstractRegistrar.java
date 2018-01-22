package by.tc.web.service.registrar;

import by.tc.web.dao.DAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.user.User;
import by.tc.web.service.encoder.Encoder;
import by.tc.web.service.encoder.EncoderFactory;
import by.tc.web.service.exception.ServiceException;
import org.apache.log4j.Logger;

public abstract class AbstractRegistrar implements UserRegistrar {
    private final Logger logger = Logger.getLogger(getClass());
    private final DAO<User> userDAO = getDAO();
    private static final EncoderFactory encoderFactory = EncoderFactory.getInstance();

    @Override
    public void register(User user) throws ServiceException {
        Encoder encoder = encoderFactory.getEncoder();
        try {
            char[] encodedPassword = encoder.encode(String.valueOf(user.getPassword()));
            if (encodedPassword == null) {
                logger.error("Cannot encode the password -> encoder returned a null result");
                throw new ServiceException("Cannot register user -> encoding error");
            }

            user.setPassword(encodedPassword);
            userDAO.create(user);
        } catch (DAOException e) {
            logger.error("Cannot register a user -> DAO layer thrown an exception", e);
            throw new ServiceException(e);
        }
    }

    protected abstract <T extends User> DAO<T> getDAO();
}
