package by.tc.web.service.order;

import by.tc.web.service.order.impl.OrderServiceImpl;

public final class OrderServiceFactory {
    private static final OrderServiceFactory instance = new OrderServiceFactory();
    private final OrderService orderService = new OrderServiceImpl();

    public OrderServiceFactory() {}

    public static OrderServiceFactory getInstance() {
        return instance;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
