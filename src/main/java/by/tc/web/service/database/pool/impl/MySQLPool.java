package by.tc.web.service.database.pool.impl;

import by.tc.web.service.database.DatabaseFactory;
import by.tc.web.service.database.connection.PooledConnection;
import by.tc.web.service.database.pool.DBPool;
import by.tc.web.service.database.pool.exception.DBPoolException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class MySQLPool implements DBPool {
    private static final MySQLPool instance;
    private static final Logger logger = Logger.getLogger(MySQLPool.class);

    private static final int INITIAL_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 20;

    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/NetTaxi";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private final DatabaseFactory databaseFactory = DatabaseFactory.getInstance();
    private final BlockingQueue<PooledConnection> pool = new ArrayBlockingQueue<>(20);
    private int currentPoolSize = 0;

    private MySQLPool() throws DBPoolException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (INITIAL_POOL_SIZE > MAXIMUM_POOL_SIZE) {
            throw new DBPoolException("Incorrect configuration constants values");
        }

        Class.forName(DRIVER_NAME).newInstance();
        logger.info("Database driver registered");

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                openPooledConnection();
            } catch (SQLException e) {
                logger.warn("Cannot to open a pooled connection" , e);
            }
        }

        if (currentPoolSize == 0 && INITIAL_POOL_SIZE != 0) {
            logger.error("Pool has no opened pooled connections");
        }
    }

    static {
        try(InputStream propertiesFile = ClassLoader.getSystemResourceAsStream("database/jdbc.properties")) {
            /*//configure log4j property file
            Properties log4jProps = new Properties();
            log4jProps.load(Thread.currentThread().getContextClassLoader().getSystemResourceAsStream("log4j/log4j.properties"));
            PropertyConfigurator.configure(log4jProps);

            //read properties from file
            Properties props = new Properties();
            props.load(propertiesFile);

            //set up class constants
            String driver = props.getProperty("jdbc.driver");
            String url = props.getProperty("jdbc.url");
            String user = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

            if (driver == null) {
                throw new DBPoolException("An incorrect jdbc.driver property was provided");
            } else {
                DRIVER_NAME = driver;
            }

            if (url == null) {
                throw new DBPoolException("An incorrect jdbc.url property was provided");
            } else {
                URL = url;
            }

            if (user == null) {
                USER = "";
            } else {
                USER = user;
            }

            if (password == null) {
                PASSWORD = null;
            } else {
                PASSWORD = password;
            }*/

            //instantiate a connections pool
            instance = new MySQLPool();

        } catch (Throwable e) {
            logger.fatal("Cannot instantiate MySQLPool:" + e.getMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private synchronized void openPooledConnection() throws SQLException {
        if (currentPoolSize >= MAXIMUM_POOL_SIZE) {
            logger.warn("Cannot to open a pooled connection: pooled connections limit is reached.");
            return;
        }

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        pool.offer(databaseFactory.createDBPooledConnection(connection, this));
        currentPoolSize++;
        logger.info("A pooled connection opened successfully");
    }

    public static MySQLPool getInstance() {
        return instance;
    }


    @Override
    public PooledConnection takeConnection() throws SQLException, InterruptedException {
        if (pool.peek() == null && currentPoolSize < MAXIMUM_POOL_SIZE) {
            logger.info("Trying to open a new pooled connection");
            try {
                openPooledConnection();
                logger.info("A pooled connection opened");
            } catch (SQLException e) {
                logger.error("Cannot open a new pooled connection");
                throw e;
            }
        }

        logger.info("Taking a pooled connection");
        // The thread will be blocked until a connection becomes available in the queue
        return pool.take();
    }

    @Override
    public void returnConnection(PooledConnection connection) {
        logger.info("Offering a pooled connection");
        pool.offer(connection);
    }

    @Override
    public int getPoolSize() {
        return currentPoolSize;
    }
}
