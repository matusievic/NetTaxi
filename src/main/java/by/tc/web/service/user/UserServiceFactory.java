package by.tc.web.service.user;

import by.tc.web.service.user.impl.CustomerService;
import by.tc.web.service.user.impl.TaxiDriverService;

public final class UserServiceFactory {
    private static final UserServiceFactory instance = new UserServiceFactory();
    private final UserService customerService = new CustomerService();
    private final UserService taxiDriverService = new TaxiDriverService();

    private UserServiceFactory() {}

    public static UserServiceFactory getInstance() {
        return instance;
    }

    public UserService getCustomerService() {
        return customerService;
    }

    public UserService getTaxiDriverService() {
        return taxiDriverService;
    }
}
