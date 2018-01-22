package by.tc.web.service.user;

import by.tc.web.dao.DAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.pagination.Pagination;
import by.tc.web.domain.pagination.builder.PaginationBuilder;
import by.tc.web.domain.point.Point;
import by.tc.web.domain.user.User;
import org.apache.log4j.Logger;

public abstract class AbstractUserService implements UserService {
    protected final Logger logger = Logger.getLogger(getClass());

    @Override
    public User get(int userId) {
        User user = null;
        try {
            user = getDAO().readById(userId);
        } catch (DAOException e) {
            logger.error("Cannot get user -> DAO layer thrown an exception");
        }
        return user;
    }

    @Override
    public Pagination<User> getAllInRange(int currentPage, int itemsPerPage) {
        final int begin = (currentPage - 1) * itemsPerPage + 1;
        final int end = begin + itemsPerPage - 1;

        User[] data = null;
        DAO<User> dao = this.getDAO();

        float lastPage = 0;
        try {
            data = dao.readInRange(begin, end).toArray(new User[0]);
            lastPage = (float) dao.readLength() / itemsPerPage;
            if (lastPage != (int) lastPage) {
                lastPage++;
            }

        } catch (DAOException e) {
            logger.error("Cannot prepare pagination -> DAO layer thrown an exception", e);
            return null;
        }

        Pagination<User> result = new PaginationBuilder<User>().data(data).
                currentPage(currentPage).lastPage((int) lastPage).
                itemsPerPage(itemsPerPage).build();

        return result;
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
    public void changeRating(int userId, byte rating) {}

    @Override
    public void discount(int userId, float discount) {}

    @Override
    public void changeLocation(int userId, Point location) {}

    @Override
    public void update(User user) {
        try {
            if (user != null) {
                getDAO().update(user);
            }
        } catch (DAOException e) {
            logger.error("Cannot update user -> DAO layer thrown an exception", e);
        }
    }

    @Override
    public void delete(User user) {
        try {
            if (user != null) {
                getDAO().delete(user);
            }
        } catch (DAOException e) {
            logger.error("Cannot delete user -> DAO layer thrown an exception", e);
        }
    }

    protected abstract <T extends User> DAO<T> getDAO();

}
