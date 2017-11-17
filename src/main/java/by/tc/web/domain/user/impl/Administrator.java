package by.tc.web.domain.user.impl;

import by.tc.web.domain.user.User;

import java.util.Objects;

public class Administrator extends User {
    private String email;

    public Administrator() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        if (!super.equals(o)) { return false; }

        Administrator administrator = (Administrator) o;

        return Objects.equals(email, administrator.email);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
