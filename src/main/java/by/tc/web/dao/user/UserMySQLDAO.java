package by.tc.web.dao.user;

import by.tc.web.dao.AbstractMySQLDAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.service.database.connection.PooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class UserMySQLDAO<T> extends AbstractMySQLDAO<T> {
    protected abstract String getReadByPhoneAndPasswordQuery();

    @Override
    public List<T> readByTaxiDriverInRange(int taxiDriverId, int begin, int itemsPerPage) throws DAOException {
        logger.error("Trying to invoke OrderDAO method on UserDAO object");
        throw new UnsupportedOperationException("Cannot perform this operation");
    }

    @Override
    public List<T> readByCustomerInRange(int customerId, int begin, int itemsPerPage) throws DAOException {
        logger.error("Trying to invoke OrderDAO method on UserDAO object");
        throw new UnsupportedOperationException("Cannot perform this operation");
    }

    @Override
    public T readActiveOrderByTaxiDriverId(int driverId) {
        logger.error("Trying to invoke OrderDAO method on UserDAO object");
        throw new UnsupportedOperationException("Cannot perform this operation");
    }

    @Override
    public T readActiveOrderByCustomerId(int customerId) {
        logger.error("Trying to invoke OrderDAO method on UserDAO object");
        throw new UnsupportedOperationException("Cannot perform this operation");
    }

    @Override
    public int readLengthByTaxiDriverId(int taxiDriverId) throws DAOException {
        logger.error("Trying to invoke OrderDAO method on UserDAO object");
        throw new UnsupportedOperationException("Cannot perform this operation");
    }

    @Override
    public int readLengthOfRatedOrdersByTaxiDriverId(int taxiDriverId) throws DAOException {
        logger.error("Trying to invoke OrderDAO method on UserDAO object");
        throw new UnsupportedOperationException("Cannot perform this operation");
    }

    @Override
    public int readLengthByCustomerId(int customerId) throws DAOException {
        logger.error("Trying to invoke OrderDAO method on UserDAO object");
        throw new UnsupportedOperationException("Cannot perform this operation");
    }

    @Override
    public T readByPhoneAndPassword(long phone, char[] password) throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(getReadByPhoneAndPasswordQuery())) {

            statement.setLong(1, phone);
            statement.setString(2, String.valueOf(password));
            ResultSet result = statement.executeQuery();

            if (result.last()) {
                if (result.getRow() != 1) {
                    return null;
                } else {
                    result.beforeFirst();
                }
            }

            if (!result.next()) {
                return null;
            }

            return parseResultSet(result);

        } catch (SQLException e) {
            logger.error("Cannot register user: ", e);
            throw new DAOException("Cannot register user due to server error");
        } catch (InterruptedException e) {
            logger.error("The thread was interrupted during waiting time", e);
            throw new DAOException("Cannot register due to server error");
        }
    }
}

