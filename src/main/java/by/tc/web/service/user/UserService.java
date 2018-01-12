package by.tc.web.service.user;

import by.tc.web.domain.user.User;

public interface UserService {
    User get(int userId);
    void block(int userId);
    void unblock(int userId);
    void discount(int userId, float discount);
}
