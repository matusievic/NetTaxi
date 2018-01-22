package by.tc.web.service.order.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.order.Order;
import by.tc.web.domain.order.OrderStatus;
import by.tc.web.domain.pagination.Pagination;
import by.tc.web.domain.pagination.builder.PaginationBuilder;
import by.tc.web.service.order.OrderService;
import by.tc.web.service.user.UserService;
import by.tc.web.service.user.UserServiceFactory;
import org.apache.log4j.Logger;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);
    private static final DAO<Order> dao = DAOFactory.getInstance().getOrderDAO();

    @Override
    public void add(Order order) {
        try {
            if (order != null) {
                dao.create(order);
            }
        } catch (DAOException e) {
            logger.error("Cannot create an order in db -> DAO layer thrown an exception", e);
        }
    }

    @Override
    public Order get(int orderId) {
        Order order = null;
        try {
            order = dao.readById(orderId);
        } catch (DAOException e) {
            logger.error("Cannot get an order from db -> DAO layer thrown an exception", e);
        }
        return order;
    }

    @Override
    public Pagination<Order> getAllOrdersInRange(int currentPage, int itemsPerPage) {
        final int begin = (currentPage - 1) * itemsPerPage;

        Order[] data = null;
        float lastPage = 0;
        try {
            data = dao.readInRange(begin, itemsPerPage).toArray(new Order[0]);
            lastPage = (float) dao.readLength() / itemsPerPage;
            if (lastPage != (int) lastPage) {
                lastPage++;
            }

        } catch (DAOException e) {
            logger.error("Cannot prepare an orders pagination -> DAO layer thrown an exception", e);
            return null;
        }

        Pagination<Order> result = new PaginationBuilder<Order>().data(data).
                                       currentPage(currentPage).lastPage((int) lastPage).
                                       itemsPerPage(itemsPerPage).build();

        return result;
    }

    @Override
    public Pagination<Order> getOrdersByCustomerIdInRange(int currentPage, int itemsPerPage, int customerId) {
        final int begin = (currentPage - 1) * itemsPerPage;

        Order[] data = null;
        float lastPage = 0;
        try {
            data = dao.readByCustomerInRange(customerId, begin, itemsPerPage).toArray(new Order[0]);
            lastPage = (float) dao.readLengthByCustomerId(customerId) / itemsPerPage;
            if (lastPage != (int) lastPage) {
                lastPage++;
            }

        } catch (DAOException e) {
            logger.error("Cannot prepare an orders pagination -> DAO layer thrown an exception", e);
            return null;
        }

        Pagination<Order> result = new PaginationBuilder<Order>().data(data).
                                       currentPage(currentPage).lastPage((int) lastPage).
                                       itemsPerPage(itemsPerPage).build();

        return result;
    }

    @Override
    public Pagination<Order> getOrdersByTaxiDriverIdInRange(int currentPage, int itemsPerPage, int taxiDriverId) {
        final int begin = (currentPage - 1) * itemsPerPage;

        Order[] data = null;
        float lastPage = 0;
        try {
            data = dao.readByTaxiDriverInRange(taxiDriverId, begin, itemsPerPage).toArray(new Order[0]);
            lastPage = (float) dao.readLengthByTaxiDriverId(taxiDriverId) / itemsPerPage;
            if (lastPage != (int) lastPage) {
                lastPage++;
            }

        } catch (DAOException e) {
            logger.error("Cannot prepare an orders pagination -> DAO layer thrown an exception");
            return null;
        }

        Pagination<Order> result = new PaginationBuilder<Order>().data(data).
                                   currentPage(currentPage).lastPage((int) lastPage).
                                   itemsPerPage(itemsPerPage).build();

        return result;
    }

    @Override
    public Order getActiveOrderByTaxiDriverId(int taxiDriverId) {
        Order order = null;
        try {
            order = dao.readActiveOrderByTaxiDriverId(taxiDriverId);
        } catch (DAOException e) {
            logger.error("Cannot get an active driver order -> DAO layer thrown an exception", e);
        }

        return order;
    }

    @Override
    public Order getActiveOrderByCustomerId(int customerId) {
        Order order = null;
        try {
            order = dao.readActiveOrderByCustomerId(customerId);
        } catch (DAOException e) {
            logger.error("Cannot get an active customer order -> DAO layer thrown an exception", e);
        }
        return order;
    }

    @Override
    public void changeStatus(int id, OrderStatus status) {
        try {
            Order order = dao.readById(id);
            order.setStatus(status);
            dao.update(order);
        } catch (DAOException e) {
            logger.error("Cannot update an order -> DAO layer thrown an exception", e);
        }
    }

    @Override
    public void rate(int orderId, byte rating) {
        Order order = get(orderId);
        order.setRating(rating);
        update(order);

        UserService userService = UserServiceFactory.getInstance().getTaxiDriverService();
        userService.changeRating(order.getTaxiDriverId(), rating);
    }

    @Override
    public void update(Order order) {
        try {
            if (order != null) {
                dao.update(order);
            }
        } catch (DAOException e) {
            logger.error("Cannot update an order -> DAO layer thrown an exception", e);
        }
    }

    @Override
    public void delete(Order order) {
        try {
            if (order != null) {
                dao.delete(order);
            }
        } catch (DAOException e) {
            logger.error("Cannot delete an order -> DAO layer thrown an exception", e);
        }
    }
}
