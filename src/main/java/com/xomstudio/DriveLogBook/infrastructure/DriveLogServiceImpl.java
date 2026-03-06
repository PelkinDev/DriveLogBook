package com.xomstudio.DriveLogBook.infrastructure;

import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleNotFoundException;
import com.xomstudio.DriveLogBook.infrastructure.persistance.DriveLogJPARepository;
import com.xomstudio.DriveLogBook.api.DriveLogService;
import com.xomstudio.DriveLogBook.infrastructure.persistance.VehicleJPARepository;
import com.xomstudio.DriveLogBook.infrastructure.persistance.DriveLogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DriveLogServiceImpl implements DriveLogService {

    private final DriveLogJPARepository driveLogJPARepository;
    private final VehicleJPARepository vehicleJPARepository;

    @Autowired
    public DriveLogServiceImpl(DriveLogJPARepository driveLogJPARepository, VehicleJPARepository vehicleJPARepository) {
        this.driveLogJPARepository = driveLogJPARepository;
        this.vehicleJPARepository = vehicleJPARepository;
    }

    @Override
    public boolean isExists(Long id) {
        return driveLogJPARepository.existsById(id);
    }

    public List<DriveLogEntity> getDriveLogs() {
        return driveLogJPARepository.findAll();
    }

    @Override
    public List<DriveLogEntity> getAllDriveLogsFromOneVehicle(Long vehicleId) {
        return driveLogJPARepository.findAllByVehicleId(vehicleId);
    }

    @Override
    public void addNewDriveLog(DriveLogEntity driveLogEntity) {
        boolean vehicleOptional = vehicleJPARepository.existsById(driveLogEntity.getVehicleEntity().getId());
        if(!vehicleOptional){
            log.warn("vehicle with ID: {} not exists", driveLogEntity.getVehicleEntity().getId());
            throw new VehicleNotFoundException("vehicle with id " + driveLogEntity.getVehicleEntity().getId() + " not exists");
        }
        log.info("new drive log was created.");
        driveLogJPARepository.save(driveLogEntity);
    }

    @Override
    public void deleteDriveLog(Long id) {
        log.info("drive log was deleted");
        driveLogJPARepository.deleteById(id);
    }
}
