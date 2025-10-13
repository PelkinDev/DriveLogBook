package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.infrastructure.entities.VehicleEntity;
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

    public List<VehicleEntity> getVehicles(){
        return vehicleRepository.findAll();
    }

/*    public Optional<Vehicle> getVehicleById(Long vehicleId){
        return vehicleRepository.findById(vehicleId);
    }
*/

    public Optional<VehicleEntity> getVehicleById(Long vehicleId){
        boolean vehicleOpt = vehicleRepository.existsById(vehicleId);
        if(!vehicleOpt){
            throw new IllegalStateException("vehicle with id " + vehicleId + " not exists.");
        }
        return vehicleRepository.findById(vehicleId);
    }



    public void addNewVehicle(VehicleEntity vehicle){
        Optional<VehicleEntity> vehicleOptional = vehicleRepository.findVehicleByCarLicensePlate(vehicle.getCarLicensePlate());
        if(vehicleOptional.isPresent()){
            throw new IllegalStateException("the vehicle with this license plate already exists");
        }
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long vehicleId){
        boolean vehicleExist = vehicleRepository.existsById(vehicleId);
        if(!vehicleExist){
            throw new IllegalStateException("vehicle with id " + vehicleExist + " not exists.");
        }
        vehicleRepository.deleteById(vehicleId);
    }

}
