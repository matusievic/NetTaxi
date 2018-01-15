package by.tc.web.service.paginator;

import by.tc.web.dao.DAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.pagination.Pagination;
import by.tc.web.domain.pagination.builder.PaginationBuilder;

public abstract class AbstractPaginator implements Paginator {
    private DAO dao;

    @Override
    public Pagination paginate(int currentPage, int itemsPerPage) {
        final int begin = (currentPage - 1) * itemsPerPage + 1;
        final int end = begin + itemsPerPage - 1;

        Pagination result = null;

        this.dao = getDAO();

        try {
            Object[] data = dao.readInRange(begin, end);

            float lastPage = (float) dao.readLength() / itemsPerPage;
            if (lastPage != (int) lastPage) {
                lastPage++;
            }

            result = new PaginationBuilder().data(data).currentPage(currentPage).
                    lastPage((int)lastPage).itemsPerPage(itemsPerPage).build();
        } catch (DAOException e) {
            //TODO
        }

        return result;
    }

    protected abstract DAO getDAO();
}
