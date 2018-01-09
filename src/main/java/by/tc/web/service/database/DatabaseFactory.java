package by.tc.web.service.database;

import by.tc.web.service.database.connection.PooledConnection;
import by.tc.web.service.database.connection.impl.MySQLPooledConnection;
import by.tc.web.service.database.pool.DBPool;
import by.tc.web.service.database.pool.impl.MySQLPool;

import java.sql.Connection;

public final class DatabaseFactory {
    private static final DatabaseFactory instance = new DatabaseFactory();

    private DatabaseFactory() {}

    public static DatabaseFactory getInstance() {
        return instance;
    }

    public DBPool createDBPool() {
        return MySQLPool.getInstance();
    }
    public PooledConnection createDBPooledConnection(Connection connection, DBPool pool) {
        return new MySQLPooledConnection(connection, pool);
    }
}
