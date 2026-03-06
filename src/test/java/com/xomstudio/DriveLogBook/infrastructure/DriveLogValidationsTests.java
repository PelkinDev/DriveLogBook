package com.xomstudio.DriveLogBook.infrastructure;

import com.xomstudio.DriveLogBook.domain.Trip;
import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.DriveLogValidateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DriveLogValidationsTests {

    @Test
    public void testValidateDriveLog(){
        DriveLogValidator.validate(testDriveLog);
    }

    @Test
    public void testDriveStartNotNullOrEmpty(){
        testDriveLog.setDriveStart(null);
        try {
            DriveLogValidator.validate(testDriveLog);
            fail();
        } catch (DriveLogValidateException e){
            assertThat("DriveStart can not be null or empty").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testDriveStartNotInFuture(){
        testDriveLog.setDriveStart(LocalDateTime.of(2099,1, 1, 10, 0, 0));
        try {
            DriveLogValidator.validate(testDriveLog);
            fail();
        } catch (DriveLogValidateException e){
            assertThat("DriveStart can not be in the future").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testMileage(){
        testDriveLog.setMileageAtStart(1000);
        testDriveLog.setMileageAtEnd(2000);
        testDriveLog.setDistanceDriven(500);
        try {
            DriveLogValidator.validate(testDriveLog);
            fail();
        } catch (DriveLogValidateException e){
            assertThat("Mileage on start is different to mileage on end + distance").isEqualTo(e.getMessage());
        }
    }


    private final DriveLogDTO testDriveLog = DriveLogDTO.builder()
            .id(1L)
            .driveStart(LocalDateTime.of(2026,1, 1, 10, 0, 0))
            .driveEnd(LocalDateTime.of(2026,1, 2, 11, 0, 0))
            .mileageAtStart(1000)
            .mileageAtEnd(1512)
            .distanceDriven(512)
            .trip(Trip.PRIVATE)
            .vehicleDto(null)
            .build();
}