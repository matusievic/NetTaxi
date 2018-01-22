package by.tc.web.service.registrar;

import by.tc.web.domain.user.User;
import by.tc.web.service.exception.ServiceException;

public interface UserRegistrar {
    void register(User user) throws ServiceException;
}
