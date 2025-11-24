package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class VehicleNoExistsException extends Exception{

    public VehicleNoExistsException() {
        super("the vehicle with this license plate already exists");
    }

    public VehicleNoExistsException(String message) {
        super(message);
    }
}
