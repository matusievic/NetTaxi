package by.tc.web.domain.user.builder.impl;

import by.tc.web.domain.car.Car;
import by.tc.web.domain.user.builder.UserBuilder;
import by.tc.web.domain.user.impl.TaxiDriver;

public class TaxiDriverBuilder extends UserBuilder {
    private Car car;
    private boolean banned;
    private float rating;

    public TaxiDriverBuilder(int id) {
        super(id);
    }

    public TaxiDriverBuilder car(Car car) {
        this.car = car;
        return this;
    }

    public TaxiDriverBuilder banned(boolean banned) {
        this.banned = banned;
        return this;
    }

    public TaxiDriverBuilder rating(float rating) {
        this.rating = rating;
        return this;
    }

    @Override
    public TaxiDriver build() {
        TaxiDriver taxiDriver = new TaxiDriver();
        taxiDriver.setPhone(super.phone);
        taxiDriver.setName(super.name);
        taxiDriver.setSurname(super.surname);
        taxiDriver.setPassword(password);
        taxiDriver.setCar(this.car);
        taxiDriver.setBanned(this.banned);
        taxiDriver.setRating(rating);
        return taxiDriver;
    }

    @Override
    public TaxiDriverBuilder phone(long phone) {
        super.phone(phone);
        return this;
    }

    @Override
    public TaxiDriverBuilder name(String name) {
        super.name(name);
        return this;
    }

    @Override
    public TaxiDriverBuilder surname(String surname) {
        super.surname(surname);
        return this;
    }

    @Override
    public TaxiDriverBuilder password(char[] password) {
        super.password(password);
        return this;
    }
}
