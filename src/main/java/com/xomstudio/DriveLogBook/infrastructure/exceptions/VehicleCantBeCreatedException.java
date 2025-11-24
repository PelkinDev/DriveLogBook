package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class VehicleCantBeCreatedException extends Exception{

    public VehicleCantBeCreatedException() {
        super("the vehicle with this license plate already exists");
    }

    public VehicleCantBeCreatedException(String message) {
        super(message);
    }
}
