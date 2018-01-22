package by.tc.web.dao;

import by.tc.web.dao.exception.DAOException;
import by.tc.web.service.database.DatabaseFactory;
import by.tc.web.service.database.connection.PooledConnection;
import by.tc.web.service.database.pool.DBPool;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMySQLDAO<T> implements DAO<T> {
    protected final Logger logger = Logger.getLogger(getClass());
    protected static final DatabaseFactory databaseFactory = DatabaseFactory.getInstance();
    protected static final DBPool dbPool = databaseFactory.createDBPool();

    protected static final String ID_COLUMN = "administrator_id";
    protected static final String CUSTOMER_ID_COLUMN = "customer_id";
    protected static final String DRIVER_ID_COLUMN = "driver_id";
    protected static final String PHONE_COLUMN = "phone";
    protected static final String NAME_COLUMN = "name";
    protected static final String SURNAME_COLUMN = "surname";
    protected static final String PASSWORD_COLUMN = "password";
    protected static final String IS_BANNED_COLUMN = "is_banned";
    protected static final String RATING_COLUMN = "rating";
    protected static final String IS_FREE_COLUMN = "is_free";
    protected static final String LOCATION_X_COLUMN = "X(location)";
    protected static final String LOCATION_Y_COLUMN = "Y(location)";
    protected static final String TARIFF_COLUMN = "tariff";
    protected static final String CAR_NUMBER_COLUMN = "car_number";
    protected static final String CAR_MODEL_COLUMN = "car_model";
    protected static final String DISCOUNT_COLUMN = "discount";

    protected abstract String getCreateQuery(T obj);

    protected abstract void prepareStatementForCreate(PreparedStatement statement, T object) throws SQLException;

    protected abstract String getReadByIdQuery();

    protected abstract String getReadInRangeQuery();

    protected abstract String getReadLengthQuery();

    protected abstract String getUpdateQuery(T object);

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    protected abstract String getDeleteQuery();

    protected abstract void prepareStatementForDelete(PreparedStatement statement, T object) throws SQLException;

    protected abstract T parseResultSet(ResultSet resultSet) throws SQLException;

    @Override
    public void create(T object) throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(getCreateQuery(object))) {

            prepareStatementForCreate(statement, object);
            if (statement.executeUpdate() != 1) {
                logger.error("Wrong query result -> incorrect result value");
                throw new DAOException("Query execution error");
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
    public T readById(int id) throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(getReadByIdQuery())) {

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
    public List<T> readInRange(int begin, int itemsPerPage) throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(getReadInRangeQuery())) {

            statement.setInt(1, begin);
            statement.setInt(2, itemsPerPage);

            ResultSet result = statement.executeQuery();

            int rowCount = 0;
            if (result.last()) {
                rowCount = result.getRow();
                result.beforeFirst();
            }

            List<T> objects = new ArrayList<>();
            int currentPosition = 0;
            while (result.next()) {
                objects.add(parseResultSet(result));
            }

            return objects;

        } catch (InterruptedException e) {
            logger.error("Cannot execute query -> interrupted", e);
            throw new DAOException(e);
        } catch (SQLException e) {
            logger.error("Cannot execute query -> SQL error", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int readLength() throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             Statement statement = connection.createStatement()) {

            ResultSet result = statement.executeQuery(getReadLengthQuery());
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
    public void update(T object) throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(getUpdateQuery(object))) {

            prepareStatementForUpdate(statement, object);
            if (statement.executeUpdate() != 1) {
                logger.error("Wrong query result -> incorrect result value");
                throw new DAOException("Query execution error");
            }

        } catch (InterruptedException e) {
            logger.error("Cannot execute query -> interrupted", e);
            throw new DAOException(e);
        } catch (SQLException e) {
            logger.error("Cannot execute query -> SQL error", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(T object) throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {

            prepareStatementForDelete(statement, object);
            if (statement.executeUpdate() != 1) {
                logger.error("Wrong query result -> incorrect result value");
                throw new DAOException("Query execution error");
            }

        } catch (InterruptedException e) {
            logger.error("Cannot execute query -> interrupted", e);
            throw new DAOException(e);
        } catch (SQLException e) {
            logger.error("Cannot execute query -> SQL error", e);
            throw new DAOException(e);
        }
    }
}
