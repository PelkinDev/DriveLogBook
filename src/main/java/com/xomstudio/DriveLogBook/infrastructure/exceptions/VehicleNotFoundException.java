package com.xomstudio.DriveLogBook.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends DriveLogBookException {

    public VehicleNotFoundException() {
        super("the vehicle not found");
    }

    public VehicleNotFoundException(String message) {
        super(message);
    }
}
