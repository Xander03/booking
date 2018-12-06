package com.korzunva.database.exception;

/**
 * Thrown when error occur while trying to open/close
 * {@link java.sql.Connection} to database
 */
public class DBException extends RuntimeException {

    public DBException() {
        super();
    }

    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause) {
        super(cause);
    }

}
