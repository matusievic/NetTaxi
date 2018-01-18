package by.tc.web.service.user;

import by.tc.web.domain.pagination.Pagination;
import by.tc.web.domain.user.User;

public interface UserService {
    Object get(int userId);
    void block(int userId);
    void unblock(int userId);
    Pagination<User> getAllInRange(int currentPage, int itemsPerPage);
    User[] getByLocation(float x, float y);
    void discount(int userId, float discount);
    void update(User user);
    void delete(User user);
}
