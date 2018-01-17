package by.tc.web.domain.order;

import by.tc.web.domain.point.Point;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {
    private static final long serialVersionUID = 5386841813313718658L;

    private int id;
    private float price;
    private Point begin;
    private Point end;
    private int customerId;
    private int taxiDriverId;
    private OrderStatus status;
    private byte rating;

    public Order() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Point getBegin() {
        return begin;
    }

    public void setBegin(Point begin) {
        this.begin = begin;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTaxiDriverId() {
        return taxiDriverId;
    }

    public void setTaxiDriverId(int taxiDriverId) {
        this.taxiDriverId = taxiDriverId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }

        Order order = (Order) obj;
        if (price != order.price) { return false; }
        if (begin != order.begin) { return false; }
        if (end != order.end) { return false; }
        if (status != order.status) { return false; }
        if (customerId != order.customerId) { return false; }
        if (taxiDriverId != order.taxiDriverId) { return false; }
        if (rating != order.rating) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = id;
        hash = 31 * hash + (int) price;
        hash = 31 * hash + Objects.hashCode(begin);
        hash = 31 * hash + Objects.hashCode(end);
        hash = 31 * hash + Objects.hashCode(status);
        hash = 31 * hash + customerId;
        hash = 31 * hash + taxiDriverId;
        hash = 31 * hash + rating;
        return hash;
    }
}
