package com.xomstudio.DriveLogBook.infrastructure;

import com.xomstudio.DriveLogBook.domain.Fuel;
import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleValidateException;

import java.time.LocalDate;

public class VehicleValidation {

    public static void valideVehicle(VehicleEntity vehicleEntity){
        valideteCarPlate(vehicleEntity.getCarLicensePlate());
        validateFirstRegistration(vehicleEntity.getFirstRegistration());
        validateMileage(vehicleEntity.getMileage());
        validateVin(vehicleEntity.getVin());
        validateCarBrand(vehicleEntity.getCarBrand());
        validateCarModel(vehicleEntity.getCarModel());
        validateCarColor(vehicleEntity.getCarColor());
        validateEnginePower(vehicleEntity.getEnginePower());
        validatePetrol(vehicleEntity.getPetrol());
    }


    private static void valideteCarPlate(String carPlate){
        if(carPlate == null || carPlate.isBlank()){
            throw new VehicleValidateException("Car plate must be defienied");
        }
    }

    private static void validateFirstRegistration(LocalDate firstRegistration) {
        if(firstRegistration == null ){
            throw new VehicleValidateException("Registration can not be null or empty");
        }
        LocalDate now = LocalDate.now();
        if(now.isBefore(firstRegistration)){
            throw new VehicleValidateException("Registration can not be in the future");
        }
    }

    private static void validateMileage(int mileage){
        if(mileage < 0){
            throw new VehicleValidateException("Mileage must be bigger or equals than 0");
        }
    }

    private static void validateVin(String vin){
        if(vin == null || vin.isBlank()){
            throw new VehicleValidateException("VIN number cannot be null or empty");
        }
    }

    private static void validateCarBrand(String carBrand){
        if(carBrand == null || carBrand.isBlank()){
            throw new VehicleValidateException("Car brand cannot be null or empty");
        }
    }

    private static void validateCarModel(String carModel){
        if(carModel == null || carModel.isBlank()){
            throw new VehicleValidateException("Car model cannot be null or empty");
        }
    }

    private static void validateCarColor(String carColor){
        if(carColor == null || carColor.isBlank()){
            throw new VehicleValidateException("Color cannot be null or empty");
        }
    }

    private static void validateEnginePower(int enginePower){
        if(enginePower < 1){
            throw new VehicleValidateException("Engine power must be bigger than 0");
        }
    }

    private static void validatePetrol(Fuel fuel){
        if(!(fuel == Fuel.GASOLINE || fuel == Fuel.DIESEL ||fuel == Fuel.ELECTRIC)){
            throw new VehicleValidateException("Fuel false");
        }
    }

}