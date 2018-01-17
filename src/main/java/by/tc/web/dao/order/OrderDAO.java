package by.tc.web.dao.order;

import by.tc.web.dao.DAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.order.Order;

public interface OrderDAO extends DAO<Order> {
    void create(Order order) throws DAOException;
    Order readById(int id) throws DAOException;
    Order[] readInRange(int begin, int end) throws DAOException;
    Order[] readByTaxiDriverInRange(int taxiDriverId, int begin, int end) throws DAOException;
    Order[] readByCustomerInRange(int customerId, int begin, int end) throws DAOException;
    int readLength() throws DAOException;
    void update(Order order) throws DAOException;
    void delete(Order order) throws DAOException;
}
