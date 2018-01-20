package by.tc.web.domain.user.impl;

import by.tc.web.domain.car.Car;
import by.tc.web.domain.point.Point;
import by.tc.web.domain.user.User;

import java.util.Objects;

public class TaxiDriver extends User {
    private static final long serialVersionUID = -7105690319962626166L;
    private boolean banned;
    private Car car;
    private float rating;
    private boolean free;
    private Point location;
    private float tariff;

    public TaxiDriver() {}

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public float getTariff() {
        return tariff;
    }

    public void setTariff(float tariff) {
        this.tariff = tariff;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        if (!super.equals(obj)) { return false; }

        TaxiDriver taxiDriver = (TaxiDriver) obj;
        if (!Objects.equals(car, taxiDriver.car)) { return false; }
        if (rating != taxiDriver.rating) { return false; }
        if (free != taxiDriver.free) { return false; }
        if (!Objects.equals(location, taxiDriver.location)) { return false; }
        if (tariff != taxiDriver.tariff) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(car);
        result = 31 * result + (int) rating;
        result = 31 * result + Boolean.hashCode(free);
        result = 31 * result + Objects.hashCode(location);
        result = 31 * result + (int) tariff;
        return result;
    }
}
