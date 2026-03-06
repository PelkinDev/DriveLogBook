package com.xomstudio.DriveLogBook.infrastructure;

import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.DriveLogValidateException;

import java.time.LocalDateTime;

public class DriveLogValidator {

    public static void validate(DriveLogDTO driveLogDTO){
        validateDriveStart(driveLogDTO.getDriveStart());
        validateTripTime(driveLogDTO.getDriveStart(), driveLogDTO.getDriveEnd());
        validateDistance(driveLogDTO.getMileageAtStart(), driveLogDTO.getMileageAtEnd(), driveLogDTO.getDistanceDriven());
    }

    private static void validateDriveStart(LocalDateTime driveStart){
        if(driveStart == null ){
            throw new DriveLogValidateException("DriveStart can not be null or empty");
        }
        LocalDateTime now = LocalDateTime.now();
        if(now.isBefore(driveStart)){
            throw new DriveLogValidateException("DriveStart can not be in the future");
        }
    }

    private static void validateTripTime(LocalDateTime driveStart, LocalDateTime driveEnd){
        if(driveStart == null || driveEnd== null){
            throw new DriveLogValidateException("DriveStart or DriveEnd can not be null or empty");
        }
        if(driveEnd.isBefore(driveStart)){
            throw new DriveLogValidateException("DriveEnd can not be before DriveStart");
        }
    }

    private static void validateDistance(int mileageAtStart, int mileageAtEnd, int distanceDriven){
        if(mileageAtStart + distanceDriven != mileageAtEnd){
            throw new DriveLogValidateException("Mileage on start is different to mileage on end + distance");
        }
    }

}
