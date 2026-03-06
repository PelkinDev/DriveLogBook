package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class VehicleNotFoundException extends DriveLogBookException {

    public VehicleNotFoundException() {
        super("the vehicle not found");
    }

    public VehicleNotFoundException(String message) {
        super(message);
    }
}
