package by.tc.web.dao;

import by.tc.web.dao.order.impl.MySQLOrderDAO;
import by.tc.web.dao.user.impl.AdministratorDAO;
import by.tc.web.dao.user.impl.CustomerDAO;
import by.tc.web.dao.user.impl.TaxiDriverDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private DAO customerDAO = new CustomerDAO();
    private DAO taxiDriverDAO = new TaxiDriverDAO();
    private DAO administratorDAO = new AdministratorDAO();
    private DAO orderDAO = new MySQLOrderDAO();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public DAO getCustomerDAO() {
        return customerDAO;
    }

    public DAO getTaxiDriverDAO() {
        return taxiDriverDAO;
    }

    public DAO getAdministratorDAO() {
        return administratorDAO;
    }

    public DAO getOrderDAO() {
        return orderDAO;
    }
}
