package by.tc.web.service.user.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.order.Order;
import by.tc.web.domain.point.Point;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.user.AbstractUserService;

public class TaxiDriverService extends AbstractUserService {
    private static final DAO<TaxiDriver> dao = DAOFactory.getInstance().getTaxiDriverDAO();
    private static final int DRIVERS_COUNT = 5;

    @Override
    public void block(int userId) {
        TaxiDriver taxiDriver = null;
        try {
            taxiDriver = (TaxiDriver) dao.readById(userId);
            taxiDriver.setBanned(true);
            dao.update(taxiDriver);
        } catch (DAOException e) {
            logger.error("Cannot block user -> DAO layer thrown an exception", e);
        }
    }

    @Override
    public void unblock(int userId) {
        TaxiDriver taxiDriver = null;
        try {
            taxiDriver = dao.readById(userId);
            taxiDriver.setBanned(false);
            dao.update(taxiDriver);
        } catch (DAOException e) {
            logger.error("Cannot unblock user -> DAO layer thrown an exception", e);
        }
    }

    @Override
    public User[] getByLocation(float x, float y) {
        if (x < 0 || y < 0) {
            return new User[0];
        }

        User[] result = null;
        try {
            result = dao.readByLocation(x, y, 5).toArray(new User[0]);
        } catch (DAOException e) {
            logger.error("Cannot get user by location -> DAO layer thrown an exception", e);
            return null;
        }

        for (User user : result) {
            user.setPassword(new char[0]);
        }

        return result;
    }


    @Override
    public void changeRating(int userId, byte rating) {
        TaxiDriver taxiDriver = (TaxiDriver) get(userId);
        float currentRating = taxiDriver.getRating();
        DAO<Order> orderDAO = DAOFactory.getInstance().getOrderDAO();

        int denominator = 0;
        try {
            denominator = orderDAO.readLengthOfRatedOrdersByTaxiDriverId(userId);
        } catch (DAOException e) {
            logger.error("Cannot change user rating -> DAO layer thrown an exception", e);
            return;
        }

        float newRating = (currentRating + rating) / denominator;
        taxiDriver.setRating(newRating);

        update(taxiDriver);
    }

    @Override
    public void setFree(int userId, boolean free) {
        TaxiDriver taxiDriver = null;
        try {
            taxiDriver = dao.readById(userId);
            taxiDriver.setFree(free);
            dao.update(taxiDriver);
        } catch (DAOException e) {
            logger.error("Cannot change user free field -> DAO layer thrown an exception", e);
        }
    }

    @Override
    protected DAO<TaxiDriver> getDAO() {
        return dao;
    }

    @Override
    public void changeLocation(int userId, Point location) {
        TaxiDriver taxiDriver = (TaxiDriver) get(userId);
        taxiDriver.setLocation(location);
        update(taxiDriver);
    }
}
