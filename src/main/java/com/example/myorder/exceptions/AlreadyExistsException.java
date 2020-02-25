package com.example.myorder.exceptions;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }
}
