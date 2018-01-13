package by.tc.web.domain.user;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public abstract class User implements Serializable {
    private static final long serialVersionUID = -5863058898809249040L;
    private int id;
    private long phone;
    private String name;
    private String surname;
    private char[] password;

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }

        User user = (User) obj;

        if (Objects.equals(phone, user.phone)) { return false; }
        if (!Objects.equals(name, user.name)) { return false; }
        if (!Objects.equals(surname, user.surname)) { return false; }
        if (Objects.equals(password, user.password)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(phone);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(surname);
        result = 31 * result + Objects.hashCode(password);
        return result;
    }
}
