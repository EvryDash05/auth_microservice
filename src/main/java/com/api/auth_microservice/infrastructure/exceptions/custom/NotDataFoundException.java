package com.api.auth_microservice.infrastructure.exceptions.custom;

public class NotDataFoundException extends RuntimeException {

    public NotDataFoundException() {
        super();
    }

    public NotDataFoundException(String message) {
        super(message);
    }
}
