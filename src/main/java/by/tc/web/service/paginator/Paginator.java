package by.tc.web.service.paginator;

import by.tc.web.domain.pagination.Pagination;

public interface Paginator {
    Pagination paginate(int currentPage, int itemsPerPage);
}
