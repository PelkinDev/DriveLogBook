package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class VehicleCantBeCreatedException extends DriveLogException{

    public VehicleCantBeCreatedException() {
        super("Vehicle cant be created");
    }

    public VehicleCantBeCreatedException(String message) {
        super(message);
    }
}
