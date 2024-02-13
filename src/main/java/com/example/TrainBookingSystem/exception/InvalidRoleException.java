package com.example.TrainBookingSystem.exception;

public class InvalidRoleException extends RuntimeException{

    public InvalidRoleException(String message) {
        super(message);
    }

    public InvalidRoleException(Throwable cause) {
        super(cause);
    }
}
