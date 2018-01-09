package by.tc.web.service.database.pool;

import by.tc.web.service.database.connection.PooledConnection;

import java.sql.SQLException;

public interface DBPool {
    PooledConnection takeConnection() throws SQLException, InterruptedException;
    void returnConnection(PooledConnection connection);
    int getPoolSize();
}
