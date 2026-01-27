package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.infrastructure.entity.DriveLogEntity;

import java.util.List;

public interface DriveLogService {

    boolean isExists(Long id);

    List<DriveLogEntity> getDriveLogs();

    void addNewDriveLog(DriveLogEntity driveLogEntity);

//    Drive Log should not be deletable
    void deleteDriveLog(Long id);

}
