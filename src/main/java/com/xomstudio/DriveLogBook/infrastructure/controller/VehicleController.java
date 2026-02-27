package com.xomstudio.DriveLogBook.infrastructure.controller;

import com.xomstudio.DriveLogBook.infrastructure.VehicleServiceImpl;
import com.xomstudio.DriveLogBook.domain.dto.VehicleDTO;
import com.xomstudio.DriveLogBook.api.Mapper;
import com.xomstudio.DriveLogBook.infrastructure.persistance.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/vehicles")
public class VehicleController {

    private final VehicleServiceImpl vehicleServiceImpl;
    private final Mapper<VehicleEntity, VehicleDTO> mapper;


    @Autowired
    public VehicleController(VehicleServiceImpl vehicleServiceImpl, Mapper<VehicleEntity, VehicleDTO> mapper) {
        this.vehicleServiceImpl = vehicleServiceImpl;
        this.mapper = mapper;
    }


    @GetMapping
    public List<VehicleDTO> getVehicles(){
        List<VehicleDTO> vehicles = vehicleServiceImpl.getVehicles();
        return vehicles.stream().collect(Collectors.toList());
    }

    @GetMapping(path = "{vehicleId}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable("vehicleId") Long vehicleId){
        Optional<VehicleDTO> foundVehicle = vehicleServiceImpl.getVehicleById(vehicleId);
        if(foundVehicle.isPresent()){
            return new ResponseEntity<>(foundVehicle.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> addNewVehicles(@RequestBody VehicleDTO vehicleDTO){
        vehicleServiceImpl.addNewVehicle(vehicleDTO);
        return new ResponseEntity<>(vehicleDTO, HttpStatus.CREATED);
    }

    @PatchMapping(path = "{vehicleId}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable("vehicleId") Long vehicleId, @RequestBody VehicleDTO vehicleDTO){
        vehicleServiceImpl.partialUpdate(vehicleId, vehicleDTO);
        return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "{vehicleId}")
    public ResponseEntity deleteVehicle(@PathVariable("vehicleId")Long vehicleId){
        vehicleServiceImpl.deleteVehicle(vehicleId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
