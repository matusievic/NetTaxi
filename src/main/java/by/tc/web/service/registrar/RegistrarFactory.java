package by.tc.web.service.registrar;

import by.tc.web.service.registrar.impl.CustomerRegistrar;
import by.tc.web.service.registrar.impl.TaxiDriverRegistrar;

public final class RegistrarFactory {
    private static final RegistrarFactory instance = new RegistrarFactory();

    private final UserRegistrar customerRegistrar = new CustomerRegistrar();
    private final UserRegistrar taxiDriverRegistrar = new TaxiDriverRegistrar();

    private RegistrarFactory() {}

    public static RegistrarFactory getInstance() {
        return instance;
    }

    public UserRegistrar createCustomerRegistrar() {
        return customerRegistrar;
    }

    public UserRegistrar createTaxiDriverRegistrar() {
        return taxiDriverRegistrar;
    }
}
