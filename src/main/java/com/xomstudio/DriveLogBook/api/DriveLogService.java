package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import com.xomstudio.DriveLogBook.infrastructure.persistance.DriveLogEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DriveLogService {

    List<DriveLogDTO> getDriveLogs();
    List<DriveLogDTO> getAllDriveLogsFromOneVehicle(@PathVariable("vehicleId") Long vehicleId);
    void addNewDriveLog(Long id, DriveLogDTO driveLogDTO);
}
