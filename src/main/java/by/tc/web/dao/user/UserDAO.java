package by.tc.web.dao.user;

import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.user.User;

public interface UserDAO {
    void create(User user) throws DAOException;
    User[] read() throws DAOException;
    User readByPhoneAndPassword(long phone, char[] password) throws DAOException;
    void update(User user) throws DAOException;
    void delete(User user) throws DAOException;
}
