package by.tc.web.dao;

import by.tc.web.dao.order.OrderDAO;
import by.tc.web.dao.order.impl.MySQLOrderDAO;
import by.tc.web.dao.user.impl.AdministratorDAO;
import by.tc.web.dao.user.impl.CustomerDAO;
import by.tc.web.dao.user.impl.TaxiDriverDAO;
import by.tc.web.dao.user.UserDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private UserDAO customerDAO = new CustomerDAO();
    private UserDAO taxiDriverDAO = new TaxiDriverDAO();
    private UserDAO administratorDAO = new AdministratorDAO();
    private OrderDAO orderDAO = new MySQLOrderDAO();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getCustomerDAO() {
        return customerDAO;
    }

    public UserDAO getTaxiDriverDAO() {
        return taxiDriverDAO;
    }

    public UserDAO getAdministratorDAO() {
        return administratorDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }
}
