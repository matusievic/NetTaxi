package by.tc.web.domain.pagination;

public final class Pagination<T> {
    private final T[] data;
    private final int currentPage;
    private final int lastPage;
    private final int itemsPerPage;

    public Pagination(T[] data, int currentPage, int lastPage, int itemsPerPage) {
        this.data = data;
        this.currentPage = currentPage;
        this.lastPage = lastPage;
        this.itemsPerPage = itemsPerPage;
    }

    public T[] getData() {
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
