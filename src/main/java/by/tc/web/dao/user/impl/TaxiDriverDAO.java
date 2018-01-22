package by.tc.web.dao.user.impl;

import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.user.UserMySQLDAO;
import by.tc.web.domain.car.Car;
import by.tc.web.domain.car.builder.CarBuilder;
import by.tc.web.domain.point.Point;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.database.connection.PooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaxiDriverDAO extends UserMySQLDAO<TaxiDriver> {
    private static final String CREATE_QUERY = "INSERT INTO drivers (phone, name, surname, password, is_banned, car_number, car_model, rating, is_free, tariff, location) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, PointFromText('%s'));";
    private static final String READ_BY_ID_QUERY = "SELECT driver_id, phone, name, surname, password, is_banned, car_number, car_model, rating, is_free, X(location), Y(location), tariff FROM drivers WHERE driver_id=?;";
    private static final String READ_IN_RANGE_QUERY = "SELECT driver_id, phone, name, surname, password, is_banned, car_number, car_model, rating, is_free, X(location), Y(location), tariff FROM drivers WHERE driver_id BETWEEN ? AND ?";
    private static final String READ_LENGTH_QUERY = "SELECT COUNT(*) FROM drivers;";
    private static final String READ_BY_PHONE_AND_PASSWORD_QUERY = "SELECT driver_id, phone, name, surname, password, is_banned, car_number, car_model, rating, is_free, X(location), Y(location), tariff FROM drivers WHERE phone=? AND password=?";
    private static final String READ_BY_LOCATION_QUERY = "SELECT driver_id, phone, name, surname, password, is_banned, car_number, car_model, rating, is_free, X(location), Y(location), tariff FROM drivers where is_banned = false AND is_free = true ORDER BY sqrt(pow((x(location) - %f), 2) + pow(abs(y(location) - %f), 2)) limit %d;";
    private static final String UPDATE_QUERY = "UPDATE drivers SET phone=?, name=?, surname=?, password=?, is_banned=?, car_number=?, car_model=?, rating=?, is_free=?, tariff=?, location=PointFromText('POINT(%s)') WHERE driver_id=?;";
    private static final String DELETE_QUERY = "DELETE FROM drivers WHERE driver_id=?;";

    @Override
    protected String getCreateQuery(TaxiDriver object) {
        String pointParam = object.getLocation().getX() + " " + object.getLocation().getY();
        return String.format(CREATE_QUERY, pointParam);
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, TaxiDriver object) throws SQLException {
        Car car = object.getCar();
        statement.setString(1, String.valueOf(object.getPhone()));
        statement.setString(2, object.getName());
        statement.setString(3, object.getSurname());
        statement.setString(4, String.valueOf(object.getPassword()));
        statement.setBoolean(5, object.isBanned());
        statement.setString(6, String.valueOf(car.getNumber()));
        statement.setString(7, car.getModel());
        statement.setFloat(8, object.getRating());
        statement.setBoolean(9, object.isFree());
        statement.setFloat(10, object.getTariff());
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
    protected String getUpdateQuery(TaxiDriver object) {
        String pointParam = object.getLocation().getX() + " " + object.getLocation().getY();
        return String.format(UPDATE_QUERY, pointParam);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, TaxiDriver object) throws SQLException {
        statement.setLong(1, object.getPhone());
        statement.setString(2, object.getName());
        statement.setString(3, object.getSurname());
        statement.setString(4, String.valueOf(object.getPassword()));
        statement.setBoolean(5, object.isBanned());
        statement.setString(6, String.valueOf(object.getCar().getNumber()));
        statement.setString(7, String.valueOf(object.getCar().getModel()));
        statement.setFloat(8, object.getRating());
        statement.setBoolean(9, object.isFree());
        statement.setFloat(10, object.getTariff());
        statement.setInt(11, object.getId());
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, TaxiDriver object) throws SQLException {
        statement.setInt(1, object.getId());
    }

    @Override
    protected TaxiDriver parseResultSet(ResultSet resultSet) throws SQLException {
        TaxiDriver taxiDriver = new TaxiDriver();

        taxiDriver.setId(resultSet.getInt(DRIVER_ID_COLUMN));
        taxiDriver.setPhone(resultSet.getLong(PHONE_COLUMN));
        taxiDriver.setName(resultSet.getString(NAME_COLUMN));
        taxiDriver.setSurname(resultSet.getString(SURNAME_COLUMN));
        taxiDriver.setPassword(resultSet.getString(PASSWORD_COLUMN).toCharArray());
        taxiDriver.setBanned(resultSet.getBoolean(IS_BANNED_COLUMN));
        taxiDriver.setRating(resultSet.getFloat(RATING_COLUMN));
        taxiDriver.setFree(resultSet.getBoolean(IS_FREE_COLUMN));
        Point point = new Point();
        point.setX(resultSet.getFloat(LOCATION_X_COLUMN));
        point.setY(resultSet.getFloat(LOCATION_Y_COLUMN));
        taxiDriver.setLocation(point);
        taxiDriver.setTariff(resultSet.getFloat(TARIFF_COLUMN));

        char[] car_number = resultSet.getString(CAR_NUMBER_COLUMN).toCharArray();
        String car_model = resultSet.getString(CAR_MODEL_COLUMN);
        taxiDriver.setCar(new CarBuilder().number(car_number).model(car_model).build());

        return taxiDriver;
    }

    @Override
    public List<TaxiDriver> readByLocation(float x, float y, int count) throws DAOException {
        try (PooledConnection connection = dbPool.takeConnection();
             Statement statement = connection.createStatement()) {

            ResultSet result = statement.executeQuery(String.format(READ_BY_LOCATION_QUERY, x, y, count));
            List<TaxiDriver> taxiDrivers = new ArrayList<>();
            while (result.next()) {
                taxiDrivers.add(parseResultSet(result));
            }

            return taxiDrivers;

        } catch (InterruptedException e) {
            logger.error("Cannot execute query -> interrupted", e);
            throw new DAOException(e);
        } catch (SQLException e) {
            logger.error("Cannot execute query -> SQL error", e);
            throw new DAOException(e);
        }

    }

    @Override
    protected String getReadByPhoneAndPasswordQuery() {
        return READ_BY_PHONE_AND_PASSWORD_QUERY;
    }
}
