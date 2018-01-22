package by.tc.web.service.registrar.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.service.registrar.AbstractRegistrar;

public final class CustomerRegistrar extends AbstractRegistrar {
    private static final DAO<Customer> dao = DAOFactory.getInstance().getCustomerDAO();

    @Override
    protected DAO<Customer> getDAO() {
        return dao;
    }
}
