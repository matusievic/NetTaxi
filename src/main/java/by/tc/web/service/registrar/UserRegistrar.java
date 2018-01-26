package by.tc.web.service.registrar;

import by.tc.web.domain.user.User;
import by.tc.web.service.exception.ServiceException;

/**
 * This interface defines methods for User registration
 */
public interface UserRegistrar {
    /**
     * This method register user in the application
     * @param user a new user
     * @throws ServiceException if cannot register user
     */
    void register(User user) throws ServiceException;
}
