package by.tc.web.domain.user.impl;

import by.tc.web.domain.user.User;

public class Customer extends User {
    private static final long serialVersionUID = -3707270676089648600L;
    private boolean banned;
    private float discount;

    public Customer() {}

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
