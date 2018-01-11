package by.tc.web.domain.user.builder;

import by.tc.web.domain.user.User;

public abstract class UserBuilder {
    protected final int id;
    protected long phone;
    protected String name;
    protected String surname;
    protected char[] password;

    public UserBuilder(int id) {
        this.id = id;
    }

    public UserBuilder phone(long phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder password(char[] password) {
        this.password = password;
        return this;
    }

    public abstract User build();
}
