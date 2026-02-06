package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class DriveLogCantBeCreatedException extends DriveLogException{

    public DriveLogCantBeCreatedException() {
        super("DriveLog can't be created: vehicle with this id not found");
    }

    public DriveLogCantBeCreatedException(String message) {
        super(message);
    }
}
