package com.korzunva.common.exception;

/**
 * Thrown when exception occur while working with messages' resources
 */
public class MessagePropertiesException extends RuntimeException {

    public MessagePropertiesException() {
        super();
    }

    public MessagePropertiesException(String message) {
        super(message);
    }

    public MessagePropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessagePropertiesException(Throwable cause) {
        super(cause);
    }

}
