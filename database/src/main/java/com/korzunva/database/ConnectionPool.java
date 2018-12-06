package com.korzunva.database;

import com.korzunva.common.messages.MessageProperties;
import com.korzunva.database.exception.DBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Connection pool contains pool of connections
 */
public enum  ConnectionPool {

    INSTANCE;

    private final DBProperties properties = DBProperties.INSTANCE;
    private final MessageProperties messages = MessageProperties.INSTANCE;

    private static final String DRIVER_NOT_FOUND_EXCEPTION = "database.driver_not_found";
    private static final String GET_CONNECTION_EXCEPTION = "database.get_connection";

    private static final String DRIVER_PROPERTY = "db.driver";
    private static final String URL_PROPERTY = "db.url";
    private static final String USERNAME_PROPERTY = "db.username";
    private static final String PASSWORD_PROPERTY = "db.password";
    private static final String SIZE_PROPERTY = "db.pool_size";
    private static final int DEFAULT_SIZE = 4;

    private String driver;
    private String url;
    private String username;
    private String password;
    private int poolSize;

    private LinkedList<Connection> availableConnections = new LinkedList<>();
    private LinkedList<Connection> usedConnections = new LinkedList<>();

    ConnectionPool() {
        initProperties();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DBException(messages.getProperty(DRIVER_NOT_FOUND_EXCEPTION), e);
        }

        for (int i = 0; i < poolSize; i++) {
            availableConnections.push(createConnection());
        }
    }

    private void initProperties() {
        this.driver = properties.getProperty(DRIVER_PROPERTY);
        this.url = properties.getProperty(URL_PROPERTY);
        this.username = properties.getProperty(USERNAME_PROPERTY);
        this.password = properties.getProperty(PASSWORD_PROPERTY);
        try {
            this.poolSize = Integer.parseInt(properties.getProperty(SIZE_PROPERTY));
        } catch (NumberFormatException e) {
            this.poolSize = DEFAULT_SIZE;
        }
    }

    private Connection createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DBException(messages.getProperty(GET_CONNECTION_EXCEPTION), e);
        }
        return connection;
    }

    /**Returns one available connection
     * if there is no available connection create new one*/
    public Connection getConnection() {
        Connection connection;
        if (availableConnections.size() == 0) {
            connection = createConnection();
        } else {
            connection = availableConnections.getLast();
            availableConnections.removeLast();
        }
        usedConnections.add(connection);
        return connection;
    }

    /**Returns used connection to available
     * @param connection connection to be returned in connection pool*/
    public void returnConnection(Connection connection) {
        if (connection != null) {
            if (usedConnections.remove(connection)) {
                availableConnections.add(connection);
            }
        }
    }

}
