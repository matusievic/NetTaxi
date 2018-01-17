package by.tc.web.dao.user;

import by.tc.web.dao.DAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.user.User;

public interface UserDAO extends DAO<User> {
    void create(User user) throws DAOException;
    //User[] read() throws DAOException;
    User readById(int id) throws DAOException;
    User[] readInRange(int begin, int end) throws DAOException;
    User readByPhoneAndPassword(long phone, char[] password) throws DAOException;
    int readLength() throws DAOException;
    void update(User user) throws DAOException;
    void delete(User user) throws DAOException;
}
