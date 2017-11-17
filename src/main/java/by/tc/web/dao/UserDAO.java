package by.tc.web.dao;

import by.tc.web.domain.user.User;

public interface UserDAO {
    User authenticate(String login, String password);
}
