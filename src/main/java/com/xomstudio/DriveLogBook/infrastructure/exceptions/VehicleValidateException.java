package com.xomstudio.DriveLogBook.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VehicleValidateException extends ValidateException{

    public VehicleValidateException() {
        super("Vehicle validation exception");
    }

    public VehicleValidateException(String message) {
        super(message);
    }
}
