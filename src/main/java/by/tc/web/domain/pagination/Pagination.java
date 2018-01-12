package by.tc.web.domain.pagination;

public final class Pagination {
    private final Object[] data;
    private final int currentPage;
    private final int lastPage;
    private final int itemsPerPage;

    public Pagination(Object[] data, int currentPage, int lastPage, int itemsPerPage) {
        this.data = data;
        this.currentPage = currentPage;
        this.lastPage = lastPage;
        this.itemsPerPage = itemsPerPage;
    }

    public Object[] getData() {
        return data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }
}
