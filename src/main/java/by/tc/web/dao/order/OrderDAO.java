package by.tc.web.dao.order;

import by.tc.web.dao.DAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.order.Order;

public interface OrderDAO extends by.tc.web.dao.DAO<Order> {
    void create(Order order) throws DAOException;
    Order readById(int id) throws DAOException;
    Order[] readInRange(int begin, int end) throws DAOException;
    int readLength() throws DAOException;
    void update(Order order) throws DAOException;
    void delete(Order order) throws DAOException;
}
