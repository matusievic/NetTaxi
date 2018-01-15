package by.tc.web.service.order.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.order.OrderDAO;
import by.tc.web.domain.order.Order;
import by.tc.web.domain.order.OrderStatus;
import by.tc.web.service.order.OrderService;

public class OrderServiceImpl implements OrderService {
    private static final DAO<Order> dao = DAOFactory.getInstance().getOrderDAO();

    @Override
    public void add(Order order) {
        try {
            if (order != null) {
                dao.create(order);
            }
        } catch (DAOException e) {
            //TODO
        }
    }

    @Override
    public Order get(int orderId) {
        Order order = null;
        try {
            order = dao.readById(orderId);
        } catch (DAOException e) {
            //TODO
        }
        return order;
    }

    @Override
    public void changeStatus(int id, OrderStatus status) {
        Order order = null;
        try {
            order = dao.readById(id);
            order.setStatus(status);
            dao.update(order);
        } catch (DAOException e) {
            //TODO
        }
    }

    @Override
    public void update(Order order) {
        try {
            if (order != null) {
                dao.update(order);
            }
        } catch (DAOException e) {
            //TODO
        }
    }

    @Override
    public void delete(Order order) {
        try {
            if (order != null) {
                dao.delete(order);
            }
        } catch (DAOException e) {
            //TODO
        }
    }
}
