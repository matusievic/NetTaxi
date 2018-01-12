package by.tc.web.service.registrar;

import by.tc.web.domain.user.User;
import by.tc.web.service.registrar.exception.RegistrarException;

public interface UserRegistrar {
    void register(User user) throws RegistrarException;
}
