package by.tc.web.service.order;

import by.tc.web.domain.order.Order;
import by.tc.web.domain.order.OrderStatus;

public interface OrderService {
    void add(Order order);
    Order get(int orderId);
    void changeStatus(int id, OrderStatus status);
    void update(Order order);
    void delete(Order order);
}
