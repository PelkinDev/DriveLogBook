package com.xomstudio.DriveLogBook.infrastructure.controller;

import com.xomstudio.DriveLogBook.api.Mapper;
import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import com.xomstudio.DriveLogBook.infrastructure.DriveLogServiceImpl;
import com.xomstudio.DriveLogBook.infrastructure.persistance.DriveLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1")
public class DriveLogController {

    private final DriveLogServiceImpl driveLogServiceImpl;

    private final Mapper<DriveLogEntity, DriveLogDTO> mapper;


    @Autowired
    public DriveLogController(DriveLogServiceImpl driveLogServiceImpl, Mapper<DriveLogEntity, DriveLogDTO> mapper) {
        this.driveLogServiceImpl = driveLogServiceImpl;
        this.mapper = mapper;
    }


    @GetMapping(path = "/driveLogs")
    public List<DriveLogDTO> getDriveLogs(){
        List<DriveLogDTO> driveLogs = driveLogServiceImpl.getDriveLogs();
        return driveLogs.stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/vehicles/{vehicleId}/driveLogs")
    public List<DriveLogDTO> getAllDriveLogsFromOneVehicle(@PathVariable("vehicleId") Long vehicleId){
        List<DriveLogDTO> driveLogs = driveLogServiceImpl.getAllDriveLogsFromOneVehicle(vehicleId);
        return driveLogs.stream().collect(Collectors.toList());
    }

    @PostMapping(path = "/vehicles/{vehicleId}/driveLogs")
    public ResponseEntity<DriveLogDTO> addNewDriveLog(@PathVariable("vehicleId") Long vehicleId, @RequestBody DriveLogDTO driveLogDTO){
        driveLogServiceImpl.addNewDriveLog(vehicleId, driveLogDTO);
        return new ResponseEntity<>(driveLogDTO, HttpStatus.CREATED);
    }

}
