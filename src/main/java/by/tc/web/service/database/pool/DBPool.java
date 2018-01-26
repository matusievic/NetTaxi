package by.tc.web.service.database.pool;

import by.tc.web.service.database.connection.PooledConnection;

import java.sql.SQLException;

/**
 * This interface defines database pool methods
 */
public interface DBPool {
    /**
     * This method is looking for a free PooledConnection and returns it
     * @return a free PooledConnection
     * @throws SQLException if a database error has occurred
     * @throws InterruptedException when multithreading error has occurred
     */
    PooledConnection takeConnection() throws SQLException, InterruptedException;

    /**
     * This methods returns a PooledConnection to the pool
     * @param connection a connection for returning
     */
    void returnConnection(PooledConnection connection);

    /**
     * This method returns database pool size
     * @return database pool size
     */
    int getPoolSize();
}
