package com.xomstudio.DriveLogBook.infrastructure;

import com.xomstudio.DriveLogBook.api.VehicleRepository;
import com.xomstudio.DriveLogBook.api.VehicleService;
import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

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
        boolean vehicleOptional = vehicleRepository.existsById(vehicleId);
        if(!vehicleOptional){
            log.warn("vehicle with ID: {} not exists", vehicleId);
            throw new VehicleNotFoundException("vehicle with id " + vehicleId + " not exists");
        }
        return vehicleRepository.findById(vehicleId);
    }

    public void addNewVehicle(VehicleEntity vehicle){
        Optional<VehicleEntity> vehicleOptional = vehicleRepository.findVehicleByCarLicensePlate(vehicle.getCarLicensePlate());
        if(vehicleOptional.isPresent()){
            log.error("the vehicle with this license plate already exists: {}", vehicle.getCarLicensePlate());
//            throw new VehicleCantBeCreatedException("the vehicle with " + vehicle.getCarLicensePlate() + " license plate already exists");
        }
        log.info("new vehicle was created: {}", vehicle.getCarLicensePlate());
        vehicleRepository.save(vehicle);
    }

    @Override
    public VehicleEntity partialUpdate(Long id, VehicleEntity vehicleEntity){
        vehicleEntity.setId(id);

        return vehicleRepository.findById(id).map(existingVehicle -> {
            Optional.ofNullable(vehicleEntity.getCarLicensePlate()).ifPresent((existingVehicle::setCarLicensePlate));
            Optional.ofNullable(vehicleEntity.getFirstRegistration()).ifPresent((existingVehicle::setFirstRegistration));
            Optional.of(vehicleEntity.getMileage()).ifPresent((existingVehicle::setMileage));
            Optional.ofNullable(vehicleEntity.getVin()).ifPresent((existingVehicle::setVin));
            Optional.ofNullable(vehicleEntity.getCarBrand()).ifPresent((existingVehicle::setCarBrand));
            Optional.ofNullable(vehicleEntity.getCarModel()).ifPresent((existingVehicle::setCarModel));
            Optional.ofNullable(vehicleEntity.getCarColor()).ifPresent((existingVehicle::setCarColor));
            Optional.of(vehicleEntity.getEnginePower()).ifPresent((existingVehicle::setEnginePower));
            Optional.ofNullable(vehicleEntity.getPetrol()).ifPresent((existingVehicle::setPetrol));
            log.info("Vehicle with ID: {} not found", id);
            return vehicleRepository.save(existingVehicle);
        }).orElseThrow(() -> new VehicleNotFoundException());
    }

    public void deleteVehicle(Long vehicleId){
        boolean vehicleExist = vehicleRepository.existsById(vehicleId);
        if(!vehicleExist){
            log.warn("Vehicle with ID: {} not exists", vehicleId);
//            throw new VehicleNotFoundException("vehicle with id " + vehicleId + " not exists");
        }
        log.info("Vehicle with ID: {} deleted", vehicleId);
        vehicleRepository.deleteById(vehicleId);
    }

}

