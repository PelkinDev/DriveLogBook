package com.xomstudio.DriveLogBook.infrastructure;

import com.xomstudio.DriveLogBook.api.DriveLogRepository;
import com.xomstudio.DriveLogBook.api.DriveLogService;
import com.xomstudio.DriveLogBook.api.VehicleRepository;
import com.xomstudio.DriveLogBook.infrastructure.entity.DriveLogEntity;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DriveLogServiceImpl implements DriveLogService {

    private final DriveLogRepository driveLogRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public DriveLogServiceImpl(DriveLogRepository driveLogRepository, VehicleRepository vehicleRepository) {
        this.driveLogRepository = driveLogRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public boolean isExists(Long id) {
        return driveLogRepository.existsById(id);
    }

    public List<DriveLogEntity> getDriveLogs() {
        return driveLogRepository.findAll();
    }

    @Override
    public List<DriveLogEntity> getAllDriveLogsFromOneVehicle(Long vehicleId) {
        return driveLogRepository.findAllByVehicleId(vehicleId);
    }

    @Override
    public void addNewDriveLog(DriveLogEntity driveLogEntity) {
        boolean vehicleOptional = vehicleRepository.existsById(driveLogEntity.getVehicleEntity().getId());
        if(!vehicleOptional){
            log.warn("vehicle with ID: {} not exists", driveLogEntity.getVehicleEntity().getId());
            throw new VehicleNotFoundException("vehicle with id " + driveLogEntity.getVehicleEntity().getId() + " not exists");
        }
        log.info("new drive log was created.");
        driveLogRepository.save(driveLogEntity);
    }

    @Override
    public void deleteDriveLog(Long id) {
        log.info("drive log was deleted");
        driveLogRepository.deleteById(id);
    }
}
