package by.tc.web.dao;

import by.tc.web.dao.exception.DAOException;

import java.util.List;

public interface DAO<T> {
    // Common methods
    void create(T order) throws DAOException;
    T readById(int id) throws DAOException;
    List<T> readInRange(int begin, int end) throws DAOException;
    int readLength() throws DAOException;
    void update(T order) throws DAOException;
    void delete(T order) throws DAOException;

    // Order methods
    List<T> readByTaxiDriverInRange(int taxiDriverId, int begin, int itemsPerPage) throws DAOException;
    List<T> readByCustomerInRange(int customerId, int begin, int itemsPerPage) throws DAOException;
    T readActiveOrderByTaxiDriverId(int driverId) throws DAOException;
    T readActiveOrderByCustomerId(int customerId) throws DAOException;
    int readLengthByTaxiDriverId(int taxiDriverId) throws DAOException;
    int readLengthOfRatedOrdersByTaxiDriverId(int taxiDriverId) throws DAOException;
    int readLengthByCustomerId(int customerId) throws DAOException;

    // User methods
    T readByPhoneAndPassword(long phone, char[] password) throws DAOException;
    List<T> readByLocation(float x, float y, int count) throws DAOException;
}
