package by.tc.web.domain.user.builder.impl;

import by.tc.web.domain.user.User;
import by.tc.web.domain.user.builder.UserBuilder;
import by.tc.web.domain.user.impl.Administrator;

public class AdministratorBuilder extends UserBuilder {
    public AdministratorBuilder(int id) {
        super(id);
    }

    @Override
    public Administrator build() {
        Administrator administrator = new Administrator();
        administrator.setId(super.id);
        administrator.setPhone(super.phone);
        administrator.setName(super.name);
        administrator.setSurname(super.surname);
        administrator.setPassword(super.password);
        return administrator;
    }
}
