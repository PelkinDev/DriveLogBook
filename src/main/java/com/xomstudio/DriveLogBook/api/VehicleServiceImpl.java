package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public boolean isExists(Long id) {
        return vehicleRepository.existsById(id);
    }

    public List<VehicleEntity> getVehicles(){
        return vehicleRepository.findAll();
    }


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

    @Override
    public VehicleEntity partialUpdate(Long id, VehicleEntity vehicleEntity) {
        vehicleEntity.setId(id);

        return vehicleRepository.findById(id).map(existingVehicle -> {
            Optional.ofNullable(vehicleEntity.getCarLicensePlate()).ifPresent((existingVehicle::setCarLicensePlate));
            Optional.ofNullable(vehicleEntity.getFirstRegistration()).ifPresent((existingVehicle::setFirstRegistration));
            Optional.ofNullable(vehicleEntity.getMileage()).ifPresent((existingVehicle::setMileage));
            Optional.ofNullable(vehicleEntity.getVin()).ifPresent((existingVehicle::setVin));
            Optional.ofNullable(vehicleEntity.getCarBrand()).ifPresent((existingVehicle::setCarBrand));
            Optional.ofNullable(vehicleEntity.getCarModel()).ifPresent((existingVehicle::setCarModel));
            Optional.ofNullable(vehicleEntity.getCarColor()).ifPresent((existingVehicle::setCarColor));
            Optional.ofNullable(vehicleEntity.getEnginePower()).ifPresent((existingVehicle::setEnginePower));
            Optional.ofNullable(vehicleEntity.getPetrol()).ifPresent((existingVehicle::setPetrol));

            return vehicleRepository.save(existingVehicle);
        }).orElseThrow(() -> new RuntimeException("Vehicle does not exist"));

    }

    public void deleteVehicle(Long vehicleId){
        boolean vehicleExist = vehicleRepository.existsById(vehicleId);
        if(!vehicleExist){
            throw new IllegalStateException("vehicle with id " + vehicleId + " not exists.");
        }
        vehicleRepository.deleteById(vehicleId);
    }

}

