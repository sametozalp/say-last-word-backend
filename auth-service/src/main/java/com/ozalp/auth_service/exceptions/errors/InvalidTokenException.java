package com.ozalp.auth_service.exceptions.errors;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
