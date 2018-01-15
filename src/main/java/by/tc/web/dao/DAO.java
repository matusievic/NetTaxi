package by.tc.web.dao;

import by.tc.web.dao.exception.DAOException;

public interface DAO<T> {
    void create(T order) throws DAOException;
    T readById(int id) throws DAOException;
    T[] readInRange(int begin, int end) throws DAOException;
    int readLength() throws DAOException;
    void update(T order) throws DAOException;
    void delete(T order) throws DAOException;
}
