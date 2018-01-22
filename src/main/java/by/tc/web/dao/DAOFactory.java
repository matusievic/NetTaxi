package by.tc.web.dao;

import by.tc.web.dao.order.impl.MySQLOrderDAO;
import by.tc.web.dao.user.impl.AdministratorDAO;
import by.tc.web.dao.user.impl.CustomerDAO;
import by.tc.web.dao.user.impl.TaxiDriverDAO;
import by.tc.web.domain.order.Order;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.domain.user.impl.TaxiDriver;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private DAO<Customer> customerDAO = new CustomerDAO();
    private DAO<TaxiDriver> taxiDriverDAO = new TaxiDriverDAO();
    private DAO<Administrator> administratorDAO = new AdministratorDAO();
    private DAO<Order> orderDAO = new MySQLOrderDAO();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public DAO<Customer> getCustomerDAO() {
        return customerDAO;
    }

    public DAO<TaxiDriver> getTaxiDriverDAO() {
        return taxiDriverDAO;
    }

    public DAO<Administrator> getAdministratorDAO() {
        return administratorDAO;
    }

    public DAO<Order> getOrderDAO() {
        return orderDAO;
    }
}
