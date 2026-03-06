package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface DriveLogRepository {

    List<DriveLogDTO> getAllDriveLogsByVehicleId(Long id);

}
