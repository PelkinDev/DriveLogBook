package com.xomstudio.DriveLogBook.domain.dto;

import com.xomstudio.DriveLogBook.domain.Trip;
import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriveLogDTO {

    private Long id;
    private LocalDateTime driveStart;
    private LocalDateTime driveEnd;
    private int mileageAtStart;
    private int mileageAtEnd;
    private int kilometersDriven;
    private Trip trip;
    private VehicleEntity vehicleEntity;

}
