package by.tc.web.service.user;

import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.user.UserDAO;
import by.tc.web.domain.pagination.Pagination;
import by.tc.web.domain.pagination.builder.PaginationBuilder;
import by.tc.web.domain.user.User;

public abstract class AbstractUserService implements UserService {
    public Pagination<User> getAllInRange(int currentPage, int itemsPerPage) {
        final int begin = (currentPage - 1) * itemsPerPage + 1;
        final int end = begin + itemsPerPage - 1;

        User[] data = null;
        UserDAO dao = this.getDAO();

        float lastPage = 0;
        try {
            data = dao.readInRange(begin, end);
            lastPage = (float) dao.readLength() / itemsPerPage;
            if (lastPage != (int) lastPage) {
                lastPage++;
            }

        } catch (DAOException e) {
            //TODO
        }

        Pagination<User> result = new PaginationBuilder<User>().data(data).
                currentPage(currentPage).lastPage((int) lastPage).
                itemsPerPage(itemsPerPage).build();

        return result;
    }

    protected abstract UserDAO getDAO();
}
