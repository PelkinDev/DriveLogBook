package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleCantBeCreatedException;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleNoExistsException;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    boolean isExists(Long id);
    List<VehicleEntity> getVehicles();
    Optional<VehicleEntity> getVehicleById(Long vehicleId) throws VehicleCantBeCreatedException, VehicleNoExistsException;
    void addNewVehicle(VehicleEntity vehicle) throws VehicleNoExistsException, VehicleCantBeCreatedException;
    VehicleEntity partialUpdate(Long id, VehicleEntity authorEntity) throws VehicleNoExistsException;
    void deleteVehicle(Long vehicleId) throws VehicleNoExistsException;

}
