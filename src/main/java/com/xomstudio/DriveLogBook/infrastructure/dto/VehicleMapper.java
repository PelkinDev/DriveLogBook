package com.xomstudio.DriveLogBook.infrastructure.dto;

public interface VehicleMapper<A, B> {

    B mapFromVehicleEntityToVehicleDTO(A a);

    A mapFromVehicleDTOToVehicleEntity(B b);

}