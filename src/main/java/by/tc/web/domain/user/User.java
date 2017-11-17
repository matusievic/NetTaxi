package by.tc.web.domain.user;

import java.util.Objects;

public abstract class User {
    private String name;
    private String surname;
    private String login;
    private String password;

    public User() {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }

        User user = (User) obj;

        if (!Objects.equals(name, user.name)) { return false; }
        if (!Objects.equals(surname, user.surname)) { return false; }
        if (Objects.equals(login, user.login)) { return false; }
        if (Objects.equals(password, user.password)) { return false; }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(surname);
        result = 31 * result + Objects.hashCode(login);
        result = 31 * result + Objects.hashCode(password);
        return result;
    }
}
