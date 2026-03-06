package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class DriveLogCantBeCreatedBookException extends DriveLogBookException {

    public DriveLogCantBeCreatedBookException() {
        super("DriveLog can't be created: vehicle with this id not found");
    }

    public DriveLogCantBeCreatedBookException(String message) {
        super(message);
    }
}
