package com.xomstudio.DriveLogBook.infrastructure.dto;

import com.xomstudio.DriveLogBook.infrastructure.Fuel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

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

}
