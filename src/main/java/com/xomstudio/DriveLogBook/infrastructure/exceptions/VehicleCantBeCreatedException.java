package com.xomstudio.DriveLogBook.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VehicleCantBeCreatedException extends DriveLogBookException {

    public VehicleCantBeCreatedException() {
        super("Vehicle cant be created");
    }

    public VehicleCantBeCreatedException(String message) {
        super(message);
    }
}
