package by.tc.web.service.database.connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public interface PooledConnection extends AutoCloseable {
    Statement createStatement() throws SQLException;
    PreparedStatement prepareStatement(String sql) throws SQLException;
    void close() throws SQLException;
}
