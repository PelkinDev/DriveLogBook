package com.xomstudio.DriveLogBook.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xomstudio.DriveLogBook.api.VehicleService;
import com.xomstudio.DriveLogBook.domain.Fuel;
import com.xomstudio.DriveLogBook.domain.dto.VehicleDTO;
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
import java.time.Month;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class VehicleControllerIntegrationTests {

    private VehicleService vehicleService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;


    @Autowired
    public VehicleControllerIntegrationTests(MockMvc mockMvc, VehicleService vehicleService) {
        this.mockMvc = mockMvc;
        this.vehicleService = vehicleService;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }


    @Test
    public void shouldCreateOneVehicle() throws Exception {
        testVehicleEntityA.setId(null);
        String vehicleJson = objectMapper.writeValueAsString(testVehicleEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createdVehicleSuccessfullySaved() throws Exception{
        testVehicleDtoA.setId(null);
        String vehicleJson = objectMapper.writeValueAsString(testVehicleDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.carLicensePlate").value("B-BB 888")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.mileage").value(16384)
        );
    }

    @Test
    public void vehiclesListReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void vehicleListReturnsListOfVehicles() throws Exception{
        testVehicleEntityA.setId(null);
        vehicleService.addNewVehicle(testVehicleEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/vehicle")
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
    public void getVehicleReturnsHttp200IfExist() throws Exception{
        testVehicleEntityA.setId(null);
        vehicleService.addNewVehicle(testVehicleEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/vehicle/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void partialUpdatedVehicleReturnsHttp200() throws Exception{
        testVehicleEntityA.setId(null);
        vehicleService.addNewVehicle(testVehicleEntityA);
        String vehicleJson = objectMapper.writeValueAsString(testVehicleDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/v1/vehicle/" + testVehicleEntityA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }



//    @Test
//    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
//        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
//        AuthorEntity savedAuthor = authorService.save(testAuthorEntityA);
//
//        AuthorEntity authorDto = TestDataUtil.createTestAuthorB();
//        authorDto.setId(savedAuthor.getId());
//
//        String authorDtoUpdateJson = objectMapper.writeValueAsString(authorDto);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(authorDtoUpdateJson)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName())
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge())
//        );
//    }
    @Test
    public void partialUpdateUpdatedVehicle() throws Exception{
        testVehicleEntityA.setId(null);
        vehicleService.addNewVehicle(testVehicleEntityA);
        testVehicleDtoA.setId(testVehicleEntityA.getId());

        String vehicleDtoUpJson = objectMapper.writeValueAsString(testVehicleDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/v1/vehicle/" + testVehicleEntityA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleDtoUpJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(testVehicleEntityA.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.carLicensePlate").value(testVehicleDtoA.getCarLicensePlate())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.mileage").value(testVehicleDtoA.getMileage())
        );

    }






    private VehicleDTO testVehicleDtoA = VehicleDTO.builder()
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

    private VehicleEntity testVehicleEntityB = VehicleEntity.builder()
            .id(1L)
            .carLicensePlate("X-XX 777")
            .firstRegistration(LocalDate.of(1999, Month.JUNE, 22))
            .mileage(8192)
            .vin("Renault VIN")
            .carBrand("Renault")
            .carModel("Megane")
            .carColor("Red")
            .enginePower(115)
            .petrol(Fuel.GASOLINE)
            .build();


}
