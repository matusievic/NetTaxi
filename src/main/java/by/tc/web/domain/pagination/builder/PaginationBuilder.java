package by.tc.web.domain.pagination.builder;

import by.tc.web.domain.pagination.Pagination;

public final class PaginationBuilder<T> {
    private T[] data;
    private int currentPage;
    private int lastPage;
    private int itemsPerPage;

    public PaginationBuilder data(T[] data) {
        this.data = data;
        return this;
    }

    public PaginationBuilder currentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public PaginationBuilder lastPage(int lastPage) {
        this.lastPage = lastPage;
        return this;
    }

    public PaginationBuilder itemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
        return this;
    }

    public Pagination build() {
        return new Pagination(data, currentPage, lastPage, itemsPerPage);
    }
}
