package by.tc.web.dao.order.impl;

import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.order.OrderDAO;
import by.tc.web.domain.order.Order;
import by.tc.web.domain.order.OrderStatus;
import by.tc.web.domain.point.Point;
import by.tc.web.service.database.DatabaseFactory;
import by.tc.web.service.database.connection.PooledConnection;
import by.tc.web.service.database.pool.DBPool;
import by.tc.web.service.registrar.impl.CustomerRegistrar;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLOrderDAO implements OrderDAO {
    private static final Logger logger = Logger.getLogger(CustomerRegistrar.class);
    private static final DatabaseFactory databaseFactory = DatabaseFactory.getInstance();
    private static final DBPool dbPool = databaseFactory.createDBPool();

    @Override
    public void create(Order order) throws DAOException {
        String beginPointParam = order.getBegin().getX() + " " + order.getBegin().getY();
        String endPointParam = order.getEnd().getX() + " " + order.getEnd().getY();
        final String query = "INSERT INTO orders (price, begin, end, customers_customer_id, drivers_driver_id, status) VALUE (?, PointFromText('POINT(" + beginPointParam + ")'), PointFromText('POINT(" + endPointParam + ")'), ?, ?, ?);";

        try (PooledConnection connection = dbPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, order.getPrice());
            statement.setFloat(2, order.getBegin().getX());
            statement.setFloat(3, order.getBegin().getY());
            statement.setFloat(4, order.getEnd().getX());
            statement.setFloat(5, order.getEnd().getY());
            statement.setInt(6, order.getCustomerId());
            statement.setInt(7, order.getTaxiDriverId());
            statement.setInt(8, order.getStatus().ordinal());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("Cannot perform update query");
            }

        } catch (SQLException e) {
            logger.error("Cannot register user: ", e);
            throw new DAOException("Cannot register user due to server error");
        } catch (InterruptedException e) {
            logger.error("The thread was interrupted during waiting time", e);
            throw new DAOException("Cannot register due to server error");
        }
    }

    @Override
    public Order readById(int id) throws DAOException {
        final String query = "SELECT order_id, price, X(begin), Y(begin), X(end), Y(end), customers_customer_id, drivers_driver_id, status FROM orders WHERE order_id=?;";

        try (PooledConnection connection = dbPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Order order = new Order();
                order.setId(result.getInt("order_id"));
                order.setPrice(result.getInt("price"));
                Point begin = new Point();
                begin.setX(result.getFloat("X(begin)"));
                begin.setY(result.getFloat("Y(begin)"));
                order.setBegin(begin);
                Point end = new Point();
                end.setX(result.getFloat("X(end)"));
                end.setY(result.getFloat("Y(end)"));
                order.setEnd(end);
                order.setCustomerId(result.getInt("customers_customer_id"));
                order.setTaxiDriverId(result.getInt("drivers_driver_id"));
                order.setStatus(OrderStatus.values()[result.getInt("status")]);
                return order;
            }

        } catch (InterruptedException e) {
            //TODO
        } catch (SQLException e) {
            //TODO
        }

        return null;
    }

    @Override
    public Order[] readInRange(int begin, int end) throws DAOException {
        final String query = "SELECT order_id, price, X(begin), Y(begin), X(end), Y(end), customers_customer_id, drivers_driver_id, status FROM orders WHERE order_id BETWEEN ? AND ?";
        try (PooledConnection connection = dbPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, begin);
            statement.setInt(2, end);

            ResultSet result = statement.executeQuery();

            int rowCount = 0;
            if (result.last()) {
                rowCount = result.getRow();
                result.beforeFirst();
            }

            Order[] orders = new Order[rowCount];
            int currentPosition = 0;
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getInt("order_id"));
                order.setPrice(result.getInt("price"));
                Point beginPoint = new Point();
                beginPoint.setX(result.getFloat("X(begin)"));
                beginPoint.setY(result.getFloat("Y(begin)"));
                order.setBegin(beginPoint);
                Point endPoint = new Point();
                endPoint.setX(result.getFloat("X(end)"));
                endPoint.setY(result.getFloat("Y(end)"));
                order.setEnd(endPoint);
                order.setCustomerId(result.getInt("customers_customer_id"));
                order.setTaxiDriverId(result.getInt("drivers_driver_id"));
                order.setStatus(OrderStatus.values()[result.getInt("status")]);
                orders[currentPosition++] = order;
            }

            return orders;

        } catch (InterruptedException e) {
            //TODO
        } catch (SQLException e) {
            //TODO
        }
        return null;
    }

    @Override
    public int readLength() throws DAOException {
        final String query = "SELECT COUNT(*) FROM orders;";

        try (PooledConnection connection = dbPool.takeConnection()) {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                return result.getInt(1);
            }

        } catch (InterruptedException e) {
            //TODO
        } catch (SQLException e) {
            //TODO
        }
        return 0;
    }

    @Override
    public void update(Order order) throws DAOException {
        String beginPointParam = order.getBegin().getX() + " " + order.getBegin().getY();
        String endPointParam = order.getEnd().getX() + " " + order.getEnd().getY();
        final String query = "UPDATE orders " +
                             "SET price=?, begin=PointFromText('POINT(" + beginPointParam + ")'), end=PointFromText('POINT(" + endPointParam + ")'), customers_customer_id=?, drivers_driver_id=?, status=? " +
                             "WHERE order_id=?;";

        try (PooledConnection connection = dbPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setFloat(1, order.getPrice());
            statement.setFloat(2, order.getBegin().getX());
            statement.setFloat(3, order.getBegin().getY());
            statement.setFloat(4, order.getEnd().getX());
            statement.setFloat(5, order.getEnd().getY());
            statement.setInt(6, order.getCustomerId());
            statement.setInt(7, order.getTaxiDriverId());
            statement.setInt(8, order.getStatus().ordinal());
            statement.setInt(9, order.getId());

            if (statement.executeUpdate() != 1) {
                //TODO
            }

        } catch (InterruptedException e) {
            //TODO
        } catch (SQLException e) {
            //TODO
        }
    }

    @Override
    public void delete(Order order) throws DAOException {
        final String query = "DELETE FROM orders WHERE order_id=?;";

        try (PooledConnection connection = dbPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, order.getId());
            if (statement.executeUpdate() != 1) {
                //TODO
            }

        } catch (InterruptedException e) {
            //TODO
        } catch (SQLException e) {
            //TODO
        }
    }
}
