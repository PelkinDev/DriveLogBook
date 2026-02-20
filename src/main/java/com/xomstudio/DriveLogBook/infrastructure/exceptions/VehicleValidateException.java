package com.xomstudio.DriveLogBook.infrastructure.exceptions;

public class VehicleValidateException extends ValidateException{

    public VehicleValidateException() {
        super("Vehicle validation exception");
    }

    public VehicleValidateException(String message) {
        super(message);
    }
}
