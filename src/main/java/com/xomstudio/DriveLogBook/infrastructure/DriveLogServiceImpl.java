package com.xomstudio.DriveLogBook.infrastructure;

import com.xomstudio.DriveLogBook.api.DriveLogRepository;
import com.xomstudio.DriveLogBook.api.DriveLogService;
import com.xomstudio.DriveLogBook.infrastructure.entity.DriveLogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DriveLogServiceImpl implements DriveLogService {

    private final DriveLogRepository driveLogRepository;

    @Autowired
    public DriveLogServiceImpl(DriveLogRepository driveLogRepository) {
        this.driveLogRepository = driveLogRepository;
    }


    @Override
    public boolean isExists(Long id) {
        return driveLogRepository.existsById(id);
    }

    @Override
    public List<DriveLogEntity> getDriveLogs() {
        return driveLogRepository.findAll();
    }

    @Override
    public void addNewDriveLog(DriveLogEntity driveLogEntity) {
        log.info("new drive log was created.");
        driveLogRepository.save(driveLogEntity);
    }

    @Override
    public void deleteDriveLog(Long id) {
        log.info("drive log was deleted");
        driveLogRepository.deleteById(id);
    }
}
