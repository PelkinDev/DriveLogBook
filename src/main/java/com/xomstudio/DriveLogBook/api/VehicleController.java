package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.infrastructure.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getVehicles(){
        return vehicleService.getVehicles();
    }

    @GetMapping(path = "{vehicleId}")
    public Vehicle getVehicleById(@PathVariable("vehicleId") Long vehicleId){
        return vehicleService.getVehicles().get(Math.toIntExact(vehicleId));
    }

    @PostMapping
    public void addNewVehicle(@RequestBody Vehicle vehicle){
        vehicleService.addNewVehicle(vehicle);
    }

    @DeleteMapping(path = "{vehicleId}")
    public void deleteVehicle(@PathVariable("vehicleId")Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
    }

}
