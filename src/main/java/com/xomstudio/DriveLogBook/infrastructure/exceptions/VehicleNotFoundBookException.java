package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class VehicleNotFoundBookException extends DriveLogBookException {

    public VehicleNotFoundBookException() {
        super("the vehicle not found");
    }

    public VehicleNotFoundBookException(String message) {
        super(message);
    }
}
