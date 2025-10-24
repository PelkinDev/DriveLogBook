package com.xomstudio.DriveLogBook;

import com.xomstudio.DriveLogBook.domain.Fuel;
import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;

import java.time.LocalDate;
import java.time.Month;

public class TestDataUtil {

    private TestDataUtil() {
    }

    public static VehicleEntity createTestVehicleA() {
        return VehicleEntity.builder()
                .id(1L)
                .carLicensePlate("F-AA 1000")
                .firstRegistration(LocalDate.of(2022, Month.OCTOBER, 12))
                .mileage(15000)
                .vin("HONDA VIN")
                .carBrand("Honda")
                .carModel("Rebel")
                .carColor("black")
                .enginePower(87)
                .petrol(Fuel.GASOLINE)
                .build();
    }

    public static VehicleEntity createTestVehicleB() {
        return VehicleEntity.builder()
                .id(2L)
                .carLicensePlate("M-OO 999")
                .firstRegistration(LocalDate.of(2015, Month.JANUARY, 22))
                .mileage(9000)
                .vin("MAZDA VIN")
                .carBrand("Mazda")
                .carModel("mx-5")
                .carColor("red")
                .enginePower(112)
                .petrol(Fuel.DIESEL)
                .build();
    }

}
