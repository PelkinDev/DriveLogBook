package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class ValidateException extends RuntimeException{

    public ValidateException() {
        super();
    }

    public ValidateException(String message) {
        super(message);
    }
}
