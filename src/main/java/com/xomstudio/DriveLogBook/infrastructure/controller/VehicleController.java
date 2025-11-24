package com.xomstudio.DriveLogBook.infrastructure.controller;

import com.xomstudio.DriveLogBook.infrastructure.VehicleServiceImpl;
import com.xomstudio.DriveLogBook.domain.dto.VehicleDTO;
import com.xomstudio.DriveLogBook.api.Mapper;
import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleCantBeCreatedException;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleNoExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {

    private final VehicleServiceImpl vehicleServiceImpl;
    private Mapper<VehicleEntity, VehicleDTO> mapper;

    @Autowired
    public VehicleController(VehicleServiceImpl vehicleServiceImpl, Mapper<VehicleEntity, VehicleDTO> mapper) {
        this.vehicleServiceImpl = vehicleServiceImpl;
        this.mapper = mapper;
    }

    @GetMapping
    public List<VehicleDTO> getVehicles(){
        List<VehicleEntity> vehicles = vehicleServiceImpl.getVehicles();
        return vehicles.stream()
                .map(mapper::mapFromEntityToDTO)
                .collect(Collectors.toList());
    }


    @GetMapping(path = "{vehicleId}")
    public Optional<VehicleEntity> getVehicleById(@PathVariable("vehicleId") Long vehicleId) throws VehicleNoExistsException {
        if(!vehicleServiceImpl.isExists(vehicleId)){
            throw new VehicleNoExistsException("vehicle with id " + vehicleId + " not exists");
        }
        return vehicleServiceImpl.getVehicleById(vehicleId);
    }


    @PostMapping
    public ResponseEntity<VehicleDTO> addNewVehicles(@RequestBody VehicleDTO vehicleDTO) throws VehicleCantBeCreatedException {
        VehicleEntity vehicleEntity = mapper.mapFromDTOToEntity(vehicleDTO);
        vehicleServiceImpl.addNewVehicle(vehicleEntity);
//        VehicleEntity savedVehicleEntity = vehicleService.addNewVehicle(vehicleEntity);
        return new ResponseEntity<>(mapper.mapFromEntityToDTO(vehicleEntity), HttpStatus.CREATED);
    }

    @PatchMapping(path = "{vehicleId}")
    public ResponseEntity<VehicleDTO> partialUpdate(@PathVariable("vehicleId") Long vehicleId, @RequestBody VehicleDTO vehicleDTO) throws VehicleNoExistsException {

        if(!vehicleServiceImpl.isExists(vehicleId)){
            throw new VehicleNoExistsException("vehicle with id " + vehicleId + " not exists");
        }

        VehicleEntity vehicleEntity = mapper.mapFromDTOToEntity(vehicleDTO);
        VehicleEntity updatedVehicle = vehicleServiceImpl.partialUpdate(vehicleId, vehicleEntity);
        return new ResponseEntity<>(mapper.mapFromEntityToDTO(updatedVehicle), HttpStatus.OK);
    }


    @DeleteMapping(path = "{vehicleId}")
    public ResponseEntity deleteVehicle(@PathVariable("vehicleId")Long vehicleId) throws VehicleNoExistsException {
        vehicleServiceImpl.deleteVehicle(vehicleId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
