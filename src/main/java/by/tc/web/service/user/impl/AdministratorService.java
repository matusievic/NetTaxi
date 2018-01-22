package by.tc.web.service.user.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.service.user.AbstractUserService;

public class AdministratorService extends AbstractUserService {
    private static final DAO<User> dao = DAOFactory.getInstance().getAdministratorDAO();

    @Override
    public Object get(int userId) {
        User administrator = null;
        try {
            administrator = dao.readById(userId);
        } catch (DAOException e) {
            //TODO
        }
        return administrator;
    }

    @Override
    public void block(int userId) {}

    @Override
    public void unblock(int userId) {}

    @Override
    public User[] getByLocation(float x, float y) {
        return new User[0];
    }

    @Override
    public void discount(int userId, float discount) {}

    @Override
    public void update(User user) {
        try {
            if (user != null) {
                dao.update((Administrator) user);
            }
        } catch (DAOException e) {
            //TODO
        }
    }

    @Override
    public void delete(User user) {
        try {
            if (user != null) {
                dao.delete((Administrator) user);
            }
        } catch (DAOException e) {
            //TODO
        }
    }

    @Override
    public void changeRating(int userId, byte rating) {

    }

    @Override
    protected DAO<User> getDAO() {
        return dao;
    }
}
