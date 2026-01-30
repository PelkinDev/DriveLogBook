package com.xomstudio.DriveLogBook.domain.dto;

import com.xomstudio.DriveLogBook.domain.Fuel;
import com.xomstudio.DriveLogBook.infrastructure.entity.DriveLogEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDTO {

    private Long id;
    private String carLicensePlate;
    private LocalDate firstRegistration;
    private int mileage;
    private String vin;
    private String carBrand;
    private String carModel;
    private String carColor;
    private int enginePower;
    private Fuel petrol;

    private List<DriveLogEntity> driveLogEntities;

}
