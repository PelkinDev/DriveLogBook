package com.xomstudio.DriveLogBook.infrastructure;

import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import com.xomstudio.DriveLogBook.domain.dto.VehicleDTO;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleNotFoundException;
import com.xomstudio.DriveLogBook.infrastructure.persistance.*;
import com.xomstudio.DriveLogBook.api.DriveLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DriveLogServiceImpl implements DriveLogService {

    private final DriveLogRepositoryAdapter driveLogRepositoryAdapter;
    private final VehicleRepositoryAdapter vehicleRepositoryAdapter;

    public DriveLogServiceImpl(DriveLogRepositoryAdapter driveLogRepositoryAdapter, VehicleRepositoryAdapter vehicleRepositoryAdapter) {
        this.driveLogRepositoryAdapter = driveLogRepositoryAdapter;
        this.vehicleRepositoryAdapter = vehicleRepositoryAdapter;
    }


    @Override
    public List<DriveLogDTO> getDriveLogs() {
        return driveLogRepositoryAdapter.getAllDriveLogs();
    }

    @Override
    public List<DriveLogDTO> getAllDriveLogsFromOneVehicle(Long vehicleId) {
        return driveLogRepositoryAdapter.getAllDriveLogsByVehicleId(vehicleId);
    }

    @Override
    public void addNewDriveLog(Long id, DriveLogDTO driveLogDTO) {
        DriveLogValidator.validate(driveLogDTO);
        boolean vehicleOptional = vehicleRepositoryAdapter.existsById(id);
        if(!vehicleOptional){
            log.warn("vehicle with ID: {} not exists", id);
            throw new VehicleNotFoundException("vehicle with id " + id + " not exists");
        }
        log.info("new drive log was created.");
        VehicleDTO vehicleDTO = driveLogDTO.getVehicleDto();
        vehicleDTO.setId(id);
        driveLogDTO.setVehicleDto(vehicleDTO);
        driveLogRepositoryAdapter.addDriveLog(driveLogDTO);
    }

}
