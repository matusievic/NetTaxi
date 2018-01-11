package by.tc.web.dao.user.impl;

import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.user.UserDAO;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Administrator;
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

public class AdministratorDAO implements UserDAO {
    private static final Logger logger = Logger.getLogger(CustomerRegistrar.class);
    private static final DatabaseFactory databaseFactory = DatabaseFactory.getInstance();
    private static final DBPool dbPool = databaseFactory.createDBPool();

    @Override
    public void create(User user) throws DAOException {
        Customer customer = (Customer) user;
        try (PooledConnection connection = dbPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO administrator (phone, name, surname, password) VALUE (?, ?, ?, ?);");

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
    public Administrator[] read() throws DAOException {
        final String query = "SELECT * FROM administrators;";
        try (PooledConnection connection = dbPool.takeConnection()) {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            int rowCount = 0;
            if (result.last()) {
                rowCount = result.getRow();
                result.beforeFirst();
            }

            Administrator[] administrators = new Administrator[rowCount];
            int currentPosition = 0;
            while (result.next()) {
                Administrator administrator = new Administrator();
                administrator.setId(result.getInt("administrator_id"));
                administrator.setPhone(result.getLong("phone"));
                administrator.setName(result.getString("name"));
                administrator.setSurname(result.getString("surname"));
                administrator.setPassword(result.getString("password").toCharArray());
                administrators[currentPosition] = administrator;
                currentPosition++;
            }

            return administrators;

        } catch (SQLException e) {
            logger.error("Cannot register user: ", e);
            throw new DAOException("Cannot register user due to server error");
        } catch (InterruptedException e) {
            logger.error("The thread was interrupted during waiting time", e);
            throw new DAOException("Cannot register due to server error");
        }
    }

    @Override
    public User readByPhoneAndPassword(long phone, char[] password) throws DAOException {
        final String query = "SELECT * FROM administrators WHERE phone=? AND password=?";

        try (PooledConnection connection = dbPool.takeConnection()) {

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

            Administrator administrator = new Administrator();
            administrator.setId(result.getInt("administrator_id"));
            administrator.setPhone(result.getLong("phone"));
            administrator.setName(result.getString("name"));
            administrator.setSurname(result.getString("surname"));
            administrator.setPassword(result.getString("password").toCharArray());

            return administrator;

        } catch (SQLException e) {
            logger.error("Cannot register user: ", e);
            throw new DAOException("Cannot register user due to server error");
        } catch (InterruptedException e) {
            logger.error("The thread was interrupted during waiting time", e);
            throw new DAOException("Cannot register due to server error");
        }
    }

    @Override
    public void update(User user) throws DAOException {
        final String query = "UPDATE administrators" +
                             "SET phone=?, name=?, surname=?, password=?" +
                             "WHERE administrator_id=?;";

        Administrator administrator = (Administrator) user;

        try (PooledConnection connection = dbPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, administrator.getPhone());
            statement.setString(2, administrator.getName());
            statement.setString(3, administrator.getSurname());
            statement.setString(4, String.valueOf(administrator.getPassword()));
            statement.setInt(5, administrator.getId());

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
        final String query = "DELETE FROM administrators WHERE administrator_id=?;";

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
