package by.tc.web.domain.user.impl;

import by.tc.web.domain.user.User;

public class Customer extends User {
    private static final long serialVersionUID = -3707270676089648600L;
    private boolean baned;
    private float discount;

    public Customer() {}

    public boolean isBaned() {
        return baned;
    }

    public void setBaned(boolean baned) {
        this.baned = baned;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
