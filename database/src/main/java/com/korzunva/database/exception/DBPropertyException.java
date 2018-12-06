package com.korzunva.database.exception;

/**
 * Throws when error occurs while reading database properties
 */
public class DBPropertyException extends RuntimeException {

    public DBPropertyException() {
        super();
    }

    public DBPropertyException(String message) {
        super(message);
    }

    public DBPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBPropertyException(Throwable cause) {
        super(cause);
    }

}
