package by.tc.web.domain.user.impl;

import by.tc.web.domain.user.User;

import java.util.Objects;

public class Customer extends User {
    private String phone;

    public Customer() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        if (!super.equals(o)) return false;

        Customer customer = (Customer) o;

        return Objects.equals(phone, customer.phone);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
