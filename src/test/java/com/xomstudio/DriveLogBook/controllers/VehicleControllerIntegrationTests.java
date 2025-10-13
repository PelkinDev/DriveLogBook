package com.xomstudio.DriveLogBook.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xomstudio.DriveLogBook.api.VehicleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class VehicleControllerIntegrationTests {

    private VehicleService vehicleService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public VehicleControllerIntegrationTests(VehicleService vehicleService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.vehicleService = vehicleService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void CreateVehicleSuccessfullyReturnsHttp201Created() throws Exception{

    }




}
