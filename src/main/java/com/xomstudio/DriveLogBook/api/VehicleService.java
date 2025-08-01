package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.infrastructure.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }

    public void addNewVehicle(Vehicle vehicle){
        Optional<Vehicle> vehicleOptional = vehicleRepository.findVehicleByCarLicensePlate(vehicle.getCarLicensePlate());
        if(vehicleOptional.isPresent()){
            throw new IllegalStateException("the vehicle with this license plate already exists");
        }
        vehicleRepository.save(vehicle);
        System.out.println(vehicle);
    }

    public void deleteVehicle(Long vehicleId){
        boolean vehicleExist = vehicleRepository.existsById(vehicleId);
        if(!vehicleExist){
            throw new IllegalStateException("vehicle with id " + vehicleExist + " not exists.");
        }
        vehicleRepository.deleteById(vehicleId);
    }






}
