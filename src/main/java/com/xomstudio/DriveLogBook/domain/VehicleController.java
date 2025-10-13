package com.xomstudio.DriveLogBook.domain;

import com.xomstudio.DriveLogBook.api.VehicleService;
import com.xomstudio.DriveLogBook.infrastructure.entities.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<VehicleEntity> getVehicles(){
        return vehicleService.getVehicles();
    }

    @GetMapping(path = "{vehicleId}")
    public Optional<VehicleEntity> getVehicleById(@PathVariable("vehicleId") Long vehicleId){
        return vehicleService.getVehicleById(vehicleId);
    }

    @PostMapping
    public void addNewVehicle(@RequestBody VehicleEntity vehicle){
        vehicleService.addNewVehicle(vehicle);
    }

    @DeleteMapping(path = "{vehicleId}")
    public void deleteVehicle(@PathVariable("vehicleId")Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
    }

}
