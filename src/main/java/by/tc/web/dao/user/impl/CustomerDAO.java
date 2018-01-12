package by.tc.web.dao.user.impl;

import by.tc.web.dao.user.UserDAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.service.database.DatabaseFactory;
import by.tc.web.service.database.connection.PooledConnection;
import by.tc.web.service.database.pool.DBPool;
import by.tc.web.service.registrar.impl.CustomerRegistrar;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO implements UserDAO {
    private static final Logger logger = Logger.getLogger(CustomerRegistrar.class);
    private static final DatabaseFactory databaseFactory = DatabaseFactory.getInstance();
    private static final DBPool dbPool = databaseFactory.createDBPool();

    @Override
    public void create(User user) throws DAOException {
        Customer customer = (Customer) user;
        try {
            PooledConnection connection = dbPool.takeConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customers (phone, name, surname, password, is_banned, discount) VALUE (?, ?, ?, ?, false, 0);");

            statement.setString(1, String.valueOf(customer.getPhone()));
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getSurname());
            statement.setString(4, String.valueOf(customer.getPassword()));

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
    public Customer[] read() throws DAOException {
        final String query = "SELECT * FROM customers;";
        try {
            PooledConnection connection = dbPool.takeConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            int rowCount = 0;
            if (result.last()) {
                rowCount = result.getRow();
                result.beforeFirst();
            }

            Customer[] customers = new Customer[rowCount];
            int currentPosition = 0;
            while (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setPhone(result.getLong("phone"));
                customer.setName(result.getString("name"));
                customer.setSurname(result.getString("surname"));
                customer.setPassword(result.getString("password").toCharArray());
                customer.setBanned(result.getBoolean("is_banned"));
                customer.setDiscount(result.getFloat("discount"));
                customers[currentPosition] = customer;
                currentPosition++;
            }

            return customers;

        } catch (SQLException e) {
            logger.error("Cannot register user: ", e);
            throw new DAOException("Cannot register user due to server error");
        } catch (InterruptedException e) {
            logger.error("The thread was interrupted during waiting time", e);
            throw new DAOException("Cannot register due to server error");
        }
    }

    @Override
    public User[] readInRange(int begin, int end) throws DAOException {
        final String query = "SELECT * FROM customers WHERE customer_id BETWEEN ? AND ?";
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

            Customer[] customers = new Customer[rowCount];
            int currentPosition = 0;
            while (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setPhone(result.getLong("phone"));
                customer.setName(result.getString("name"));
                customer.setSurname(result.getString("surname"));
                customer.setPassword(result.getString("password").toCharArray());
                customer.setBanned(result.getBoolean("is_banned"));
                customer.setDiscount(result.getFloat("discount"));
                customers[currentPosition] = customer;
                currentPosition++;
            }

            return customers;

        } catch (InterruptedException e) {
            //TODO
        } catch (SQLException e) {
            //TODO
        }
        return null;
    }

    @Override
    public User readByPhoneAndPassword(long phone, char[] password) throws DAOException {
        final String query = "SELECT * FROM customers WHERE phone=? AND password=?";

        try {
            PooledConnection connection = dbPool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(query);
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

            Customer customer = new Customer();
            customer.setId(result.getInt("customer_id"));
            customer.setPhone(result.getLong("phone"));
            customer.setName(result.getString("name"));
            customer.setSurname(result.getString("surname"));
            customer.setPassword(result.getString("password").toCharArray());
            customer.setBanned(result.getBoolean("is_banned"));
            customer.setDiscount(result.getFloat("discount"));

            return customer;

        } catch (SQLException e) {
            logger.error("Cannot register user: ", e);
            throw new DAOException("Cannot register user due to server error");
        } catch (InterruptedException e) {
            logger.error("The thread was interrupted during waiting time", e);
            throw new DAOException("Cannot register due to server error");
        }
    }

    @Override
    public int readLength() throws DAOException {
        final String query = "SELECT COUNT(*) FROM customers;";

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
    public void update(User user) throws DAOException {
        final String query = "UPDATE customers" +
                "SET phone=?, name=?, surname=?, password=?, is_banned=?, discount=?" +
                "WHERE customer_id=?;";

        Customer customer = (Customer) user;

        try (PooledConnection connection = dbPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, customer.getPhone());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getSurname());
            statement.setString(4, String.valueOf(customer.getPassword()));
            statement.setBoolean(5, customer.isBanned());
            statement.setFloat(6, customer.getDiscount());
            statement.setInt(7, customer.getId());

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
    public void delete(User user) throws DAOException {
        final String query = "DELETE FROM customers WHERE customer_id=?;";

        try (PooledConnection connection = dbPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
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
