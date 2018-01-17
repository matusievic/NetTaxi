package by.tc.web.service.paginator;

import by.tc.web.service.paginator.impl.CustomerPaginator;
import by.tc.web.service.paginator.impl.AdministratorOrderPaginator;
import by.tc.web.service.paginator.impl.TaxiDriverPaginator;

public final class PaginatorFactory {
    private static final PaginatorFactory instance = new PaginatorFactory();
    private final Paginator customerPaginator = new CustomerPaginator();
    private final Paginator taxiDriverPaginator = new TaxiDriverPaginator();
    private final Paginator orderPaginator = new AdministratorOrderPaginator();

    private PaginatorFactory() {}

    public static PaginatorFactory getInstance() {
        return instance;
    }

    public Paginator getCustomerPaginator() {
        return customerPaginator;
    }

    public Paginator getTaxiDriverPaginator() {
        return taxiDriverPaginator;
    }

    public Paginator getOrderPaginator() {
        return orderPaginator;
    }
}
