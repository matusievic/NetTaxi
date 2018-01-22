package by.tc.web.dao.user.impl;

import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.user.UserMySQLDAO;
import by.tc.web.domain.user.impl.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends UserMySQLDAO<Customer> {
    private static final String CREATE_QUERY = "INSERT INTO customers (phone, name, surname, password, is_banned, discount) VALUE (?, ?, ?, ?, false, 0);";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM customers WHERE customer_id=?;";
    private static final String READ_IN_RANGE_QUERY = "SELECT * FROM customers WHERE customer_id BETWEEN ? AND ?";
    private static final String READ_BY_PHONE_AND_PASSWORD_QUERY = "SELECT * FROM customers WHERE phone=? AND password=?";
    private static final String READ_LENGTH_QUERY = "SELECT COUNT(*) FROM customers;";
    private static final String UPDATE_QUERY = "UPDATE customers SET phone=?, name=?, surname=?, password=?, is_banned=?, discount=? WHERE customer_id=?;";
    private static final String DELETE_QUERY = "DELETE FROM customers WHERE customer_id=?;";

    @Override
    protected String getCreateQuery(Customer obj) {
        return CREATE_QUERY;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Customer object) throws SQLException {
        statement.setString(1, String.valueOf(object.getPhone()));
        statement.setString(2, object.getName());
        statement.setString(3, object.getSurname());
        statement.setString(4, String.valueOf(object.getPassword()));
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
    protected String getUpdateQuery(Customer object) {
        return UPDATE_QUERY;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Customer object) throws SQLException {
        statement.setLong(1, object.getPhone());
        statement.setString(2, object.getName());
        statement.setString(3, object.getSurname());
        statement.setString(4, String.valueOf(object.getPassword()));
        statement.setBoolean(5, object.isBanned());
        statement.setFloat(6, object.getDiscount());
        statement.setInt(7, object.getId());
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Customer object) throws SQLException {
        statement.setInt(1, object.getId());
    }

    @Override
    protected Customer parseResultSet(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt(CUSTOMER_ID_COLUMN));
        customer.setPhone(resultSet.getLong(PHONE_COLUMN));
        customer.setName(resultSet.getString(NAME_COLUMN));
        customer.setSurname(resultSet.getString(SURNAME_COLUMN));
        customer.setPassword(resultSet.getString(PASSWORD_COLUMN).toCharArray());
        customer.setBanned(resultSet.getBoolean(IS_BANNED_COLUMN));
        customer.setDiscount(resultSet.getFloat(DISCOUNT_COLUMN));
        return customer;
    }

    @Override
    protected String getReadByPhoneAndPasswordQuery() {
        return READ_BY_PHONE_AND_PASSWORD_QUERY;
    }

    @Override
    public List<Customer> readByLocation(float x, float y, int count) throws DAOException {
        return new ArrayList<>();
    }
}
