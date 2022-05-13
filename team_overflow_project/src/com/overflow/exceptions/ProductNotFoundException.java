package com.overflow.exceptions;

/**
 * Exception to throw when a given Product ID is not found in the database
 */
public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3090989286995390753L;

    /**
     * Exception with no message or cause.
     */
    public ProductNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause
     * @param message A descriptive message for this exception
     * @param cause The original throwable resulting in this exception.
     */
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause
     * @param cause The original throwable resulting in this exception.
     */
    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }
}