package com.shchehlov.universityschedule.exceptions;

public class IllegalUserToSaveException extends RuntimeException {

    public IllegalUserToSaveException(String message) {
        super(message);
    }
}
