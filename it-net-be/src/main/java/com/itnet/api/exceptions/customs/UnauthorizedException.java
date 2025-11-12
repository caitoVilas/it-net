package com.itnet.api.exceptions.customs;

/**
 * UnauthorizedException is a custom exception that indicates an unauthorized access attempt.
 * It extends RuntimeException and can be thrown when a user tries to access a resource
 * without proper authentication or authorization.
 *
 * @author Caito
 */
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
