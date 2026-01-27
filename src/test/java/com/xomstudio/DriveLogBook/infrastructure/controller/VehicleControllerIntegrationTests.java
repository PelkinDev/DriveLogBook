package com.xomstudio.DriveLogBook.infrastructure.controller;


import com.xomstudio.DriveLogBook.TestDataUtil;
import com.xomstudio.DriveLogBook.api.Mapper;
import com.xomstudio.DriveLogBook.api.VehicleRepository;
import com.xomstudio.DriveLogBook.api.VehicleService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class VehicleControllerIntegrationTests {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    private MockMvc mockMvc;

    private Mapper<VehicleEntity, VehicleDTO> mapper;

    @Autowired
    public VehicleControllerIntegrationTests(MockMvc mockMvc, Mapper<VehicleEntity, VehicleDTO> mapper) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

    @Test
    public void shouldCreateOneVehicle() throws Exception {
        VehicleDTO vehicleDto = mapper.mapFromEntityToDTO(TestDataUtil.createTestVehicleA());
        vehicleDto.setId(null);

        String vehicleJSON = String.valueOf(vehicleDto);

        mockMvc.perform(post("/api/v1/vehicle/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(vehicleJSON))
                .andExpect(status().isCreated());

    }

//    void should_create_one_user() throws Exception {
//        final File jsonFile = new ClassPathResource("init/user.json").getFile();
//        final String userToCreate = Files.readString(jsonFile.toPath());
//
//        this.mockMvc.perform(post("/user/create")
//                        .contentType(APPLICATION_JSON)
//                        .content(userToCreate))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$").isMap())
//                .andExpect(jsonPath("$", aMapWithSize(3)));
//
//        assertThat(this.repository.findAll()).hasSize(6);
//    }


}
