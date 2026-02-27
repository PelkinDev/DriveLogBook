package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.domain.dto.VehicleDTO;

import java.util.Optional;

public interface VehicleRepository {

    Optional<VehicleDTO> getVehicleByCarLicensePlate(String carLicensePlate);
}
