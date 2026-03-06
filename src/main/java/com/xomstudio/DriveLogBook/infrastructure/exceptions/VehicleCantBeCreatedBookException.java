package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class VehicleCantBeCreatedBookException extends DriveLogBookException {

    public VehicleCantBeCreatedBookException() {
        super("Vehicle cant be created");
    }

    public VehicleCantBeCreatedBookException(String message) {
        super(message);
    }
}
