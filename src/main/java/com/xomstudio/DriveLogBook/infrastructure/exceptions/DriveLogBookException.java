package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class DriveLogBookException extends RuntimeException{

    public DriveLogBookException() {
        super();
    }

    public DriveLogBookException(String message) {
        super(message);
    }
}
