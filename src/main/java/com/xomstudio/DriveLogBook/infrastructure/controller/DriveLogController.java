package com.xomstudio.DriveLogBook.infrastructure.controller;

import com.xomstudio.DriveLogBook.api.Mapper;
import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import com.xomstudio.DriveLogBook.infrastructure.DriveLogServiceImpl;
import com.xomstudio.DriveLogBook.infrastructure.VehicleServiceImpl;
import com.xomstudio.DriveLogBook.infrastructure.entity.DriveLogEntity;
import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
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
    private final VehicleServiceImpl vehicleServiceImpl;

    private Mapper<DriveLogEntity, DriveLogDTO> mapper;


    @Autowired
    public DriveLogController(DriveLogServiceImpl driveLogServiceImpl, VehicleServiceImpl vehicleServiceImpl, Mapper<DriveLogEntity, DriveLogDTO> mapper) {
        this.driveLogServiceImpl = driveLogServiceImpl;
        this.vehicleServiceImpl = vehicleServiceImpl;
        this.mapper = mapper;
    }

    @GetMapping(path = "/driveLog")
    public List<DriveLogDTO> getDriveLogs(){
        List<DriveLogEntity> driveLogs = driveLogServiceImpl.getDriveLogs();
        return driveLogs.stream()
                .map(mapper::mapFromEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/vehicle/{vehicleId}/driveLog")
    public List<DriveLogDTO> getAllDriveLogsFromOneVehicle(@PathVariable("vehicleId") Long vehicleId){
        List<DriveLogEntity> driveLogs = driveLogServiceImpl.getAllDriveLogsFromOneVehicle(vehicleId);
        return driveLogs.stream()
                .map(mapper::mapFromEntityToDTO)
                .collect(Collectors.toList());
    }

//    @PostMapping(path = "/driveLog")
//    public ResponseEntity<DriveLogDTO> addNewDriveLog(@RequestBody DriveLogDTO driveLogDTO){
//        DriveLogEntity driveLogEntity = mapper.mapFromDTOToEntity(driveLogDTO);
//        driveLogServiceImpl.addNewDriveLog(driveLogEntity);
//        return new ResponseEntity<>(mapper.mapFromEntityToDTO(driveLogEntity), HttpStatus.CREATED);
//    }

    @PostMapping(path = "/vehicle/{vehicleId}/driveLog")
    public ResponseEntity<DriveLogDTO> addNewDriveLog(@PathVariable("vehicleId") Long vehicleId, @RequestBody DriveLogDTO driveLogDTO){
//        pathvariable to save right Key vehicle to drivelog
        VehicleEntity vehicleEntity = driveLogDTO.getVehicleEntity();
        vehicleEntity.setId(vehicleId);
        driveLogDTO.setVehicleEntity(vehicleEntity);

        DriveLogEntity driveLogEntity = mapper.mapFromDTOToEntity(driveLogDTO);
        driveLogServiceImpl.addNewDriveLog(driveLogEntity);
        return new ResponseEntity<>(mapper.mapFromEntityToDTO(driveLogEntity), HttpStatus.CREATED);
    }

}
