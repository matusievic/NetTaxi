package by.tc.web.service.user.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.service.user.UserService;

public class CustomerService implements UserService {
    private static final DAO<User> dao = DAOFactory.getInstance().getCustomerDAO();

    @Override
    public Object get(int userId) {
        Object customer = null;
        try {
            customer = dao.readById(userId);
        } catch (DAOException e) {
            //TODO
        }
        return customer;
    }

    @Override
    public void block(int userId) {
        Customer customer = null;
        try {
            customer = (Customer) dao.readById(userId);
            customer.setBanned(true);
            dao.update(customer);
        } catch (DAOException e) {
            //TODO
        }
    }

    @Override
    public void unblock(int userId) {
        Customer customer = null;
        try {
            customer = (Customer) dao.readById(userId);
            customer.setBanned(false);
            dao.update(customer);
        } catch (DAOException e) {
            //TODO
        }
    }

    @Override
    public void discount(int userId, float discount) {
        Customer customer = (Customer) this.get(userId);
        customer.setDiscount(discount);
        try {
            dao.update(customer);
        } catch (DAOException e) {
            //TODO
        }
    }

    @Override
    public void update(User user) {
        try {
            if (user != null) {
                dao.update(user);
            }
        } catch (DAOException e) {
            //TODO
        }
    }

    @Override
    public void delete(User user) {
        try {
            if (user != null) {
                dao.delete(user);
            }
        } catch (DAOException e) {
            //TODO
        }
    }
}
