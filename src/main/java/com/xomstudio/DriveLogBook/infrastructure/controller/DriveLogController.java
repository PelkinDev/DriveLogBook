package com.xomstudio.DriveLogBook.infrastructure.controller;

import com.xomstudio.DriveLogBook.api.DriveLogService;
import com.xomstudio.DriveLogBook.api.Mapper;
import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import com.xomstudio.DriveLogBook.domain.dto.VehicleDTO;
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

    private final DriveLogService driveLogService;

    private Mapper<DriveLogEntity, DriveLogDTO> mapper;


    private VehicleEntity vehicleEntity;

    @Autowired
    public DriveLogController(DriveLogService driveLogService, Mapper<DriveLogEntity, DriveLogDTO> mapper) {
        this.driveLogService = driveLogService;
        this.mapper = mapper;
    }

    @GetMapping(path = "driveLog")
    public List<DriveLogDTO> getDriveLogs(){
        List<DriveLogEntity> driveLogs = driveLogService.getDriveLogs();
        return driveLogs.stream()
                .map(mapper::mapFromEntityToDTO)
                .collect(Collectors.toList());
    }


    @PostMapping(path = "/vehicle/{vehicleId}/driveLog")
    public ResponseEntity<DriveLogDTO> addNewDriveLog(@PathVariable("vehicleId") Long vehicleId, @RequestBody DriveLogDTO driveLogDTO){
//        pathvariable to save right Key vehicle to drivelog
        vehicleEntity = driveLogDTO.getVehicleEntity();
        vehicleEntity.setId(vehicleId);
        driveLogDTO.setVehicleEntity(vehicleEntity);

        DriveLogEntity driveLogEntity = mapper.mapFromDTOToEntity(driveLogDTO);
        driveLogService.addNewDriveLog(driveLogEntity);
        return new ResponseEntity<>(mapper.mapFromEntityToDTO(driveLogEntity), HttpStatus.CREATED);
    }



//    @GetMapping(path = "/vehicle/{vehicleId}/driveLog")
//    public List<DriveLogDTO> getAllDriveLogsFromOneVehicle(@PathVariable("vehicleId") Long vehicleId){
//        List<DriveLogEntity> driveLogs = driveLogService.getAllDriveLogsFromOneVehicle(vehicleId);
//        return driveLogs.stream()
//                .map(mapper::mapFromEntityToDTO)
//                .collect(Collectors.toList());
//    }


}
