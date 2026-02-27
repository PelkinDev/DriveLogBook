package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.domain.dto.VehicleDTO;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    boolean isExists(Long id);
    List<VehicleDTO> getVehicles();
    Optional<VehicleDTO> getVehicleById(Long vehicleId);
    void addNewVehicle(VehicleDTO vehicle);
    void partialUpdate(Long id, VehicleDTO vehicleDTO);
    void deleteVehicle(Long vehicleId);

}
