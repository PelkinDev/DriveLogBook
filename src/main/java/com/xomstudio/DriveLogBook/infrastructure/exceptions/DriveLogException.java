package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class DriveLogException extends RuntimeException{

    public DriveLogException() {
        super();
    }

    public DriveLogException(String message) {
        super(message);
    }
}
