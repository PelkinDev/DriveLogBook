package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class VehicleNotFoundException extends DriveLogException{

    public VehicleNotFoundException() {
        super("the vehicle not found");
    }

    public VehicleNotFoundException(String message) {
        super(message);
    }
}
