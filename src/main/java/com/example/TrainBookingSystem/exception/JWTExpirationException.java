package com.example.TrainBookingSystem.exception;

public class JWTExpirationException extends RuntimeException {
    public JWTExpirationException(String message) {
        super(message);
    }

    public JWTExpirationException(String message, Throwable cause) {
        super(message, cause);
    }
}

