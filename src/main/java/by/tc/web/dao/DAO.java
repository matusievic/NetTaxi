package by.tc.web.dao;

import by.tc.web.dao.exception.DAOException;

import java.util.List;

/**
 * An interface that represents all possible DAO-methods
 * @param <T>
 */
public interface DAO<T> {
    /**
     * This method save an object of type T to the database
     * @param object an object for saving
     * @throws DAOException if cannot save object to the database
     */
    void create(T object) throws DAOException;

    /**
     * This method get an object of type T from the corresponding database
     * @param id an object identifier
     * @return an received object
     * @throws DAOException if cannot get object from the database
     */
    T readById(int id) throws DAOException;

    /**
     * This method reads objects of type T in range [begin; end]
     * begin, and - are not table primary keys, they're like indexes in arrays
     * @param begin the index of the first element
     * @param end the index of the last element
     * @return an array of read received objects
     * @throws DAOException if cannot get objects from the database
     */
    List<T> readInRange(int begin, int end) throws DAOException;

    /**
     * This method gets a quantity of object in the corresponding database
     * @return the quantity of objects
     * @throws DAOException if cannot read length from the database
     */
    int readLength() throws DAOException;

    /**
     * This methods updates corresponding table record for object
     * @param object an object for updating
     * @throws DAOException if cannot perform update operation in the database
     */
    void update(T object) throws DAOException;

    /**
     * This methods deletes object from the corresponding database
     * @param object an object for deleting
     * @throws DAOException if cannot perform delete operation in the database
     */
    void delete(T object) throws DAOException;


    /**
     * This methods reads orders, which were performed by a specified taxi driver.
     * For UserDAO classes UnsupportedOperationException is throws.
     * @param taxiDriverId a taxi driver identifier, that performed orders
     * @param begin the index of the first order
     * @param itemsPerPage the index of the last order
     * @return orders in the range [begin; end]
     * @throws DAOException if cannot read orders from the database
     */
    List<T> readByTaxiDriverInRange(int taxiDriverId, int begin, int itemsPerPage) throws DAOException;

    /**
     * This method reads orders, which were made by a specified customer.
     * For UserDAO classes UnsupportedOperationException is throws.
     * @param customerId a customer identifier, that made orders
     * @param begin the index of the first order
     * @param itemsPerPage the index of the last order
     * @return orders in the range [begin; end]
     * @throws DAOException if cannot read orders from the database
     */
    List<T> readByCustomerInRange(int customerId, int begin, int itemsPerPage) throws DAOException;

    /**
     * This method gets a currently active order of specified taxi driver.
     * For UserDAO classes UnsupportedOperationException is throws.
     * @param driverId a taxi driver identifier, that performing an order
     * @return an active order
     * @throws DAOException if cannot read order from the database
     */
    T readActiveOrderByTaxiDriverId(int driverId) throws DAOException;

    /**
     * This method gets a currently active order of specified taxi driver.
     * For UserDAO classes UnsupportedOperationException is throws.
     * @param customerId a customer identifier, that performing an order
     * @return an active order
     * @throws DAOException if cannot read order from the database
     */
    T readActiveOrderByCustomerId(int customerId) throws DAOException;

    /**
     * This method gets a quantity of orders of specified taxi driver.
     * For UserDAO classes UnsupportedOperationException is throws.
     * @param taxiDriverId a taxi driver identifier, that performed an orders
     * @return a quantity of orders
     * @throws DAOException if cannot read order from the database
     */
    int readLengthByTaxiDriverId(int taxiDriverId) throws DAOException;

    /**
     * This method gets a quantity of rated orders of specified taxi driver.
     * For UserDAO classes UnsupportedOperationException is throws.
     * @param taxiDriverId a taxi driver identifier, that performed an orders
     * @return a quantity of rated orders
     * @throws DAOException if cannot read order from the database
     */
    int readLengthOfRatedOrdersByTaxiDriverId(int taxiDriverId) throws DAOException;

    /**
     * This method gets a quantity of orders of specified customer.
     * For UserDAO classes UnsupportedOperationException is throws.
     * @param customerId a customer identifier, that performed an orders
     * @return a quantity of orders
     * @throws DAOException if cannot read order from the database
     */
    int readLengthByCustomerId(int customerId) throws DAOException;


    /**
     * This method gets a user by specified phone and password.
     * For OrderDAO classes UnsupportedOperationException is throws.
     * @param phone a phone
     * @param password a hashed password
     * @return a user or null if incorrect data was provided
     * @throws DAOException if cannot read order from the database
     */
    T readByPhoneAndPassword(long phone, char[] password) throws DAOException;

    /**
     * This method gets users by its location.
     * For non TaxiDriverDAO classes UnsupportedOperationException is throws.
     * @param x a phone
     * @param y a hashed password
     * @return a list of nearest users
     * @throws DAOException if cannot read order from the database
     */
    List<T> readByLocation(float x, float y, int count) throws DAOException;
}
