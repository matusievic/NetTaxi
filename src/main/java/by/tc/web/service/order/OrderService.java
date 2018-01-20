package by.tc.web.service.order;

import by.tc.web.domain.order.Order;
import by.tc.web.domain.order.OrderStatus;
import by.tc.web.domain.pagination.Pagination;

public interface OrderService {
    void add(Order order);
    Order get(int orderId);
    Pagination<Order> getAllOrdersInRange(int currentPage, int itemsPerPage);
    Pagination<Order> getOrdersByCustomerIdInRange(int currentPage, int itemsPerPage, int customerId);
    Pagination<Order> getOrdersByTaxiDriverIdInRange(int currentPage, int itemsPerPage, int taxiDriverId);
    Order getActiveOrderByTaxiDriverId(int taxiDriverId);
    Order getActiveOrderByCustomerId(int customerId);
    void changeStatus(int id, OrderStatus status);
    void rate(int orderId, byte rating);
    void update(Order order);
    void delete(Order order);
}
