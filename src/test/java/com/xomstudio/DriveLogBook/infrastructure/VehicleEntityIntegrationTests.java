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
    public void VehicleCanBeCreatedAndRecalled(){
        VehicleEntity vehicleEntity = TestDataUtil.createTestVehicleA();
        vehicleEntity.setId(null);
        underTest.save(vehicleEntity);
        Optional<VehicleEntity> result = underTest.findById(vehicleEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(vehicleEntity);
    }


    @Test
    public void MultipleVehiclesCanBeCreatedAndRecalled() {
        VehicleEntity vehicleEntityA = TestDataUtil.createTestVehicleA();
        vehicleEntityA.setId(null);
        underTest.save(vehicleEntityA);
        VehicleEntity vehicleEntityB = TestDataUtil.createTestVehicleA();
        vehicleEntityB.setId(null);
        underTest.save(vehicleEntityB);

        Iterable<VehicleEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(2).
                containsExactly(vehicleEntityA, vehicleEntityB);
    }
}
