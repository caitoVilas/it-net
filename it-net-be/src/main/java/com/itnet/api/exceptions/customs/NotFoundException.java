package com.itnet.api.exceptions.customs;

/**
 * NotFoundException is a custom exception class that represents a not found error.
 *
 * @author Caito
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
