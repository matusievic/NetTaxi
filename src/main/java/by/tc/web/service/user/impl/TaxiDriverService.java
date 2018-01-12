package by.tc.web.service.user.impl;

import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.user.UserDAO;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.user.UserService;

public class TaxiDriverService implements UserService {
    private static final UserDAO dao = DAOFactory.getInstance().getTaxiDriverDAO();

    @Override
    public User get(int userId) {
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
    public void discount(int userId, float discount) {}
}
