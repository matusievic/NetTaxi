package by.tc.web.dao.order.impl;

import by.tc.web.dao.AbstractMySQLDAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.order.Order;
import by.tc.web.domain.order.OrderStatus;
import by.tc.web.domain.point.Point;
import by.tc.web.service.database.connection.PooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrderDAO extends AbstractMySQLDAO<Order> {
    private static final String CREATE_QUERY = "INSERT INTO orders (price, begin, end, customers_customer_id, drivers_driver_id, status, rating) VALUE (?, PointFromText('POINT(%s)'), PointFromText('POINT(%s)'), ?, ?, ?, ?);";
    private static final String READ_BY_ID_QUERY = "SELECT order_id, price, X(begin), Y(begin), X(end), Y(end), customers_customer_id, drivers_driver_id, status, rating FROM orders WHERE order_id=?;";
    private static final String READ_IN_RANGE_QUERY = "SELECT order_id, price, X(begin), Y(begin), X(end), Y(end), customers_customer_id, drivers_driver_id, status, rating FROM orders LIMIT ?, ?";
    private static final String READ_BY_TAXI_DRIVER_IN_RANGE_QUERY = "SELECT order_id, price, X(begin), Y(begin), X(end), Y(end), customers_customer_id, drivers_driver_id, status, rating FROM orders WHERE drivers_driver_id=? LIMIT ?, ?";
    private static final String READ_BY_CUSTOMER_IN_RANGE_QUERY = "SELECT order_id, price, X(begin), Y(begin), X(end), Y(end), customers_customer_id, drivers_driver_id, status, rating FROM orders WHERE customers_customer_id=? LIMIT ?, ?";
    private static final String READ_ACTIVE_ORDER_BY_TAXI_DRIVER_ID_QUERY = "SELECT order_id, price, X(begin), Y(begin), X(end), Y(end), customers_customer_id, drivers_driver_id, status, rating FROM orders WHERE drivers_driver_id=? AND status BETWEEN 0 AND 2;";
    private static final String READ_ACTIVE_ORDER_BY_CUSTOMER_ID_QUERY = "SELECT order_id, price, X(begin), Y(begin), X(end), Y(end), customers_customer_id, drivers_driver_id, status, rating FROM orders WHERE customers_customer_id=? AND status BETWEEN 0 AND 2;";
    private static final String READ_LENGTH_QUERY = "SELECT COUNT(*) FROM orders;";
    private static final String READ_LENGTH_BY_TAXI_DRIVER_ID_QUERY = "SELECT COUNT(*) FROM orders WHERE drivers_driver_id=?;";
    private static final String READ_LENGTH_OF_RATED_ORDERS_BY_TAXI_DRIVER_ID_QUERY = "SELECT COUNT(*) FROM orders WHERE drivers_driver_id=? AND rating > 0;";
    private static final String READ_LENGTH_BY_CUSTOMER_ID_QUERY = "SELECT COUNT(*) FROM orders WHERE customers_customer_id=?;";
    private static final String UPDATE_QUERY = "UPDATE orders SET price=?, begin=PointFromText('POINT(%s)'), end=PointFromText('POINT(%s)'), customers_customer_id=?, drivers_driver_id=?, status=?, rating=? WHERE order_id=?;";
    private static final String DELETE_QUERY = "DELETE FROM orders WHERE order_id=?;";

    private static final String ID_COLUMN = "order_id";
    private static final String PRICE_COLUMN = "price";
    private static final String BEGIN_X_COLUMN = "X(begin)";
    private static final String BEGIN_Y_COLUMN = "X(end)";
    private static final String END_X_COLUMN = "Y(begin)";
    private static final String END_Y_COLUMN = "Y(end)";
    private static final String CUSTOMER_ID_COLUMN = "customers_customer_id";
    private static final String DRIVER_ID_COLUMN = "drivers_driver_id";
    private static final String STATUS_COLUMN = "status";
    private static final String RATING_COLUMN = "rating";

    @Override
    protected String getCreateQuery(Order object) {
        String beginPointParam = object.getBegin().getX() + " " + object.getBegin().getY();
        String endPointParam = object.getEnd().getX() + " " + object.getEnd().getY();
        return String.format(CREATE_QUERY, beginPointParam, endPointParam);
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Order object) throws SQLException {
        statement.setFloat(1, object.getPrice());
        statement.setInt(2, object.getCustomerId());
        statement.setInt(3, object.getTaxiDriverId());
        statement.setInt(4, object.getStatus().ordinal());
        statement.setInt(5, object.getRating());
    }

    @Override
    protected String getReadByIdQuery() {
        return READ_BY_ID_QUERY;
    }

    @Override
    protected String getReadInRangeQuery() {
        return READ_IN_RANGE_QUERY;
    }

    @Override
    protected String getReadLengthQuery() {
        return READ_LENGTH_QUERY;
    }

    @Override
    protected String getUpdateQuery(Order object) {
        String beginPointParam = object.getBegin().getX() + " " + object.getBegin().getY();
        String endPointParam = object.getEnd().getX() + " " + object.getEnd().getY();
        return String.format(UPDATE_QUERY, beginPointParam, endPointParam);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order object) throws SQLException {
        statement.setFloat(1, object.getPrice());
        statement.setInt(2, object.getCustomerId());
        statement.setInt(3, object.getTaxiDriverId());
        statement.setInt(4, object.getStatus().ordinal());
        statement.setInt(5, object.getRating());
        statement.setInt(6, object.getId());
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Order object) throws SQLException {
        statement.setInt(1, object.getId());
    }

    @Override
    protected Order parseResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt(ID_COLUMN));
        order.setPrice(resultSet.getInt(PRICE_COLUMN));
        Point begin = new Point();
        begin.setX(resultSet.getFloat(BEGIN_X_COLUMN));
        begin.setY(resultSet.getFloat(BEGIN_Y_COLUMN));
        order.setBegin(begin);
        Point end = new Point();
        end.setX(resultSet.getFloat(END_X_COLUMN));
        end.setY(resultSet.getFloat(END_Y_COLUMN));
        order.setEnd(end);
        order.setCustomerId(resultSet.getInt(CUSTOMER_ID_COLUMN));
        order.setTaxiDriverId(resultSet.getInt(DRIVER_ID_COLUMN));
        order.setStatus(OrderStatus.values()[resultSet.getInt(STATUS_COLUMN)]);
        order.setRating((byte) resultSet.getInt(RATING_COLUMN));
        return order;
    }

    @Override
    public List<Order> readByLocation(float x, float y, int count) throws DAOException {
        logger.error("Trying to invoke UserDAO method on OrderDAO object");
        throw new UnsupportedOperationException("Cannot perform this operation");
    }

    @Override
    public List<Order> readByTaxiDriverInRange(int taxiDriverId, int begin, int itemsPerPage) throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(READ_BY_TAXI_DRIVER_IN_RANGE_QUERY)) {

            statement.setInt(1, taxiDriverId);
            statement.setInt(2, begin);
            statement.setInt(3, itemsPerPage);

            ResultSet result = statement.executeQuery();

            List<Order> orders = new ArrayList<>();
            int currentPosition = 0;
            while (result.next()) {
                orders.add(parseResultSet(result));
            }

            return orders;

        } catch (InterruptedException e) {
            logger.error("Cannot execute query -> interrupted", e);
            throw new DAOException(e);
        } catch (SQLException e) {
            logger.error("Cannot execute query -> SQL error", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<Order> readByCustomerInRange(int customerId, int begin, int itemsPerPage) throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(READ_BY_CUSTOMER_IN_RANGE_QUERY)) {

            statement.setInt(1, customerId);
            statement.setInt(2, begin);
            statement.setInt(3, itemsPerPage);

            ResultSet result = statement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (result.next()) {
                orders.add(parseResultSet(result));
            }

            return orders;

        } catch (InterruptedException e) {
            logger.error("Cannot execute query -> interrupted", e);
            throw new DAOException(e);
        } catch (SQLException e) {
            logger.error("Cannot execute query -> SQL error", e);
            throw new DAOException(e);
        }
    }

    @Override
    public Order readActiveOrderByTaxiDriverId(int driverId) throws DAOException {
        return readActiveOrder(READ_ACTIVE_ORDER_BY_TAXI_DRIVER_ID_QUERY, driverId);
    }

    @Override
    public Order readActiveOrderByCustomerId(int customerId) throws DAOException {
        return readActiveOrder(READ_ACTIVE_ORDER_BY_CUSTOMER_ID_QUERY, customerId);
    }

    private Order readActiveOrder(String query, int id) throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                return parseResultSet(result);
            }

        } catch (InterruptedException e) {
            logger.error("Cannot execute query -> interrupted", e);
            throw new DAOException(e);
        } catch (SQLException e) {
            logger.error("Cannot execute query -> SQL error", e);
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    public int readLengthByTaxiDriverId(int taxiDriverId) throws DAOException {
        return readLength(READ_LENGTH_BY_TAXI_DRIVER_ID_QUERY, taxiDriverId);
    }

    @Override
    public int readLengthOfRatedOrdersByTaxiDriverId(int taxiDriverId) throws DAOException {
        return readLength(READ_LENGTH_OF_RATED_ORDERS_BY_TAXI_DRIVER_ID_QUERY, taxiDriverId);
    }

    @Override
    public int readLengthByCustomerId(int customerId) throws DAOException {
        return readLength(READ_LENGTH_BY_CUSTOMER_ID_QUERY, customerId);
    }

    private int readLength(String query, int id) throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                return result.getInt(1);
            }

        } catch (InterruptedException e) {
            logger.error("Cannot execute query -> interrupted", e);
            throw new DAOException(e);
        } catch (SQLException e) {
            logger.error("Cannot execute query -> SQL error", e);
            throw new DAOException(e);
        }
        return 0;
    }

    @Override
    public Order readByPhoneAndPassword(long phone, char[] password) throws DAOException {
        logger.error("Trying to invoke UserDAO method on OrderDAO object");
        throw new UnsupportedOperationException("Cannot perform this operation");
    }
}
