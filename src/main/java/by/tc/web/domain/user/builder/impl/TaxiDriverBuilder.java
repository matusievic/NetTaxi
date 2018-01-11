package by.tc.web.domain.user.builder.impl;

import by.tc.web.domain.car.Car;
import by.tc.web.domain.user.builder.UserBuilder;
import by.tc.web.domain.user.impl.TaxiDriver;

public class TaxiDriverBuilder extends UserBuilder {
    private Car car;
    private boolean baned;

    public TaxiDriverBuilder(int id) {
        super(id);
    }

    public TaxiDriverBuilder car(Car car) {
        this.car = car;
        return this;
    }

    public TaxiDriverBuilder baned(boolean baned) {
        this.baned = baned;
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
        taxiDriver.setBaned(this.baned);
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
