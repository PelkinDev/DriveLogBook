package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    boolean isExists(Long id);
    List<VehicleEntity> getVehicles();
    Optional<VehicleEntity> getVehicleById(Long vehicleId);
    void addNewVehicle(VehicleEntity vehicle);
    VehicleEntity partialUpdate(Long id, VehicleEntity authorEntity);
    void deleteVehicle(Long vehicleId);

}
