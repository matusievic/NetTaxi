package by.tc.web.domain.user.builder.impl;

import by.tc.web.domain.user.builder.UserBuilder;
import by.tc.web.domain.user.impl.Customer;

public class CustomerBuilder extends UserBuilder {
    private boolean baned;
    private float discount;

    public CustomerBuilder(int id) {
        super(id);
    }

    public CustomerBuilder baned(boolean baned) {
        this.baned = baned;
        return this;
    }

    public CustomerBuilder discount(float discount) {
        this.discount = discount;
        return this;
    }

    @Override
    public Customer build() {
        Customer customer = new Customer();
        customer.setId(super.id);
        customer.setPhone(phone);
        customer.setName(super.name);
        customer.setSurname(super.surname);
        customer.setPassword(super.password);
        customer.setBaned(this.baned);
        customer.setDiscount(this.discount);
        return customer;
    }

    @Override
    public CustomerBuilder phone(long phone) {
        super.phone(phone);
        return this;
    }

    @Override
    public CustomerBuilder name(String name) {
        super.name(name);
        return this;
    }

    @Override
    public CustomerBuilder surname(String surname) {
        super.surname(surname);
        return this;
    }

    @Override
    public CustomerBuilder password(char[] password) {
        super.password(password);
        return this;
    }
}
