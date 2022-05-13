package com.overflow.exceptions;

/**
 * Exception to throw when a given Inventory ID is not found in the database
 */
public class InventoryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 6523368336964682871L;

    /**
     * Exception with no message or cause.
     */
    public InventoryNotFoundException() {
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public InventoryNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause
     * @param message A descriptive message for this exception
     * @param cause The original throwable resulting in this exception.
     */
    public InventoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause
     * @param cause The original throwable resulting in this exception.
     */
    public InventoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
