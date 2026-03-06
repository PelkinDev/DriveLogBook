package com.xomstudio.DriveLogBook.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DriveLogValidateException extends ValidateException{

    public DriveLogValidateException() {
        super("DriveLog validation exception");
    }

    public DriveLogValidateException(String message) {
        super(message);
    }
}
