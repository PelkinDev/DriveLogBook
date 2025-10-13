package com.xomstudio.DriveLogBook.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xomstudio.DriveLogBook.api.VehicleService;
import com.xomstudio.DriveLogBook.infrastructure.entities.VehicleEntity;
import com.xomstudio.DriveLogBook.repositories.TestDataUtil;
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
    }

    @Test
    public void CreateVehicleSuccessfullyReturnsHttp201Created() throws Exception{

        VehicleEntity testVehicleA = TestDataUtil.createTestVehicleA();
        testVehicleA.setId(null);
        testVehicleA.setFirstRegistration(null);
        String vehicleJSON = objectMapper.writeValueAsString(testVehicleA);


//        Achtung der test wird erst wenn VehicleDTO im Controller implementiert wurde
        mockMvc.perform(
                MockMvcRequestBuilders.post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJSON)
                ).andExpect(MockMvcResultMatchers.status().isCreated()
        );
    }
}
