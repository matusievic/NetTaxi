package by.tc.web.service.paginator;

import by.tc.web.domain.pagination.Pagination;
import by.tc.web.domain.pagination.builder.PaginationBuilder;

public abstract class AbstractPaginator implements Paginator {
    @Override
    public Pagination paginate(int currentPage, int itemsPerPage) {
        final int begin = (currentPage - 1) * itemsPerPage + 1;
        final int end = begin + itemsPerPage - 1;

        Pagination result = null;

        Object[] data = this.getData(begin, end);

        float lastPage = (float) this.getLength() / itemsPerPage;
        if (lastPage != (int) lastPage) {
            lastPage++;
        }

        result = new PaginationBuilder().data(data).currentPage(currentPage).
                lastPage((int) lastPage).itemsPerPage(itemsPerPage).build();

        return result;
    }

    protected abstract Object[] getData(int begin, int end, Object... params);

    protected abstract int getLength();
}
