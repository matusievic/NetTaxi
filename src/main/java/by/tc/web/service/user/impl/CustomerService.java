package by.tc.web.service.user.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.service.user.AbstractUserService;

public class CustomerService extends AbstractUserService {
    private static final DAO<Customer> dao = DAOFactory.getInstance().getCustomerDAO();

    @Override
    public void block(int userId) {
        Customer customer = null;
        try {
            customer = dao.readById(userId);
            customer.setBanned(true);
            dao.update(customer);
        } catch (DAOException e) {
            logger.error("Cannot block user -> DAO layer thrown an exception", e);
        }
    }

    @Override
    public void unblock(int userId) {
        Customer customer = null;
        try {
            customer = dao.readById(userId);
            customer.setBanned(false);
            dao.update(customer);
        } catch (DAOException e) {
            logger.error("Cannot unblock user -> DAO layer thrown an exception", e);
        }
    }

    @Override
    public void discount(int userId, float discount) {
        Customer customer = (Customer) this.get(userId);
        customer.setDiscount(discount);
        try {
            dao.update(customer);
        } catch (DAOException e) {
            logger.error("Cannot discount user -> DAO layer thrown an exception", e);
        }
    }

    @Override
    protected DAO<Customer> getDAO() {
        return dao;
    }
}
