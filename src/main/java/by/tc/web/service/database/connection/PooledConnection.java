package by.tc.web.service.database.connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This interface defines PooledConnection methods
 */
public interface PooledConnection extends AutoCloseable {
    /**
     * This methods corresponds to createStatement() method of Connection class
     * @return a Statement
     * @throws SQLException if an SQL error has occurred
     */
    Statement createStatement() throws SQLException;

    /**
     * This methods corresponds to prepareStatement() method of Connection class
     * @param sql a SQL-query
     * @return a PreparedStatement
     * @throws SQLException if an SQL error has occurred
     */
    PreparedStatement prepareStatement(String sql) throws SQLException;

    /**
     * This method closes this PooledConnection
     * @throws SQLException if an SQL error has occurred
     */
    void close() throws SQLException;
}
