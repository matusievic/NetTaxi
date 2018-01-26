package by.tc.web.service.user;

import by.tc.web.domain.pagination.Pagination;
import by.tc.web.domain.point.Point;
import by.tc.web.domain.user.User;

/**
 * This interface defines User methods
 */
public interface UserService {
    /**
     * This method gets a User from the storage using the corresponding DAO method
     * @param userId a user identifier
     * @return a user, null otherwise
     */
    User get(int userId);

    /**
     * This method blocks user with specified identifier.
     * Or throws an UnsupportedOperationException when trying to invoke this method on Administrator.
     * @param userId an id for blocking
     */
    void block(int userId);

    /**
     * This method unblocks user with specified identifier.
     * Throws an UnsupportedOperationException when trying to invoke this method on Administrator.
     * @param userId an id for unblocking
     */
    void unblock(int userId);

    /**
     * This method get all users by indexes in range [begin; end]
     * @param currentPage page
     * @param itemsPerPage the quantity of users in Pagination
     * @return a Pagination of users
     */
    Pagination<User> getAllInRange(int currentPage, int itemsPerPage);

    /**
     * This method get an array of the nearest users.
     * Or throws an UnsupportedOperationException when trying to invoke this method on non-TaxiDriver class.
     * @param x the fist coordinate
     * @param y the second coordinate
     * @return the array of nearest users
     */
    User[] getByLocation(float x, float y);

    /**
     * This method changes a location of specified user.
     * Or throws an UnsupportedOperationException when trying to invoke this method on non-TaxiDriver class.
     * @param userId a user identifier
     * @param location the new location
     */
    void changeLocation(int userId, Point location);

    /**
     * This method offers a discount to specified user.
     * Or throws an UnsupportedOperationException when trying to invoke this method on non-Customer class.
     * @param userId a user identifier
     * @param discount the new discount
     */
    void discount(int userId, float discount);

    /**
     * This method updates a User in the storage using the corresponding DAO method
     * @param user a user for updating
     */
    void update(User user);

    /**
     * This method deletes a User from the storage using the corresponding DAO method
     * @param user a user for deleting
     */
    void delete(User user);

    /**
     * This method changes the rating of a User by its identifier
     * Throws an UnsupportedOperationException when trying to invoke this method on non-TaxiDriver class.
     * @param userId a user identifier
     * @param rating the new rating
     */
    void changeRating(int userId, byte rating);

    /**
     * This method changes free field value of TaxiDriver class instance.
     * Throws an UnsupportedOperationException when trying to invoke this method on non-TaxiDriver class.
     * @param userId a user identifier
     * @param free the new value for free field
     */
    void setFree(int userId, boolean free);
}
