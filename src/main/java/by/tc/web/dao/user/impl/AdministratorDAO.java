package by.tc.web.dao.user.impl;

import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.user.UserMySQLDAO;
import by.tc.web.domain.user.impl.Administrator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAO extends UserMySQLDAO<Administrator> {
    private static final String CREATE_QUERY = "INSERT INTO administrator (phone, name, surname, password) VALUE (?, ?, ?, ?);";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM administrators WHERE administrator_id=?;";
    private static final String READ_IN_RANGE = "SELECT * FROM administrators WHERE administrator_id BETWEEN ? AND ?";
    private static final String READ_BY_PHONE_AND_PASSWORD_QUERY = "SELECT * FROM administrators WHERE phone=? AND password=?";
    private static final String READ_LENGTH = "SELECT COUNT(*) FROM administrators;";
    private static final String UPDATE_QUERY = "UPDATE administrators SET phone=?, name=?, surname=?, password=? WHERE administrator_id=?;";
    private static final String DELETE_QUERY = "DELETE FROM administrators WHERE administrator_id=?;";

    @Override
    protected String getReadByPhoneAndPasswordQuery() {
        return READ_BY_PHONE_AND_PASSWORD_QUERY;
    }

    @Override
    public List<Administrator> readByLocation(float x, float y, int count) throws DAOException {
        return new ArrayList<>();
    }

    @Override
    protected String getCreateQuery(Administrator object) {
        return CREATE_QUERY;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Administrator object) throws SQLException {
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
        return READ_IN_RANGE;
    }

    @Override
    protected String getReadLengthQuery() {
        return READ_LENGTH;
    }

    @Override
    protected String getUpdateQuery(Administrator object) {
        return UPDATE_QUERY;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Administrator object) throws SQLException {
        statement.setLong(1, object.getPhone());
        statement.setString(2, object.getName());
        statement.setString(3, object.getSurname());
        statement.setString(4, String.valueOf(object.getPassword()));
        statement.setInt(5, object.getId());
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Administrator object) throws SQLException {
        statement.setInt(1, object.getId());
    }

    @Override
    protected Administrator parseResultSet(ResultSet resultSet) throws SQLException {
        Administrator administrator = new Administrator();
        administrator.setId(resultSet.getInt(ID_COLUMN));
        administrator.setPhone(resultSet.getLong(PHONE_COLUMN));
        administrator.setName(resultSet.getString(NAME_COLUMN));
        administrator.setSurname(resultSet.getString(SURNAME_COLUMN));
        administrator.setPassword(resultSet.getString(PASSWORD_COLUMN).toCharArray());
        return administrator;
    }
}
