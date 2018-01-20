package by.tc.web.service.user.impl;

import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.order.OrderDAO;
import by.tc.web.dao.user.UserDAO;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.user.AbstractUserService;

public class TaxiDriverService extends AbstractUserService {
    private static final UserDAO dao = (UserDAO) DAOFactory.getInstance().getTaxiDriverDAO();
    private static final int DRIVERS_COUNT = 5;

    @Override
    public Object get(int userId) {
        User taxiDriver = null;
        try {
            taxiDriver = dao.readById(userId);
        } catch (DAOException e) {
            //TODO
        }
        return taxiDriver;
    }

    @Override
    public void block(int userId) {
        TaxiDriver taxiDriver = null;
        try {
            taxiDriver = (TaxiDriver) dao.readById(userId);
            taxiDriver.setBanned(true);
            dao.update(taxiDriver);
        } catch (DAOException e) {
            //TODO
        }
    }

    @Override
    public void unblock(int userId) {
        TaxiDriver taxiDriver = null;
        try {
            taxiDriver = (TaxiDriver) dao.readById(userId);
            taxiDriver.setBanned(false);
            dao.update(taxiDriver);
        } catch (DAOException e) {
            //TODO
        }
    }

    @Override
    public User[] getByLocation(float x, float y) {
        if (x < 0 || y < 0) {
            return new User[0];
        }

        User[] result = null;
        try {
            result = dao.readByLocation(x, y, 5);
        } catch (DAOException e) {
            //TODO
        }

        for (User user : result) {
            user.setPassword(new char[0]);
        }

        return result;
    }

    @Override
    public void discount(int userId, float discount) {
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

    @Override
    public void changeRating(int userId, byte rating) {
        TaxiDriver taxiDriver = (TaxiDriver) get(userId);
        float currentRating = taxiDriver.getRating();
        OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getOrderDAO();

        int denominator = 0;
        try {
            denominator = orderDAO.readLengthOfRatedOrdersByTaxiDriverId(userId);
        } catch (DAOException e) {
            //TODO
        }

        float newRating = (currentRating + rating) / denominator;
        taxiDriver.setRating(newRating);

        update(taxiDriver);
    }

    @Override
    protected UserDAO getDAO() {
        return dao;
    }
}
