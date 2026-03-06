package com.xomstudio.DriveLogBook.infrastructure;

import com.xomstudio.DriveLogBook.domain.Fuel;
import com.xomstudio.DriveLogBook.domain.Trip;
import com.xomstudio.DriveLogBook.infrastructure.persistance.DriveLogEntity;
import com.xomstudio.DriveLogBook.infrastructure.persistance.DriveLogJPARepository;
import com.xomstudio.DriveLogBook.infrastructure.persistance.VehicleEntity;
import com.xomstudio.DriveLogBook.infrastructure.persistance.VehicleJPARepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DriveLogEntityIntegrationTests {

    private DriveLogJPARepository driveLogJPARepository;
    private VehicleJPARepository vehicleJPARepository;


    @Autowired
    public DriveLogEntityIntegrationTests(DriveLogJPARepository driveLogJPARepository, VehicleJPARepository vehicleJPARepository) {
        this.driveLogJPARepository = driveLogJPARepository;
        this.vehicleJPARepository = vehicleJPARepository;
    }


    @Test
    public void driveLogCanBeCreatedAndRecalled(){
        testVehicleEntity.setId(null);
        vehicleJPARepository.save(testVehicleEntity);
        testVehicleEntity.setId(1L);
        testDriveLogEntityA.setId(null);
        driveLogJPARepository.save(testDriveLogEntityA);
        Optional<DriveLogEntity> result = driveLogJPARepository.findById(testDriveLogEntityA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testDriveLogEntityA);
    }

    @Test
    public void testMultipleDriveLogsCantBeCreatedAndRecalled(){
        testVehicleEntity.setId(null);
        vehicleJPARepository.save(testVehicleEntity);
        testVehicleEntity.setId(1L);
        testDriveLogEntityA.setId(null);
        driveLogJPARepository.save(testDriveLogEntityA);
        testDriveLogEntityB.setId(null);
        driveLogJPARepository.save(testDriveLogEntityB);

        Iterable<DriveLogEntity> result = driveLogJPARepository.findAll();
        assertThat(result)
                .hasSize(2).
                containsExactly(testDriveLogEntityA, testDriveLogEntityB);
    }


    private VehicleEntity testVehicleEntity = VehicleEntity.builder()
            .id(1L)
            .carLicensePlate("Z-ZZ 111")
            .firstRegistration(LocalDate.of(2015, Month.JUNE, 12))
            .mileage(65536)
            .vin("Jaguar VIN")
            .carBrand("Jaguar")
            .carModel("X-Type")
            .carColor("Green")
            .enginePower(299)
            .petrol(Fuel.GASOLINE)
            .build();

    private DriveLogEntity testDriveLogEntityA = DriveLogEntity.builder()
            .id(1L)
            .driveStart(LocalDateTime.of(2026,01, 01, 10, 00, 00))
            .driveEnd(LocalDateTime.of(2026,01, 02, 11, 00, 00))
            .mileageAtStart(1000)
            .mileageAtEnd(1512)
            .distanceDriven(512)
            .trip(Trip.PRIVATE)
            .vehicleEntity(testVehicleEntity)
            .build();

    private DriveLogEntity testDriveLogEntityB = DriveLogEntity.builder()
            .id(1L)
            .driveStart(LocalDateTime.of(2020,10, 10, 10, 00, 00))
            .driveEnd(LocalDateTime.of(2020,12, 02, 11, 00, 00))
            .mileageAtStart(5000)
            .mileageAtEnd(5888)
            .distanceDriven(888)
            .trip(Trip.PRIVATE)
            .vehicleEntity(testVehicleEntity)
            .build();

}
