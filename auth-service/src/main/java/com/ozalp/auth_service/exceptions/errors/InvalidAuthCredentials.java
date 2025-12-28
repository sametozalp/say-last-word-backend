package com.ozalp.auth_service.exceptions.errors;

public class InvalidAuthCredentials extends RuntimeException {
    public InvalidAuthCredentials(String message) {
        super(message);
    }
}
