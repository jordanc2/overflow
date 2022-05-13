package com.overflow.exceptions;

/**
 * Exception to throw when a given Client ID is not found in the database
 */
public class ClientNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2176448220320219435L;

    /**
     * Exception with no message or cause.
     */
    public ClientNotFoundException() {
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public ClientNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause
     * @param message A descriptive message for this exception
     * @param cause The original throwable resulting in this exception.
     */
    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause
     * @param cause The original throwable resulting in this exception.
     */
    public ClientNotFoundException(Throwable cause) {
        super(cause);
    }
}
