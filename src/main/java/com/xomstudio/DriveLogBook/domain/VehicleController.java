package com.xomstudio.DriveLogBook.domain;

import com.xomstudio.DriveLogBook.api.VehicleService;
import com.xomstudio.DriveLogBook.infrastructure.dto.VehicleDTO;
import com.xomstudio.DriveLogBook.infrastructure.dto.VehicleMapper;
import com.xomstudio.DriveLogBook.infrastructure.entities.VehicleEntity;
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

    private final VehicleService vehicleService;
    private VehicleMapper<VehicleEntity, VehicleDTO> vehicleMapper;

    @Autowired
    public VehicleController(VehicleService vehicleService, VehicleMapper<VehicleEntity, VehicleDTO> vehicleMapper) {
        this.vehicleService = vehicleService;
        this.vehicleMapper = vehicleMapper;
    }

    @GetMapping
    public List<VehicleDTO> getVehicles(){
        List<VehicleEntity> vehicles = vehicleService.getVehicles();
        return vehicles.stream()
                .map(vehicleMapper::mapFromVehicleEntityToVehicleDTO)
                .collect(Collectors.toList());
    }


    @GetMapping(path = "{vehicleId}")
    public Optional<VehicleEntity> getVehicleById(@PathVariable("vehicleId") Long vehicleId){
        return vehicleService.getVehicleById(vehicleId);
    }


    @PostMapping
    public ResponseEntity<VehicleDTO> addNewVehicles(@RequestBody VehicleDTO vehicleDTO) {
        VehicleEntity vehicleEntity = vehicleMapper.mapFromVehicleDTOToVehicleEntity(vehicleDTO);
        vehicleService.addNewVehicle(vehicleEntity);
//        VehicleEntity savedVehicleEntity = vehicleService.addNewVehicle(vehicleEntity);
        return new ResponseEntity<>(vehicleMapper.mapFromVehicleEntityToVehicleDTO(vehicleEntity), HttpStatus.CREATED);
    }


    @DeleteMapping(path = "{vehicleId}")
    public void deleteVehicle(@PathVariable("vehicleId")Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
    }

}
