package com.itnet.api.exceptions.customs;

/**
 * TokenException is a custom exception class that represents token-related errors.
 *
 * @author Caito
 */
public class TokenException extends RuntimeException {
    public TokenException(String message) {
        super(message);
    }
}
