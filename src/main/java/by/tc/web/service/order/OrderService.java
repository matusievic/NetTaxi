package by.tc.web.service.order;

import by.tc.web.domain.order.Order;
import by.tc.web.domain.order.OrderStatus;
import by.tc.web.domain.pagination.Pagination;

/**
 * This interface defines Order service methods
 */
public interface OrderService {
    /**
     * This methods adds an order to the storage
     * @param order an order for adding
     */
    void add(Order order);

    /**
     * This method get an order from the storage by its identifier
     * @param orderId an order identifier
     * @return an corresponding order, null otherwise
     */
    Order get(int orderId);

    /**
     * This methods prepares a Pagination of all orders for specified page
     * @param currentPage a page
     * @param itemsPerPage a quantity of orders in Pagination
     * @return a Pagination of orders
     */
    Pagination<Order> getAllOrdersInRange(int currentPage, int itemsPerPage);

    /**
     * This method prepares a Pagination of orders by specified customer for specified page
     * @param currentPage a page
     * @param itemsPerPage a quantity of orders in Pagination
     * @param customerId a customer identifier
     * @return a Pagination of orders
     */
    Pagination<Order> getOrdersByCustomerIdInRange(int currentPage, int itemsPerPage, int customerId);

    /**
     * This method prepares a Pagination of orders by specified taxi driver for specified page
     * @param currentPage a page
     * @param itemsPerPage a quantity of orders in Pagination
     * @param taxiDriverId a taxi driver identifier
     * @return a Pagination of orders
     */
    Pagination<Order> getOrdersByTaxiDriverIdInRange(int currentPage, int itemsPerPage, int taxiDriverId);

    /**
     * This method get active order by specified taxi driver using the corresponding DAO method
     * @param taxiDriverId a taxi driver identifier
     * @return an active Order
     */
    Order getActiveOrderByTaxiDriverId(int taxiDriverId);

    /**
     * This method get active order by specified customer using the corresponding DAO method
     * @param customerId a customer identifier
     * @return an active Order
     */
    Order getActiveOrderByCustomerId(int customerId);

    /**
     * This method changes the status of an order
     * @param id an order identifier
     * @param status the new status for order
     */
    void changeStatus(int id, OrderStatus status);

    /**
     * This method rates an order
     * @param orderId an order identifier
     * @param rating the new rating for order
     */
    void rate(int orderId, byte rating);

    /**
     * This methods updates order using the corresponding DAO method
     * @param order the order for updating
     */
    void update(Order order);

    /**
     * This methods deletes order using the corresponding DAO method
     * @param order the order for deleting
     */
    void delete(Order order);
}
