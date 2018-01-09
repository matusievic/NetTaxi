package by.tc.web.service.database.connection.impl;

import by.tc.web.service.database.connection.PooledConnection;
import by.tc.web.service.database.pool.DBPool;

import java.sql.*;

public class MySQLPooledConnection extends AbstractPooledConnection implements PooledConnection {
    private Connection connection;
    private DBPool pool;

    public MySQLPooledConnection(Connection connection, DBPool pool) {
        this.connection = connection;
        this.pool = pool;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    @Override
    public void close() throws SQLException {
        pool.returnConnection(this);
    }
}
