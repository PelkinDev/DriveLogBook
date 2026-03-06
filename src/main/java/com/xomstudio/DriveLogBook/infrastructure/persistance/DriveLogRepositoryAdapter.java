package com.xomstudio.DriveLogBook.infrastructure.persistance;

import com.xomstudio.DriveLogBook.api.DriveLogRepository;
import com.xomstudio.DriveLogBook.api.Mapper;
import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DriveLogRepositoryAdapter implements DriveLogRepository {

    private final DriveLogJPARepository driveLogJPARepository;
    private final Mapper<DriveLogEntity, DriveLogDTO> mapper;


    @Autowired
    public DriveLogRepositoryAdapter(DriveLogJPARepository driveLogJPARepository, Mapper<DriveLogEntity, DriveLogDTO> mapper) {
        this.driveLogJPARepository = driveLogJPARepository;
        this.mapper = mapper;
    }


    public List<DriveLogDTO> getAllDriveLogs(){
        List<DriveLogEntity> driveLogs = driveLogJPARepository.findAll();
        return driveLogs.stream()
                .map(mapper::mapFromEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriveLogDTO> getAllDriveLogsByVehicleId(Long id) {
        List<DriveLogEntity> driveLogs = driveLogJPARepository.findAllByVehicleId(id);
        return driveLogs.stream()
                .map(mapper::mapFromEntityToDTO)
                .collect(Collectors.toList());
    }

    public void addDriveLog(DriveLogDTO driveLogDTO) {
        driveLogJPARepository.save(mapper.mapFromDTOToEntity(driveLogDTO));
    }

}
