package com.xomstudio.DriveLogBook.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DriveLogCantBeCreatedException extends DriveLogBookException {

    public DriveLogCantBeCreatedException() {
        super("DriveLog can't be created: vehicle with this id not found");
    }

    public DriveLogCantBeCreatedException(String message) {
        super(message);
    }
}
