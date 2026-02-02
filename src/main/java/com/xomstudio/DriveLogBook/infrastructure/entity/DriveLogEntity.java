package com.xomstudio.DriveLogBook.infrastructure.entity;

import com.xomstudio.DriveLogBook.domain.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "drive_logs")
public class DriveLogEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private LocalDateTime driveStart;

    @Basic
    private LocalDateTime driveEnd;

    private int mileageAtStart;

    private int mileageAtEnd;

    private int kilometersDriven;

    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private VehicleEntity vehicleEntity;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn()
//    private VehicleEntity vehicleEntity;
}
