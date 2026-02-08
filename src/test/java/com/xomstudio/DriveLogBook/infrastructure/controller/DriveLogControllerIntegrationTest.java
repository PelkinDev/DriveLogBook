package com.xomstudio.DriveLogBook.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xomstudio.DriveLogBook.api.DriveLogService;
import com.xomstudio.DriveLogBook.api.VehicleService;
import com.xomstudio.DriveLogBook.domain.Fuel;
import com.xomstudio.DriveLogBook.domain.Trip;
import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import com.xomstudio.DriveLogBook.infrastructure.entity.DriveLogEntity;
import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class DriveLogControllerIntegrationTest {

    private DriveLogService driveLogService;
    private VehicleService vehicleService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public DriveLogControllerIntegrationTest(DriveLogService driveLogService, VehicleService vehicleService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.driveLogService = driveLogService;
        this.vehicleService = vehicleService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void shouldCreateOneDriveLog() throws Exception {
        testVehicleEntityA.setId(null);
        vehicleService.addNewVehicle(testVehicleEntityA);
        testDriveLogEntityA.setId(null);
        String driveLogJson = objectMapper.writeValueAsString(testDriveLogEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/vehicles/1/driveLogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(driveLogJson)
                ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createdDriveLogSuccessfullySaved() throws Exception {
        testVehicleEntityA.setId(null);
        vehicleService.addNewVehicle(testVehicleEntityA);
        testDriveLogDtoA.setId(null);
        String driveLogJson = objectMapper.writeValueAsString(testDriveLogDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/vehicles/1/driveLogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(driveLogJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.kilometersDriven").value(64)
        );
    }

    @Test
    public void driveLogsListReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/driveLogs")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void driveLogListReturnsListOfVehicles() throws Exception {
        testVehicleEntityA.setId(null);
        vehicleService.addNewVehicle(testVehicleEntityA);
        testDriveLogEntityA.setId(null);
        driveLogService.addNewDriveLog(testDriveLogEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/driveLogs")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].kilometersDriven").value(64)
        );
    }


    private VehicleEntity testVehicleEntityA = VehicleEntity.builder()
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
            .driveStart(LocalDateTime.of(LocalDate.of(2020, Month.JANUARY, 01), LocalTime.of(10, 30)))
            .driveEnd(LocalDateTime.of(LocalDate.of(2020, Month.JANUARY, 01), LocalTime.of(20, 45)))
            .mileageAtStart(1000)
            .mileageAtEnd(1064)
            .kilometersDriven(64)
            .trip(Trip.PRIVATE)
            .vehicleEntity(testVehicleEntityA)
            .build();

    private DriveLogDTO testDriveLogDtoA = DriveLogDTO.builder()
            .id(1L)
            .driveStart(LocalDateTime.of(LocalDate.of(2020, Month.JANUARY, 01), LocalTime.of(10, 30)))
            .driveEnd(LocalDateTime.of(LocalDate.of(2020, Month.JANUARY, 01), LocalTime.of(20, 45)))
            .mileageAtStart(1000)
            .mileageAtEnd(1064)
            .kilometersDriven(64)
            .trip(Trip.PRIVATE)
            .vehicleEntity(testVehicleEntityA)
            .build();

}



