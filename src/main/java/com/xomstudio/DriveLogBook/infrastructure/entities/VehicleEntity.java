package com.xomstudio.DriveLogBook.infrastructure.entities;

import com.xomstudio.DriveLogBook.infrastructure.Fuel;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicles")
public class VehicleEntity {
    @Id
    @SequenceGenerator(
            name = "vehicle_sequence",
            sequenceName = "vehicle_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicle_sequence"
    )
    private Long id;

//    @Column(nullable = false)
    private String carLicensePlate;

    private LocalDate firstRegistration;

    private int mileage;

    private String vin;

    private String carBrand;

    private String carModel;

    private String carColor;

    private int enginePower;

    private Fuel petrol;

}
