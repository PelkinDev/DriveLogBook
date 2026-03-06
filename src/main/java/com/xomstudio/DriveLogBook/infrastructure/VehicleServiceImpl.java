package com.xomstudio.DriveLogBook.infrastructure;

import com.xomstudio.DriveLogBook.domain.dto.VehicleDTO;
import com.xomstudio.DriveLogBook.api.VehicleService;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleCantBeCreatedBookException;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleNotFoundBookException;
import com.xomstudio.DriveLogBook.infrastructure.persistance.VehicleRepositoryAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepositoryAdapter vehicleRepositoryAdapter;

    @Autowired
    public VehicleServiceImpl(VehicleRepositoryAdapter vehicleRepositoryAdapter) {
        this.vehicleRepositoryAdapter = vehicleRepositoryAdapter;
    }

    @Override
    public boolean isExists(Long id) {
        return vehicleRepositoryAdapter.existsById(id);
    }

    @Override
    public List<VehicleDTO> getVehicles() {
        return vehicleRepositoryAdapter.findAll();
    }

    @Override
    public Optional<VehicleDTO> getVehicleById(Long vehicleId) {
        boolean vehicleOptional = vehicleRepositoryAdapter.existsById(vehicleId);
        if(!vehicleOptional){
            log.warn("vehicle with ID: {} not exists", vehicleId);
            throw new VehicleNotFoundBookException("vehicle with id " + vehicleId + " not exists");
        }
        return vehicleRepositoryAdapter.getVehicleById(vehicleId);
    }

    @Override
    public void addNewVehicle(VehicleDTO vehicleDTO) {
        VehicleValidator.validate(vehicleDTO);
        Optional<VehicleDTO> vehicleOptional = vehicleRepositoryAdapter.getVehicleByCarLicensePlate(vehicleDTO.getCarLicensePlate());
        if(vehicleOptional.isPresent()){
            log.error("the vehicleDTO with this license plate already exists: {}", vehicleDTO.getCarLicensePlate());
            throw new VehicleCantBeCreatedBookException("the vehicleDTO with " + vehicleDTO.getCarLicensePlate() + " license plate already exists");
        }
        log.info("new vehicleDTO was created: {}", vehicleDTO.getCarLicensePlate());
        vehicleRepositoryAdapter.store(vehicleDTO);
    }

    @Override
    public void partialUpdate(Long id, VehicleDTO vehicleDTO) {
        VehicleValidator.validate(vehicleDTO);
        if(!vehicleRepositoryAdapter.existsById(id)){
            throw new VehicleNotFoundBookException("vehicle with id " + id + " not exists");
        }
        log.info("vehicle with id: {} updated", vehicleDTO.getId());
        vehicleRepositoryAdapter.partialUpdate(id, vehicleDTO);
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        boolean vehicleExist = vehicleRepositoryAdapter.existsById(vehicleId);
        if(!vehicleExist){
            log.warn("Vehicle with ID: {} not exists", vehicleId);
            throw new VehicleNotFoundBookException("vehicle with id " + vehicleId + " not exists");
        }
        log.info("Vehicle with ID: {} deleted", vehicleId);
        vehicleRepositoryAdapter.deleteById(vehicleId);
    }

}

