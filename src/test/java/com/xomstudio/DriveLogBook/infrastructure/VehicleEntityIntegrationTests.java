package com.xomstudio.DriveLogBook.infrastructure;


import com.xomstudio.DriveLogBook.TestDataUtil;
import com.xomstudio.DriveLogBook.api.VehicleRepository;
import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VehicleEntityIntegrationTests {

    private VehicleRepository underTest;

    @Autowired
    public VehicleEntityIntegrationTests(VehicleRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void vehicleCanBeCreatedAndRecalled(){
        testVehicleA.setId(null);
        underTest.save(testVehicleA);
        Optional<VehicleEntity> result = underTest.findById(testVehicleA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testVehicleA);
    }


    @Test
    public void multipleVehiclesCanBeCreatedAndRecalled() {
        testVehicleA.setId(null);
        underTest.save(testVehicleA);
        testVehicleB.setId(null);
        underTest.save(testVehicleB);

        Iterable<VehicleEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(2).
                containsExactly(testVehicleA, testVehicleB);
    }

    @Test
    public void vehicleCaBeUpdated(){
        testVehicleA.setCarLicensePlate("UPDATED");
        testVehicleA.setId(null);
        underTest.save(testVehicleA);
        Optional<VehicleEntity> result = underTest.findById(testVehicleA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testVehicleA);

    }



    private VehicleEntity testVehicleA = VehicleEntity.builder()
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

    private VehicleEntity testVehicleB = VehicleEntity.builder()
            .id(1L)
            .carLicensePlate("B-BB 888")
            .firstRegistration(LocalDate.of(2025, Month.FEBRUARY, 22))
            .mileage(16384)
            .vin("Benz VIN")
            .carBrand("Mercedes")
            .carModel("190E")
            .carColor("Silver")
            .enginePower(156)
            .petrol(Fuel.GASOLINE)
            .build();

}
