package by.tc.web.dao.user.impl;

import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.user.UserDAO;
import by.tc.web.domain.car.Car;
import by.tc.web.domain.car.builder.CarBuilder;
import by.tc.web.domain.point.Point;
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
        String pointParam = taxiDriver.getLocation().getX() + " " + taxiDriver.getLocation().getY();
        Car car = taxiDriver.getCar();
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO drivers (phone, name, surname, password, is_banned, car_number, car_model, rating, is_free, location) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, PointFromText('" + pointParam + "'));")) {

            statement.setString(1, String.valueOf(taxiDriver.getPhone()));
            statement.setString(2, taxiDriver.getName());
            statement.setString(3, taxiDriver.getSurname());
            statement.setString(4, String.valueOf(taxiDriver.getPassword()));
            statement.setBoolean(5, taxiDriver.isBanned());
            statement.setString(6, String.valueOf(car.getNumber()));
            statement.setString(7, car.getModel());
            statement.setFloat(8, taxiDriver.getRating());
            statement.setBoolean(9, taxiDriver.isFree());
            statement.setFloat(10, taxiDriver.getLocation().getX());
            statement.setFloat(11, taxiDriver.getLocation().getY());

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
    public User readById(int id) throws DAOException {
        final String query = "SELECT driver_id, phone, name, surname, password, is_banned, car_number, car_model, rating, is_free, X(location), Y(location) FROM drivers WHERE driver_id=?;";

        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                TaxiDriver taxiDriver = new TaxiDriver();

                taxiDriver.setId(result.getInt("driver_id"));
                taxiDriver.setPhone(result.getLong("phone"));
                taxiDriver.setName(result.getString("name"));
                taxiDriver.setSurname(result.getString("surname"));
                taxiDriver.setPassword(result.getString("password").toCharArray());
                taxiDriver.setBanned(result.getBoolean("is_banned"));
                taxiDriver.setRating(result.getFloat("rating"));
                taxiDriver.setFree(result.getBoolean("is_free"));
                Point point = new Point();
                point.setX(result.getFloat("X(location)"));
                point.setY(result.getFloat("Y(location)"));
                taxiDriver.setLocation(point);

                char[] car_number = result.getString("car_number").toCharArray();
                String car_model = result.getString("car_model");
                taxiDriver.setCar(new CarBuilder().number(car_number).model(car_model).build());

                return taxiDriver;
            }

        } catch (InterruptedException e) {
            //TODO
        } catch (SQLException e) {
            //TODO
        }

        return null;
    }

    @Override
    public User[] readByLocation(float x, float y, int count) throws DAOException {
        final String query = "SELECT driver_id, phone, name, surname, password, is_banned, car_number, car_model, rating, is_free, X(location), Y(location) " +
                "FROM drivers where is_banned = false AND is_free = true " +
                "ORDER BY sqrt(pow((x(location) - " + x + "), 2) + pow(abs(y(location) - " + y + "), 2)) limit " + count + ";";

        try (PooledConnection connection = dbPool.takeConnection();
             Statement statement = connection.createStatement()) {

            ResultSet result = statement.executeQuery(query);
            int rowCount = 0;
            if (result.last()) {
                rowCount = result.getRow();
                result.beforeFirst();
            }

            TaxiDriver[] drivers = new TaxiDriver[rowCount];
            int currentRow = 0;
            while (result.next()) {
                TaxiDriver taxiDriver = new TaxiDriver();

                taxiDriver.setId(result.getInt("driver_id"));
                taxiDriver.setPhone(result.getLong("phone"));
                taxiDriver.setName(result.getString("name"));
                taxiDriver.setSurname(result.getString("surname"));
                taxiDriver.setPassword(result.getString("password").toCharArray());
                taxiDriver.setBanned(result.getBoolean("is_banned"));
                taxiDriver.setRating(result.getFloat("rating"));
                taxiDriver.setFree(result.getBoolean("is_free"));
                Point point = new Point();
                point.setX(result.getFloat("X(location)"));
                point.setY(result.getFloat("Y(location)"));
                taxiDriver.setLocation(point);

                char[] car_number = result.getString("car_number").toCharArray();
                String car_model = result.getString("car_model");
                taxiDriver.setCar(new CarBuilder().number(car_number).model(car_model).build());

                drivers[currentRow++] = taxiDriver;
            }

            return drivers;

        } catch (InterruptedException e) {
            //TODO
        } catch (SQLException e) {
            //TODO
        }

        return null;
    }

    @Override
    public User[] readInRange(int begin, int end) throws DAOException {
        final String query = "SELECT driver_id, phone, name, surname, password, is_banned, car_number, car_model, rating, is_free, X(location), Y(location) FROM drivers WHERE driver_id BETWEEN ? AND ?";
        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, begin);
            statement.setInt(2, end);

            ResultSet result = statement.executeQuery();

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
                taxiDriver.setBanned(result.getBoolean("is_banned"));
                taxiDriver.setCar(new Car(result.getString("car_number").toCharArray(), result.getString("car_model")));
                taxiDriver.setRating(result.getFloat("rating"));
                taxiDriver.setFree(result.getBoolean("is_free"));
                Point point = new Point();
                point.setX(result.getFloat("X(location)"));
                point.setY(result.getFloat("Y(location)"));
                taxiDriver.setLocation(point);
                taxiDrivers[currentPosition] = taxiDriver;
                currentPosition++;
            }
            return taxiDrivers;

        } catch (InterruptedException e) {
            //TODO
        } catch (SQLException e) {
            //TODO
        }
        return null;
    }

    @Override
    public User readByPhoneAndPassword(long phone, char[] password) throws DAOException {
        final String query = "SELECT driver_id, phone, name, surname, password, is_banned, car_number, car_model, rating, is_free, X(location), Y(location) FROM drivers WHERE phone=? AND password=?";

        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

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
            taxiDriver.setBanned(result.getBoolean("is_banned"));
            taxiDriver.setCar(new Car(result.getString("car_number").toCharArray(), result.getString("car_model")));
            taxiDriver.setRating(result.getFloat("rating"));
            taxiDriver.setFree(result.getBoolean("is_free"));
            Point point = new Point();
            point.setX(result.getFloat("X(location)"));
            point.setY(result.getFloat("Y(location)"));
            taxiDriver.setLocation(point);

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
    public int readLength() throws DAOException {

        final String query = "SELECT COUNT(*) FROM drivers;";

        try (PooledConnection connection = dbPool.takeConnection();
             Statement statement = connection.createStatement()) {

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
        TaxiDriver taxiDriver = (TaxiDriver) user;

        String pointParam = taxiDriver.getLocation().getX() + " " + taxiDriver.getLocation().getY();
        final String query = "UPDATE drivers " +
                "SET phone=?, name=?, surname=?, password=?, is_banned=?, car_number=?, car_model=?, rating=?, is_free=?, location=PointFromText('POINT(" + pointParam + ")') " +
                "WHERE driver_id=?;";

        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, taxiDriver.getPhone());
            statement.setString(2, taxiDriver.getName());
            statement.setString(3, taxiDriver.getSurname());
            statement.setString(4, String.valueOf(taxiDriver.getPassword()));
            statement.setBoolean(5, taxiDriver.isBanned());
            statement.setString(6, String.valueOf(taxiDriver.getCar().getNumber()));
            statement.setString(7, String.valueOf(taxiDriver.getCar().getModel()));
            statement.setFloat(8, taxiDriver.getRating());
            statement.setBoolean(9, taxiDriver.isFree());
            statement.setInt(10, taxiDriver.getId());

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

        try (PooledConnection connection = dbPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

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
