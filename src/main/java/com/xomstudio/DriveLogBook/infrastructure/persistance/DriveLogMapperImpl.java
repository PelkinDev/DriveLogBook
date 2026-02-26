package com.xomstudio.DriveLogBook.infrastructure.persistance;

import com.xomstudio.DriveLogBook.api.Mapper;
import com.xomstudio.DriveLogBook.domain.dto.DriveLogDTO;
import com.xomstudio.DriveLogBook.infrastructure.persistance.DriveLogEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DriveLogMapperImpl implements Mapper<DriveLogEntity, DriveLogDTO> {
    private ModelMapper modelMapper;

    public DriveLogMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DriveLogDTO mapFromEntityToDTO(DriveLogEntity driveLogEntity) {
        return modelMapper.map(driveLogEntity, DriveLogDTO.class);
    }

    @Override
    public DriveLogEntity mapFromDTOToEntity(DriveLogDTO driveLogDTO) {
        return modelMapper.map(driveLogDTO, DriveLogEntity.class);
    }
}
