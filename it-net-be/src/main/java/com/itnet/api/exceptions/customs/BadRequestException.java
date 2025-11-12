package com.itnet.api.exceptions.customs;

import lombok.Getter;

import java.util.List;

/**
 * BadRequestException is a custom exception class that represents a bad request error.
 * It holds a list of error messages related to the bad request.
 *
 * @author Caito
 */
@Getter
public class BadRequestException extends RuntimeException{
    private final List<String> messages;
    public BadRequestException(List<String> messages) {
        this.messages = messages;
    }
}
