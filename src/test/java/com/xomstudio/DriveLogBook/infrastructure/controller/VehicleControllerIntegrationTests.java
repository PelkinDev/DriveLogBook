package com.xomstudio.DriveLogBook.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xomstudio.DriveLogBook.api.VehicleService;
import com.xomstudio.DriveLogBook.domain.Fuel;
import com.xomstudio.DriveLogBook.domain.dto.VehicleDTO;
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
import java.time.Month;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class VehicleControllerIntegrationTests {

    private final VehicleService vehicleService;

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;


    @Autowired
    public VehicleControllerIntegrationTests(MockMvc mockMvc, VehicleService vehicleService) {
        this.mockMvc = mockMvc;
        this.vehicleService = vehicleService;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }


    @Test
    public void shouldCreateOneVehicle() throws Exception {
        testVehicleDtoA.setId(null);
        String vehicleJson = objectMapper.writeValueAsString(testVehicleDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createdVehicleSuccessfullySaved() throws Exception{
        testVehicleDtoA.setId(null);
        String vehicleJson = objectMapper.writeValueAsString(testVehicleDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.carLicensePlate").value("B-BB 888")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.mileage").value(16384)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.enginePower").isNumber()
        );
    }

    @Test
    public void vehiclesListReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void vehicleListReturnsListOfVehicles() throws Exception{
        testVehicleDtoB.setId(null);
        vehicleService.addNewVehicle(testVehicleDtoB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].carLicensePlate").value("Z-ZZ 111")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].mileage").value(65536)
        );
    }

    @Test
    public void getVehiclesReturnsHttp200IfExist() throws Exception{
        testVehicleDtoA.setId(null);
        vehicleService.addNewVehicle(testVehicleDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/vehicles/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void partialUpdatedVehicleReturnsHttp200() throws Exception{
        testVehicleDtoA.setId(null);
        vehicleService.addNewVehicle(testVehicleDtoA);
        String vehicleJson = objectMapper.writeValueAsString(testVehicleDtoB);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/v1/vehicles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void partialUpdateUpdatedVehicle() throws Exception{
        testVehicleDtoA.setId(null);
        vehicleService.addNewVehicle(testVehicleDtoA);
        String vehicleDtoUpJson = objectMapper.writeValueAsString(testVehicleDtoB);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/v1/vehicles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleDtoUpJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(testVehicleDtoB.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.carLicensePlate").value(testVehicleDtoB.getCarLicensePlate())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.mileage").value(testVehicleDtoB.getMileage())
        );
    }


    private final VehicleDTO testVehicleDtoA = VehicleDTO.builder()
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

    private final VehicleDTO testVehicleDtoB = VehicleDTO.builder()
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

}
