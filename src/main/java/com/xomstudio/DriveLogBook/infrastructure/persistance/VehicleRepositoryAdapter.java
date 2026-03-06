package com.xomstudio.DriveLogBook.infrastructure.persistance;

import com.xomstudio.DriveLogBook.api.Mapper;
import com.xomstudio.DriveLogBook.api.VehicleRepository;
import com.xomstudio.DriveLogBook.domain.dto.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VehicleRepositoryAdapter implements VehicleRepository {

    private final VehicleJPARepository vehicleJPARepository;
    private final Mapper<VehicleEntity, VehicleDTO> mapper;

    @Autowired
    public VehicleRepositoryAdapter(VehicleJPARepository vehicleJPARepository, Mapper<VehicleEntity, VehicleDTO> mapper) {
        this.vehicleJPARepository = vehicleJPARepository;
        this.mapper = mapper;
    }


    public boolean existsById(Long id) {
        return vehicleJPARepository.existsById(id);
    }

    public Optional<VehicleDTO> getVehicleByCarLicensePlate(String carPlate){
        return vehicleJPARepository.findVehicleByCarLicensePlate(carPlate).map(mapper::mapFromEntityToDTO);
    }

    public List<VehicleDTO> findAll() {
        return vehicleJPARepository.findAll()
                .stream()
                .map(mapper::mapFromEntityToDTO)
                .collect(Collectors.toList());
    }

    public Optional<VehicleDTO> getVehicleById(Long vehicleId) {
        return vehicleJPARepository.findById(vehicleId).map(mapper::mapFromEntityToDTO);
    }

    public void store(VehicleDTO vehicleDTO) {
        vehicleJPARepository.save(mapper.mapFromDTOToEntity(vehicleDTO));
    }

    public void partialUpdate(Long id, VehicleDTO vehicleDTO){
        vehicleDTO.setId(id);
        VehicleEntity updatedVehicle = mapper.mapFromDTOToEntity(vehicleDTO);
        vehicleJPARepository.save(updatedVehicle);
    }

    public void deleteById(Long vehicleId) {
        vehicleJPARepository.deleteById(vehicleId);
    }
}