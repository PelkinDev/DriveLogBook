package com.xomstudio.DriveLogBook.infrastructure.dto;


import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapperImpl implements VehicleMapper<VehicleEntity, VehicleDTO>{
    private ModelMapper modelMapper;

    public VehicleMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public VehicleDTO mapFromVehicleEntityToVehicleDTO(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }

    @Override
    public VehicleEntity mapFromVehicleDTOToVehicleEntity(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, VehicleEntity.class);
    }
}


