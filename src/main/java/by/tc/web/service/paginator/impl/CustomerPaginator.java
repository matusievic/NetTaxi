package by.tc.web.service.paginator.impl;

import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.user.UserDAO;
import by.tc.web.domain.pagination.Pagination;
import by.tc.web.domain.pagination.builder.PaginationBuilder;
import by.tc.web.service.paginator.Paginator;

public final class CustomerPaginator implements Paginator {
    private static final UserDAO dao = DAOFactory.getInstance().getCustomerDAO();

    @Override
    public Pagination paginate(int currentPage, int itemsPerPage) {
        final int begin = (currentPage - 1) * itemsPerPage;
        final int end = begin + itemsPerPage;

        Pagination result = null;

        try {
            Object[] data = dao.readInRange(begin, end);

            float temp = dao.readLength() / itemsPerPage;
            int lastPage = (int) temp;
            if (temp % 2 != 0) {
                lastPage++;
            }

            result = new PaginationBuilder().data(data).currentPage(currentPage).
                    lastPage(lastPage).itemsPerPage(itemsPerPage).build();
        } catch (DAOException e) {
            //TODO
        }

        return result;
    }
}
