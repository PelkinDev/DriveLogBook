package com.xomstudio.DriveLogBook.domain.dto;

import com.xomstudio.DriveLogBook.api.Mapper;
import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapperImpl implements Mapper<VehicleEntity, VehicleDTO> {
    private ModelMapper modelMapper;

    public VehicleMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public VehicleDTO mapFromEntityToDTO(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }

    @Override
    public VehicleEntity mapFromDTOToEntity(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, VehicleEntity.class);
    }
}


