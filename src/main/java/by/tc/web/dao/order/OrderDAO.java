package by.tc.web.dao.order;

import by.tc.web.dao.DAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.order.Order;

public interface OrderDAO extends DAO<Order> {
    void create(Order order) throws DAOException;
    Order readById(int id) throws DAOException;
    Order[] readInRange(int begin, int itemsPerPage) throws DAOException;
    Order[] readByTaxiDriverInRange(int taxiDriverId, int begin, int itemsPerPage) throws DAOException;
    Order[] readByCustomerInRange(int customerId, int begin, int itemsPerPage) throws DAOException;
    Order readActiveOrderByTaxiDriverId(int driverId);
    Order readActiveOrderByCustomerId(int customerId);
    int readLength() throws DAOException;
    int readLengthByTaxiDriverId(int taxiDriverId) throws DAOException;
    int readLengthOfRatedOrdersByTaxiDriverId(int taxiDriverId) throws DAOException;
    int readLengthByCustomerId(int customerId) throws DAOException;
    void update(Order order) throws DAOException;
    void delete(Order order) throws DAOException;
}
