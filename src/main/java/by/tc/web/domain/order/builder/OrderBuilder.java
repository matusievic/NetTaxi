package by.tc.web.domain.order.builder;

import by.tc.web.domain.order.Order;
import by.tc.web.domain.order.OrderStatus;
import by.tc.web.domain.point.Point;

public class OrderBuilder {
    private int id;
    private float price;
    private Point begin;
    private Point end;
    private int customerId;
    private int taxiDriverId;
    private OrderStatus status;
    private byte rating;

    public OrderBuilder() {
    }

    public OrderBuilder(int id) {
        this.id = id;
    }

    public OrderBuilder price(float price) {
        this.price = price;
        return this;
    }

    public OrderBuilder begin(Point begin) {
        this.begin = begin;
        return this;
    }

    public OrderBuilder end(Point end) {
        this.end = end;
        return this;
    }

    public OrderBuilder customerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderBuilder taxiDriverId(int taxiDriverId) {
        this.taxiDriverId = taxiDriverId;
        return this;
    }

    public OrderBuilder status(OrderStatus status) {
        this.status = status;
        return this;
    }

    public OrderBuilder rating(byte rating) {
        this.rating = rating;
        return this;
    }

    public Order build() {
        Order order = new Order();
        order.setId(this.id);
        order.setPrice(this.price);
        order.setBegin(this.begin);
        order.setEnd(this.end);
        order.setCustomerId(this.customerId);
        order.setTaxiDriverId(this.taxiDriverId);
        order.setStatus(this.status);
        order.setRating(this.rating);
        return order;
    }
}
