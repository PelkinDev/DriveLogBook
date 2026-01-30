package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import com.xomstudio.DriveLogBook.infrastructure.entity.DriveLogEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DriveLogService {

    boolean isExists(Long id);

    List<DriveLogEntity> getDriveLogs();

    public List<DriveLogEntity> getAllDriveLogsFromOneVehicle(@PathVariable("vehicleId") Long vehicleId);

    void addNewDriveLog(DriveLogEntity driveLogEntity);

//    Drive Log should not be deletable
    void deleteDriveLog(Long id);

}
