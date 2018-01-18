package by.tc.web.service.order.impl;

import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.order.OrderDAO;
import by.tc.web.domain.order.Order;
import by.tc.web.domain.order.OrderStatus;
import by.tc.web.domain.pagination.Pagination;
import by.tc.web.domain.pagination.builder.PaginationBuilder;
import by.tc.web.service.order.OrderService;

public class OrderServiceImpl implements OrderService {
    private static final OrderDAO dao = (OrderDAO) DAOFactory.getInstance().getOrderDAO();

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
    public Pagination<Order> getAllOrdersInRang(int currentPage, int itemsPerPage) {
        final int begin = (currentPage - 1) * itemsPerPage + 1;
        final int end = begin + itemsPerPage - 1;

        Order[] data = null;
        float lastPage = 0;
        try {
            data = dao.readInRange(begin, end);
            lastPage = (float) dao.readLength() / itemsPerPage;
            if (lastPage != (int) lastPage) {
                lastPage++;
            }

        } catch (DAOException e) {
            //TODO
        }

        Pagination<Order> result = new PaginationBuilder<Order>().data(data).
                                       currentPage(currentPage).lastPage((int) lastPage).
                                       itemsPerPage(itemsPerPage).build();

        return result;
    }

    @Override
    public Pagination<Order> getOrdersByCustomerIdInRange(int currentPage, int itemsPerPage, int customerId) {
        final int begin = (currentPage - 1) * itemsPerPage + 1;
        final int end = begin + itemsPerPage - 1;

        Order[] data = null;
        float lastPage = 0;
        try {
            data = dao.readByCustomerInRange(begin, end, customerId);
            lastPage = (float) dao.readLengthByCustomerId(customerId) / itemsPerPage;
            if (lastPage != (int) lastPage) {
                lastPage++;
            }

        } catch (DAOException e) {
            //TODO
        }

        Pagination<Order> result = new PaginationBuilder<Order>().data(data).
                currentPage(currentPage).lastPage((int) lastPage).
                itemsPerPage(itemsPerPage).build();

        return result;
    }

    @Override
    public Pagination<Order> getOrdersByTaxiDriverIdInRange(int currentPage, int itemsPerPage, int taxiDriverId) {
        final int begin = (currentPage - 1) * itemsPerPage + 1;
        final int end = begin + itemsPerPage - 1;

        Order[] data = null;
        float lastPage = 0;
        try {
            data = dao.readByTaxiDriverInRange(begin, end, taxiDriverId);
            lastPage = (float) dao.readLengthByTaxiDriverId(taxiDriverId) / itemsPerPage;
            if (lastPage != (int) lastPage) {
                lastPage++;
            }

        } catch (DAOException e) {
            //TODO
        }

        Pagination<Order> result = new PaginationBuilder<Order>().data(data).
                currentPage(currentPage).lastPage((int) lastPage).
                itemsPerPage(itemsPerPage).build();

        return result;
    }

    @Override
    public Order getActiveOrderByTaxiDriverId(int taxiDriverId) {
        return dao.readActiveOrderByTaxiDriverId(taxiDriverId);
    }

    @Override
    public Order getActiveOrderByCustomerId(int customerId) {
        return dao.readActiveOrderByCustomerId(customerId);
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
