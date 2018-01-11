package by.tc.web.dao.user.impl;

import by.tc.web.dao.user.UserDAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.domain.car.Car;
import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.database.DatabaseFactory;
import by.tc.web.service.database.connection.PooledConnection;
import by.tc.web.service.database.pool.DBPool;
import by.tc.web.service.registrar.impl.CustomerRegistrar;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TaxiDriverDAO implements UserDAO {
    private static final Logger logger = Logger.getLogger(CustomerRegistrar.class);
    private static final DatabaseFactory databaseFactory = DatabaseFactory.getInstance();
    private static final DBPool dbPool = databaseFactory.createDBPool();

    @Override
    public void create(User user) throws DAOException {
        TaxiDriver taxiDriver = (TaxiDriver) user;
        Car car = taxiDriver.getCar();
        try {
            PooledConnection connection = dbPool.takeConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO drivers (phone, name, surname, password, is_baned, car_number, car_model, rating) VALUE (?, ?, ?, ?, 0, ?, ?, 0);");
            statement.setString(1, String.valueOf(taxiDriver.getPhone()));
            statement.setString(2, taxiDriver.getName());
            statement.setString(3, taxiDriver.getSurname());
            statement.setString(4, String.valueOf(taxiDriver.getPassword()));
            statement.setString(5, String.valueOf(car.getNumber()));
            statement.setString(6, String.valueOf(car.getModel()));
            statement.execute();
        } catch (SQLException e) {
            logger.error("Cannot register user: ", e);
            throw new DAOException("Cannot register user due to server error");
        } catch (InterruptedException e) {
            logger.error("The thread was interrupted during waiting time", e);
            throw new DAOException("Cannot register due to server error");
        }
    }

    @Override
    public TaxiDriver[] read() throws DAOException {
        final String readQuery = "SELECT * FROM drivers;";
        try {
            PooledConnection connection = dbPool.takeConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(readQuery);

            int rowCount = 0;
            if (result.last()) {
                rowCount = result.getRow();
                result.beforeFirst();
            }

            TaxiDriver[] taxiDrivers = new TaxiDriver[rowCount];
            int currentPosition = 0;
            while (result.next()) {
                TaxiDriver taxiDriver = new TaxiDriver();
                taxiDriver.setId(result.getInt("driver_id"));
                taxiDriver.setPhone(result.getLong("phone"));
                taxiDriver.setName(result.getString("name"));
                taxiDriver.setSurname(result.getString("surname"));
                taxiDriver.setPassword(result.getString("password").toCharArray());
                taxiDriver.setBaned(result.getBoolean("is_baned"));
                taxiDriver.setCar(new Car(result.getString("car_number").toCharArray(), result.getString("car_model")));
                taxiDriver.setRating(result.getFloat("rating"));
                taxiDrivers[currentPosition] = taxiDriver;
                currentPosition++;
            }
            return taxiDrivers;
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
        final String query = "SELECT * FROM drivers WHERE phone=? AND password=?";

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

            TaxiDriver taxiDriver = new TaxiDriver();
            taxiDriver.setId(result.getInt("driver_id"));
            taxiDriver.setPhone(result.getLong("phone"));
            taxiDriver.setName(result.getString("name"));
            taxiDriver.setSurname(result.getString("surname"));
            taxiDriver.setPassword(result.getString("password").toCharArray());
            taxiDriver.setBaned(result.getBoolean("is_baned"));
            taxiDriver.setCar(new Car(result.getString("car_number").toCharArray(), result.getString("car_model")));
            taxiDriver.setRating(result.getFloat("rating"));

            return taxiDriver;

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
        final String query = "UPDATE drivers" +
                "SET phone=?, name=?, surname=?, password=?, is_baned=?, car_number=?, car_model=?, rating=?" +
                "WHERE administrator_id=?;";

        TaxiDriver taxiDriver = (TaxiDriver) user;

        try (PooledConnection connection = dbPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, taxiDriver.getPhone());
            statement.setString(2, taxiDriver.getName());
            statement.setString(3, taxiDriver.getSurname());
            statement.setString(4, String.valueOf(taxiDriver.getPassword()));
            statement.setBoolean(5, taxiDriver.isBaned());
            statement.setString(6, String.valueOf(taxiDriver.getCar().getNumber()));
            statement.setString(7, String.valueOf(taxiDriver.getCar().getModel()));
            statement.setFloat(8, taxiDriver.getRating());
            statement.setInt(9, taxiDriver.getId());

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
        final String query = "DELETE FROM drivers WHERE driver_id=?;";

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
